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
    
    /**
     *
     */
    public Turbine() {
        super();
    }

    /**
     *
     */
    public void step() {
        System.out.println("Turbine Input Water Mass " + inputPort.mass);
        System.out.println("Turbine Output Water Mass 1 " + outputPort.mass);
            
        if (getFailureState() == FailureState.Normal) {
            outputPower = 10 * inputPort.mass.inKilograms();
            outputPort.mass = inputPort.mass;
            outputPort.pressure = inputPort.pressure;
            outputPort.temperature = inputPort.temperature;
            setWear(calculateWearDelta());
        } else {
            controller = Parser.returnController();
            controller.moveControlRods(new Percentage(0));
            setWear(new Percentage(100));
            }
        
        System.out.println("Turbine Output Water Mass 2 " + outputPort.mass);
    }

    /**
     *
     * @return
     */
    public double outputPower() {
        return outputPower;
    }

    /**
     *
     * @return
     */
    public Port inputPort() {
        return inputPort;
    }

    /**
     *
     * @return
     */
    public Port outputPort() {
        return outputPort;
    }

    /**
     *
     * @return
     */
    @Override
    public Percentage calculateWearDelta() {
        return new Percentage(1);
    }
}
