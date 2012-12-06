/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.FailureModel.FailableComponent;
import static eel.seprphase2.Simulator.PhysicalConstants.*;
import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;
import eel.seprphase2.Utilities.Velocity;
import eel.seprphase2.Utilities.Volume;

/**
 *
 * @author Yazidi
 */
public class Reactor extends FailableComponent{

    private final Mass maximumWaterMass = kilograms(1000);
    private final Volume reactorVolume = cubicMetres(2);
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
    private Port outputPort = new Port();

    public Reactor() {
        super();
        fuelPile.moveControlRods(new Percentage(0));
        waterMass = maximumWaterMass;
        steamMass = kilograms(0);
        temperature = kelvin(350);
        pressure = pascals(101325);
    }

    public Reactor(Percentage controlRodPosition, Percentage waterLevel,
                   Temperature temperature, Pressure pressure) {
        super();
        fuelPile.moveControlRods(controlRodPosition);
        waterMass = kilograms(maximumWaterMass.inKilograms() * waterLevel.ratio());
        steamMass = kilograms(0);
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public void moveControlRods(Percentage extracted) {
        fuelPile.moveControlRods(extracted);
    }

    public Percentage controlRodPosition() {
        return fuelPile.controlRodPosition();
    }

    public Percentage waterLevel() {
        return new Percentage(waterMass.inKilograms() / maximumWaterMass.inKilograms());
    }

    public Temperature temperature() {
        return this.temperature;
    }

    public Pressure pressure() {
        return this.pressure;
    }

    public void step() {
        steamMass = steamMass.plus(outputPort.mass);
        if (temperature.inKelvin() < boilingPointOfWater) {
            temperature = kelvin(temperature.inKelvin() +
                                 fuelPile.output(1) / waterMass.inKilograms() /
                                 specificHeatOfWater);
        } else {
            Mass deltaMass = kilograms(fuelPile.output(1) / latentHeatOfWater);
            steamMass = steamMass.plus(deltaMass);
            waterMass = waterMass.minus(deltaMass);
            Volume steamVolume = reactorVolume.minus(waterMass.volumeAt(Density.ofLiquidWater()));
            pressure = IdealGas.pressure(steamVolume, steamMass, temperature);
            steamDensity = steamMass.densityAt(steamVolume);
            outputPort.density = steamDensity;
            outputPort.pressure = pressure;
            outputPort.temperature = temperature;   
        }
        Percentage wearDelta = calculateWearDelta();
        setWear(wearDelta);
    }

    public Velocity outputFlowVelocity() {
        return metresPerSecond(pressure().inPascals() / 100);
    }

    public Port outputPort() {
        return outputPort;
    }
    
    @Override
    public Percentage calculateWearDelta()
    {
        return new Percentage(1);
    }
}
