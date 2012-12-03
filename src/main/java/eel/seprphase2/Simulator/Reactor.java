/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import static eel.seprphase2.Simulator.PhysicalConstants.*;
import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;
import eel.seprphase2.Utilities.Volume;

/**
 *
 * @author Yazidi
 */
public class Reactor implements PlantController, PlantStatus {

    private FuelPile fuelPile = new FuelPile();
    private Mass waterMass;
    private Mass steamMass;
    private Temperature temperature;
    private Pressure pressure;
    private final Mass maximumWaterMass = kilograms(1000);
    private final Volume reactorVolume = cubicMetres(2);

    public Reactor() {
        fuelPile.moveControlRods(new Percentage(0));
        waterMass = maximumWaterMass;
        steamMass = kilograms(0);
        temperature = kelvin(350);
        pressure = pascals(101325);
    }

    public Reactor(Percentage controlRodPosition, Percentage waterLevel,
                   Temperature temperature, Pressure pressure) {
        fuelPile.moveControlRods(controlRodPosition);
        waterMass = kilograms(maximumWaterMass.inKilograms() * waterLevel.ratio());
        steamMass = kilograms(0);
        this.temperature = temperature;
        this.pressure = pressure;
    }

    @Override
    public void moveControlRods(Percentage extracted) {
        fuelPile.moveControlRods(extracted);
    }

    @Override
    public Percentage controlRodPosition() {
        return fuelPile.controlRodPosition();
    }

    @Override
    public Percentage waterLevel() {
        return new Percentage(waterMass.inKilograms() / maximumWaterMass.inKilograms());
    }

    @Override
    public Temperature temperature() {
        return this.temperature;
    }

    @Override
    public Pressure pressure() {
        return this.pressure;
    }

    public void step() {
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
        }
    }
}
