/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.FailureModel.FailableComponent;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.*;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Yazidi
 */
public class Condenser extends FailableComponent {
    
    @JsonProperty
    private Mass waterMass;
    @JsonProperty
    private final Mass maximumWaterMass = kilograms(1000);
    @JsonProperty
    private Port inputPort = new Port();
    @JsonProperty
    private Port outputPort = new Port();
    @JsonProperty
    private Port reactorInputPort = new Port();
    @JsonProperty
    private Temperature temperature;
    @JsonProperty
    private int tempDelta = 10;
    @JsonProperty
    private Pressure pressure;
    @JsonProperty
    private Percentage waterLevel = percent(0);

    public Condenser() {
        waterMass = kilograms(0);
        inputPort.mass = kilograms(0);
        reactorInputPort.mass = kilograms(0);
        outputPort.mass = kilograms(0);
        inputPort.temperature = kelvin(0);
        reactorInputPort.temperature = kelvin(0);
        temperature = kelvin(373.15);
    }

    public void step() {
        waterMass = inputPort.mass;
        
        System.out.println("Condenser Steam Mass: " + inputPort.mass);
        
        if (inputPort.mass.inKilograms() > 0 || reactorInputPort.mass.inKilograms() > 0){
        calculateNewTemperature(inputPort);       
        calculateNewTemperature(reactorInputPort);
        }
        outputPort.mass = waterMass;
        reduceTemperature(tempDelta);
        outputPort.temperature = temperature;
        
        pressure = inputPort.pressure;
        waterLevel = new Percentage(waterMass.inKilograms() / maximumWaterMass.inKilograms());
        setWear(calculateWearDelta());
        
        System.out.println("Condenser Water Level: " + waterLevel);
        System.out.println("Condenser output Mass: " + outputPort.mass);
        System.out.println("Condenser Temperature " + temperature);
        
        
    }

    public void calculateNewTemperature(Port in) {
        temperature = kelvin((temperature.inKelvin() * waterMass.inKilograms() + in.temperature.inKelvin() * in.mass
                              .inKilograms()) / (waterMass.inKilograms() + in.mass.inKilograms()));
    }

    public void reduceTemperature(int delta) {
        temperature = temperature.minus(kelvin(delta));
    }

    public void setTemperature(Temperature newTemp) {
        temperature = newTemp;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setWaterMass(Mass newWater) {
        waterMass = newWater;
    }

    public Mass getWaterMass() {
        return waterMass;
    }

    public Pressure getPressure() {
        return pressure;
    }
    
    public Port inputPort()
    {
        return inputPort;
    }
    public Port outputPort()
    {
        return outputPort;
    }
    
    public Percentage getWaterLevel()
    {
        return waterLevel;
    }

    @Override
    public Percentage calculateWearDelta() {
        return percent(1);
    }
}
