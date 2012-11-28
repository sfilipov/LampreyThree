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

    private Percentage controlRodPosition = new Percentage(0);
    private Percentage waterLevel = new Percentage(100);
    private Temperature temperature = new Temperature(25);
    private Pressure pressure = new Pressure(101325);

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
}
