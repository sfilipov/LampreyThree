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

    private Percentage controlRodPosition;
    private Percentage waterLevel;
    private Temperature temperature;
    private Pressure pressure;

    public Reactor() {
        controlRodPosition = new Percentage(0);
        waterLevel = new Percentage(100);
        temperature = new Temperature(350);
        pressure = new Pressure(101325);
    }

    public Reactor(Percentage controlRodPosition, Percentage waterLevel,
                   Temperature temperature, Pressure pressure) {
        this.controlRodPosition = controlRodPosition;
        this.waterLevel = waterLevel;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    @Override
    public void moveControlRods(Percentage extracted) {
        this.controlRodPosition = extracted;
    }

    @Override
    public Percentage controlRodPosition() {
        return this.controlRodPosition;
    }

    @Override
    public Percentage waterLevel() {
        return this.waterLevel;
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
    }
}
