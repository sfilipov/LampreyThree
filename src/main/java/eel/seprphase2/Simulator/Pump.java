/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Marius
 */
public class Pump extends FailableComponent {

    @JsonProperty
    private Port inputPort;
    @JsonProperty
    private Port outputPort;
    @JsonProperty
    private Mass capacity = kilograms(3);
    @JsonProperty
    private boolean status = true;

    private Pump() {
        super();
        inputPort = null;
        outputPort = null;
    }

    public Pump(Port input, Port output) {

        inputPort = input;
        outputPort = output;

    }

    public void step() {
        if (hasFailed()) {
            outputPort.mass = kilograms(0);
        }
        if (status) {
            if (inputPort.mass.inKilograms() > capacity.inKilograms()) {
                outputPort.mass = capacity;
                inputPort.mass = inputPort.mass.minus(capacity);
            } else {
                outputPort.mass = inputPort.mass;
                inputPort.mass = kilograms(0);
            }

            outputPort.temperature = inputPort.temperature;
            stepWear();
        }
    }

    @Override
    public Percentage calculateWearDelta() {
        return percent(1);
    }

    public void setStatus(boolean newStatus) {
        status = newStatus;
    }

    public void setCapacity(Mass newCapacity) {
        capacity = newCapacity;
    }

    public boolean getStatus() {
        return status;
    }

    public Port inputPort() {
        return inputPort;
    }

    public Port outputPort() {
        return outputPort;
    }
}
