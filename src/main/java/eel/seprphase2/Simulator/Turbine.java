/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import FailureModel.FailableComponent;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import eel.seprphase2.Utilities.Velocity;

/**
 *
 * @author Yazidi
 */
public class Turbine extends FailableComponent {

    private Pressure inputPressure;
    private Pressure outputPressure;
    private Temperature temperature;
    private Velocity flowVelocity;
    private double outputPower;
    private Port inputPort = new Port();
    private Port outputPort = new Port();

    public Turbine(){
        super();
    }
    
    public void setFlowVelocity(Velocity flowVelocity) {
        this.flowVelocity = flowVelocity;
    }

    public void step() {
        Pressure deltaPressure = inputPort.pressure
                .minus(outputPort.pressure);
        Velocity flowVelocity = Bernoulli.velocity(deltaPressure, inputPort.density);
        outputPower = 10 * flowVelocity.inMetresPerSecond();
    }

    public double outputPower() {
        return outputPower;
    }

    public Port inputPort() {
        return inputPort;
    }

    public Port outputPort() {
        return outputPort;
    }
}
