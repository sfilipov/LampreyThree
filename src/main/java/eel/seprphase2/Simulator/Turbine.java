/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;

/**
 *
 * @author Yazidi
 */
public class Turbine {

    private Pressure inputPressure;
    private Pressure outputPressure;
    private Temperature temperature;

    public void setInputPressure(Pressure pressure) {
        this.inputPressure = pressure;
    }

    public void setOutputPressure(Pressure pressure) {
        this.outputPressure = pressure;
    }

    public void setInputTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public int outputPower() {
        return 0;
    }
}
