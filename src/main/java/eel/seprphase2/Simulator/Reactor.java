package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.GameOverException;
import static eel.seprphase2.Simulator.PhysicalConstants.*;
import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;
import eel.seprphase2.Utilities.Velocity;
import eel.seprphase2.Utilities.Volume;
import lamprey.seprphase3.DynSimulator.FlowThroughComponent;
import lamprey.seprphase3.DynSimulator.OutputPort;

/**
 *
 * @author Marius
 */
public class Reactor extends FailableComponent {

    private final Mass maximumWaterMass = kilograms(1000);
    private final Mass minimumWaterMass = kilograms(800);
    private final Volume reactorVolume = cubicMetres(5);
    @JsonProperty
    private FuelPile fuelPile = new FuelPile();
    @JsonProperty
    private Mass waterMass;
    @JsonProperty
    private Mass steamMass;
    @JsonProperty
    private Temperature temperature;
    @JsonProperty
    private Pressure pressure;
    @JsonProperty
    private Density steamDensity;
    @JsonProperty
    private double boilingPtAtPressure;
    @JsonProperty
    private double neededEnergy;
    private double deltaSeconds;

    /**
     *
     */
    public Reactor() {
        super();
        fuelPile.moveControlRods(new Percentage(0));
        waterMass = maximumWaterMass;
        steamMass = kilograms(0);
        temperature = kelvin(350);
        pressure = pascals(101325);
        this.pressurised = true;
    }

    /**
     *
     * @param controlRodPosition
     * @param waterLevel
     * @param temperature
     * @param pressure
     */
    public Reactor(Percentage controlRodPosition, Percentage waterLevel,
                   Temperature temperature, Pressure pressure) {
        super();
        fuelPile.moveControlRods(controlRodPosition);
        waterMass = kilograms(maximumWaterMass.inKilograms() * waterLevel.ratio());
        steamMass = kilograms(0);
        this.temperature = temperature;
        this.pressure = pressure;
        correctWaterMass();
    }

    /**
     *
     * @param extracted
     */
    public void moveControlRods(Percentage extracted) {
        fuelPile.moveControlRods(extracted);
    }

    /**
     *
     * @return
     */
    public Percentage controlRodPosition() {
        return fuelPile.controlRodPosition();
    }

    /**
     *
     * @return
     */
    public Percentage waterLevel() {
        return new Percentage((waterMass.inKilograms() / maximumWaterMass.inKilograms()) * 100);
    }

    /**
     *
     * @return
     */
    public Temperature temperature() {
        return this.temperature;
    }

    /**
     *
     * @return
     */
    public Pressure pressure() {
        return this.pressure;
    }
    
    private Mass getMassComingInOverTime(double seconds){
        return this.input.outputPort((FlowThroughComponent)this).flowRate.massFlowForTime(seconds);
    }
    
    private Mass massOfSteamLeavingOverTime(double seconds) {
        return this.outputPort(null).flownThroughInTime(seconds);
    }

    /**
     *
     */
    public void step(double seconds) throws GameOverException {
        System.out.println("RSM: " + steamMass);
        System.out.println("RWM: " + waterMass);
        deltaSeconds = seconds;
        if (steamMass.inKilograms() > massOfSteamLeavingOverTime(seconds).inKilograms()) {
            steamMass = steamMass.minus(massOfSteamLeavingOverTime(seconds));
            waterMass = waterMass.plus(getMassComingInOverTime(seconds));
            correctWaterMass();
            calculateNewTemperature(input.outputPort(this));
        } else {
            waterMass = waterMass.plus(steamMass);
            correctWaterMass();
            steamMass = kilograms(0);
            calculateNewTemperature(input.outputPort((FlowThroughComponent)this));
        }

        if (hasFailed()) {
            throw new GameOverException();
        }

        /*
         * Calculates the boiling point at the current pressure,
         * then it calculates the needed enegry to reach that boiling point
         */

        boilingPtAtPressure = boilingPointOfWater + 10 * Math.log(pressure.inPascals() / atmosphericPressure);

        neededEnergy = (boilingPtAtPressure - temperature.inKelvin()) * waterMass.inKilograms() * specificHeatOfWater;


        if (neededEnergy >= fuelPile.output(seconds)) {

            /*
             * Calculates how much the water heats if it's not at boiling point
             */

            temperature = kelvin(temperature.inKelvin() +
                                 fuelPile.output(seconds) / waterMass.inKilograms() /
                                 specificHeatOfWater);
        } else {

            /*
             * Sets temperature to boiling point
             * If any energy is left from the fuelpile after heating up:
             * Calculates how much water turns to steam in one timestep at boiling point using remaining energy
             */
            temperature = kelvin(boilingPtAtPressure);
            Mass deltaMass = kilograms((fuelPile.output(seconds) - neededEnergy) / latentHeatOfWater);
            steamMass = steamMass.plus(deltaMass);
            waterMass = waterMass.minus(deltaMass);
            correctWaterMass();
        }

        /*
         * Calculates volume of steam in this particular timestep
         * Calculates pressure of said steam
         */

        Volume steamVolume = reactorVolume.minus(waterMass.volumeAt(Density.ofLiquidWater()));
        pressure = IdealGas.pressure(steamVolume, steamMass, temperature);
        if (pressure.inPascals() < atmosphericPressure) {
            pressure = pascals(atmosphericPressure);
        }
        steamDensity = steamMass.densityAt(steamVolume);
        
        /*
         * Calculates component wear after a time step
         */
        stepWear();
    }

    /**
     *
     * @return
     */
    public Velocity outputFlowVelocity() {
        return metresPerSecond(pressure().inPascals() / 100);
    }

    public Mass maximumWaterMass() {
        return maximumWaterMass;
    }

    public Mass minimumWaterMass() {
        return minimumWaterMass;
    }

    public void calculateNewTemperature(OutputPort in) {
        temperature = kelvin((temperature.inKelvin() * waterMass.inKilograms() + in.temperature.inKelvin() * getMassComingInOverTime(deltaSeconds)
                              .inKilograms()) / (waterMass.inKilograms() + getMassComingInOverTime(deltaSeconds).inKilograms()));
    }

    @Override
    public Percentage calculateWearDelta() {
        return new Percentage(0);
    }

    public Percentage minimumWaterLevel() {
        return new Percentage((this.minimumWaterMass.inKilograms() / this.maximumWaterMass.inKilograms()) * 100);
    }

    // avoid issues with floating-point error
    private void correctWaterMass() {
        if (waterMass.inKilograms() > maximumWaterMass.inKilograms()) {
            waterMass = maximumWaterMass;
        }
        if (waterMass.inKilograms() < 0) {
            waterMass = kilograms(0);
        }
    }
}

    