/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import eel.seprphase2.Utilities.Velocity;

/**
 *
 * @author Yazidi
 */
public class Turbine {

    private Pressure inputPressure;
    private Pressure outputPressure;
    private Temperature temperature;
    private Velocity flowVelocity;
    private double outputPower;

    public void setInputPressure(Pressure pressure) {
        this.inputPressure = pressure;
    }

    public void setOutputPressure(Pressure pressure) {
        this.outputPressure = pressure;
    }

    public void setInputTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
    
    public void setFlowVelocity(Velocity flowVelocity){
        this.flowVelocity = flowVelocity;
    }
    
    public void step(){
        outputPower = 10 * flowVelocity.inMetresPerSecond();
    }

    public double outputPower() {
        return outputPower;
    }
}
