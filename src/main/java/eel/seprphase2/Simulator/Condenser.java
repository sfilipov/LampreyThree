package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.*;
import static eel.seprphase2.Utilities.Units.*;
import static lamprey.seprphase3.Utilities.Units.*;
import static eel.seprphase2.Simulator.PhysicalConstants.atmosphericPressure;
import lamprey.seprphase3.DynSimulator.OutputPort;
import static lamprey.seprphase3.DynSimulator.GameConfig.*;
import lamprey.seprphase3.Utilities.MassFlowRate;

/**
 * 
 * @author Marius
 */
public class Condenser extends FailableComponent {

    @JsonProperty
    private final Mass maximumWaterMass = CONDENSER_VOLUME.massAt(Density.ofLiquidWater());
    @JsonProperty
    private Mass steamMass;
    @JsonProperty
    private Mass waterMass;
    @JsonProperty
    private OutputPort coolantInput;
    @JsonProperty
    private Temperature temperature;
    @JsonProperty
    private Pressure pressure;
    @JsonProperty
    private Percentage waterLevel = percent(0);
    // Duration for current step.
    @JsonProperty
    private double deltaSeconds;

    public Condenser() {
        initializeVariables();
    }

    public void step(double seconds) {
        System.out.println("Condenser: ");
        System.out.println("\t steam mass: " + steamMass);
        System.out.println("\t water mass: " + waterMass);
        System.out.println("\t max water mass: " + maximumWaterMass);
        
        deltaSeconds = seconds;
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
        Temperature tempDiff = temperature.minus(coolantInput.temperature);
        double dropOffCoefficient = 0.3;
        MassFlowRate rateOfCondensation = kilogramsPerSecond(CONDENSER_MAXSTEAMCONDENSEDPERSEC.inKilogramsPerSecond()/((dropOffCoefficient * tempDiff.inKelvin()) + 1));
        Mass steamCondensed = rateOfCondensation.massFlowForTime(deltaSeconds);
        System.out.println("Rate of condensation" + rateOfCondensation);
        System.out.println("\tmax rate: " + CONDENSER_MAXSTEAMCONDENSEDPERSEC.inKilogramsPerSecond());
        System.out.println("\ttemp ratio: " + (temperature.inKelvin() / coolantInput.temperature.inKelvin()));

        if (steamCondensed.inKilograms() > this.steamMass.inKilograms()) {
            steamCondensed = this.steamMass;
        }
        steamMass = steamMass.minus(steamCondensed);
        waterMass = waterMass.plus(steamCondensed);
        
    }
    
    /**
     * Returns the mass of water / steam flowing through an OutputPort (port)
     * for a given duration (seconds)
     * @param port port to calculate for.
     * @param seconds duration.
     * @return the mass flowing through port for the duration, seconds.
     */
    private Mass getMassComingThroughOverTime(OutputPort port, double seconds) {
        return port.flowRate.massFlowForTime(seconds);
    }

    /**
     * Returns the volume that the steam in the condenser occupies.
     * (Condenser volume - volume occupied by water).
     * @return the volume that the steam in the condenser occupies.
     */
    private Volume calculateSteamVolume() {
        return CONDENSER_VOLUME.minus(waterMass.volumeAt(Density.ofLiquidWater()));
    }

    /**
     * Calculates the change in temperature due to the amount of steam coming in through
     * the OutputPort in.
     * @param in input port for steam flowing in to condenser.
     */
    private Temperature calculateNewTemperature(OutputPort in) {
        Temperature deltaTemp = in.temperature.minus(this.temperature);
        System.out.println("\t steam in temp diff: " + deltaTemp.inKelvin());
        Mass totalMass = this.steamMass.plus(this.waterMass);
        Mass massIn = in.flownThroughInTime(deltaSeconds);
        deltaTemp = kelvin(deltaTemp.inKelvin() * (massIn.inKilograms() / totalMass.inKilograms()));
        return deltaTemp;
    }

    private void initializeVariables() {
        pressure = pascals(atmosphericPressure);
        waterMass = CONDENSER_INITIALWATERMASS;
        steamMass = kilograms(0);
        temperature = ROOMTEMPERATURE; 
        coolantInput = new HeatSink().outputPort();
        this.pressurised = true;
    }

    private void pullInSteam() {
        Mass incomingSteam = input.outputPort(this).flownThroughInTime(deltaSeconds);
        System.out.println("\tIncoming steam: " + incomingSteam);
        if (incomingSteam.inKilograms() > 0) {
            this.temperature = temperature.plus(calculateNewTemperature(input.outputPort(this)));
            steamMass = steamMass.plus(incomingSteam);
        }
    }

    private void coolantEffect() {
        if (!hasFailed()) {
            temperature = temperature.plus(calculateNewTemperature(coolantInput));
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
    
     // Possibly remove
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
    }
}
