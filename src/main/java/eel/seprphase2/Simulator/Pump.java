/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.FailureModel.FailableComponent;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Yazidi
 */
public class Pump extends FailableComponent {

    private Port inputPort;
    private Port outputPort;
    private Mass capacity = kilograms(2);
    private boolean status = true;

    public Pump(Port input, Port output) {
        inputPort = input;
        outputPort = output;
    }

    public void step() {

        if (status) {
            if (inputPort.mass.inKilograms() > capacity.inKilograms()) {
                outputPort.mass = capacity;
            } else {
                outputPort.mass = inputPort.mass;
            }

            outputPort.temperature = inputPort.temperature;

        }

    }

    @Override
    public Percentage calculateWearDelta() {
        return percent(0);
    }

    public void setStatus(boolean newStatus) {
        status = newStatus;
    }

    public void setCapacity(Mass newCapacity) {
        capacity = newCapacity;
    }
}
