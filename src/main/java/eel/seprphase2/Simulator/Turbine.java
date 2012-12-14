/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.FailureModel.FailableComponent;
import eel.seprphase2.FailureModel.FailureState;
import eel.seprphase2.TextInterface.Parser;
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
    @JsonIgnore
    private static PlantController controller;   
    
    public Turbine() {
        super();
    }

    public void step() {

        Pressure deltaPressure = inputPort.pressure
                .minus(outputPort.pressure);
        Velocity flowVelocity = Bernoulli.velocity(deltaPressure, inputPort.density);
        
        if (getFailureState() == FailureState.Normal) {
            outputPower = 10 * flowVelocity.inMetresPerSecond();
            Percentage wearDelta = calculateWearDelta();
            setWear(wearDelta);
        } else {
            controller = Parser.returnController();
            controller.moveControlRods(new Percentage(0));
            setWear(new Percentage(100));
            }
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
