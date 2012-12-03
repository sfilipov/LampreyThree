/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;

/**
 *
 * @author Yazidi
 */
public class Reactor implements PlantController, PlantStatus {

    private FuelPile fuelPile = new FuelPile();
    private int waterMass;
    private int steamMass;
    private Temperature temperature;
    private Pressure pressure;
    private final int specificHeatOfWater = 4181;
    private final int latentHeatOfWater = 2260000;
    private double gasConstant = 8.314;
    private final int maximumWaterMass = 1000;
    private final double volume = 2; // cubic metres

    public Reactor() {
        fuelPile.moveControlRods(new Percentage(0));
        waterMass = maximumWaterMass;
        temperature = new Temperature(350);
        pressure = new Pressure(101325);
    }

    public Reactor(Percentage controlRodPosition, Percentage waterLevel,
                   Temperature temperature, Pressure pressure) {
        fuelPile.moveControlRods(controlRodPosition);
        waterMass = (int)(maximumWaterMass * waterLevel.ratio());
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
        return new Percentage((waterMass * 1.0) / maximumWaterMass);
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
        if (temperature.degreesKelvin() < 373.15) {
            temperature = new Temperature(temperature.degreesKelvin() +
                                          fuelPile.output(1) / waterMass /
                                          specificHeatOfWater);
        } else {
            int deltaMass = fuelPile.output(1) / latentHeatOfWater;
            steamMass += deltaMass;
            waterMass -= deltaMass;
            double steamVolume = volume - waterMass/1000;
            pressure = new Pressure((int)((molesOfSteam() * gasConstant * temperature.degreesKelvin()) / steamVolume));
        }
    }
    
    private double molesOfSteam() {
        return steamMass * 1000 / 18;
    }
    
}
