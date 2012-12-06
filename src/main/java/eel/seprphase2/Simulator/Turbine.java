/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.FailureModel.FailableComponent;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import eel.seprphase2.Utilities.Velocity;

/**
 *
 * @author Yazidi
 */
public class Turbine extends FailableComponent {

    @JsonProperty
    private double outputPower;
    @JsonProperty
    private Port inputPort = new Port();
    @JsonProperty
    private Port outputPort = new Port();

    public Turbine() {
        super();
    }

    public void step() {
        Pressure deltaPressure = inputPort.pressure
                .minus(outputPort.pressure);
        Velocity flowVelocity = Bernoulli.velocity(deltaPressure, inputPort.density);
        outputPower = 10 * flowVelocity.inMetresPerSecond();

        Percentage wearDelta = calculateWearDelta();
        setWear(wearDelta);
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

    @Override
    public Percentage calculateWearDelta() {
        return new Percentage(1);
    }
}
