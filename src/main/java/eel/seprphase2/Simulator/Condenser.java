package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.*;
import static eel.seprphase2.Utilities.Units.*;
import static lamprey.seprphase3.Utilities.Units.*;
import lamprey.seprphase3.DynSimulator.OutputPort;
import static lamprey.seprphase3.DynSimulator.GameConfig.MaxSteamCondensedPerSecond;
import static lamprey.seprphase3.DynSimulator.GameConfig.RoomTemperature;
import lamprey.seprphase3.Utilities.MassFlowRate;

/**
 *
 * @author Marius
 */
public class Condenser extends FailableComponent {

    @JsonProperty
    private Mass steamMass;
    @JsonProperty
    private Mass waterMass;
    @JsonProperty
    private final Mass maximumWaterMass = kilograms(1000);
    @JsonProperty
    private OutputPort coolantInput;
    @JsonProperty
    private Temperature temperature;
    @JsonProperty
    private Pressure pressure;
    @JsonProperty
    private Percentage waterLevel = percent(0);
    @JsonProperty
    private Mass incomingSteam;
    private double deltaSeconds;

    public Condenser() {
        initializeVariables();
    }

    public void step(double seconds) {
        System.out.println("CSM: " + steamMass);
        System.out.println("CWM: " + waterMass);
        
        deltaSeconds = seconds;
        incomingSteam = getMassComingThroughOverTime(input.outputPort(this), deltaSeconds);
        pullInSteam();
        coolantEffect();
        condenseSteam();
        pushOutWater();
        updatePressure();

        if (!hasFailed()) {
            stepWear();
        }
    }

    private void condenseSteam() {
        MassFlowRate rateOfCondensation = kilogramsPerSecond(MaxSteamCondensedPerSecond.inKilogramsPerSecond() * 
                                                             Math.log(temperature.inKelvin() / RoomTemperature.inKelvin()));
        Mass steamCondensed = rateOfCondensation.massFlowForTime(deltaSeconds);
        if (steamCondensed.inKilograms() > this.steamMass.inKilograms()) {
            steamCondensed = this.steamMass;
        }
        System.out.println("Mass Of Steam Condensed" + steamCondensed);
        steamMass = steamMass.minus(steamCondensed);
        waterMass = waterMass.plus(steamCondensed);
        
    }

    private Mass getMassComingThroughOverTime(OutputPort port, double seconds) {
        return port.flowRate.massFlowForTime(seconds);
    }

    /*
     * @Todo subtract water volume
     */
    private Volume calculateSteamVolume() {
        return maximumWaterMass.volumeAt(Density.ofLiquidWater()).minus(waterMass.volumeAt(Density.ofLiquidWater()));
    }

    public void calculateNewTemperature(OutputPort in) {
        /*
         temperature = kelvin((temperature.inKelvin() * waterMass.inKilograms() + in.temperature.inKelvin() * in.mass
         .inKilograms()) / (waterMass.inKilograms() + in.mass.inKilograms()));
         */


        /*
         * 4.19 kJ/kg = c_w of water (specific heat of water)
         * h_fg = c_w * (t_f - t_0) = enthalpy of water
         * t_f = steam saturation temperature = 100C
         * t_0 = reference temperature = 0C
         * 
         * m_s*h_fg = mass of steam * enthalpy = thermal energy
         */
        Temperature delta = new Temperature(((4.19 * (in.temperature.inKelvin() - temperature.inKelvin())) / 1000) 
                                            * getMassComingThroughOverTime(in, deltaSeconds).inKilograms());
        temperature = temperature.plus(delta);


    }

    public void reduceTemperature(int delta) {
        temperature = temperature.minus(kelvin(delta));
    }

    public void setTemperature(Temperature newTemp) {
        temperature = newTemp;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setWaterMass(Mass newWater) {
        waterMass = newWater;
    }

    public Mass getWaterMass() {
        return waterMass;
    }

    public Pressure getPressure() {
        return pressure;
    }

    public Percentage getWaterLevel() {
        return waterLevel;
    }

    @Override
    public Percentage calculateWearDelta() {
        return percent(1);
    }

    @Override
    public void repair() throws CannotRepairException {
        super.repair();
        initializeVariables();
    }

    private void initializeVariables() {
        pressure = pascals(101325);
        waterMass = kilograms(300);
        steamMass = kilograms(0);
        temperature = kelvin(298.15);  //Start at room temp
        coolantInput = new HeatSink().outputPort();
        this.pressurised = true;
    }

    private void pullInSteam() {
        if (incomingSteam.inKilograms() > 0) {
            steamMass = steamMass.plus(incomingSteam);
            calculateNewTemperature(input.outputPort(this));
        }
    }

    private void coolantEffect() {
        if (!hasFailed()) {
            calculateNewTemperature(coolantInput);
        }
    }

    private void updatePressure() {
        pressure = IdealGas.pressure(calculateSteamVolume(), steamMass, temperature);
    }

    private void pushOutWater() {
        Mass waterOut = getMassComingThroughOverTime(outputPort(null), deltaSeconds);
        if (waterOut.inKilograms() > waterMass.inKilograms()) {
            waterOut = waterMass;
        }
        waterMass.minus(waterOut);
        try {
            waterLevel = new Percentage((waterMass.inKilograms() / maximumWaterMass.inKilograms()) * 100);
        } catch (Exception e) {
            waterLevel = new Percentage(100);
            //This is over-pressure condition, however will be handeled by failure model and not needed to be done here
        }
    }
}
