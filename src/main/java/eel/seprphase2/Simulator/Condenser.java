/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import eel.seprphase2.Utilities.Volume;
import eel.seprphase2.Utilities.Units;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.FailureModel.FailableComponent;
import eel.seprphase2.FailureModel.FailureState;
import static eel.seprphase2.Simulator.PhysicalConstants.*;
import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;
import eel.seprphase2.Utilities.Velocity;
import eel.seprphase2.Utilities.Volume;
/**
 *
 * @author James
 */
public class Condenser extends FailableComponent {

    private final Mass maximumWaterMass = kilograms(1000);
    private final Volume reactorVolume = cubicMetres(2);

    @JsonProperty
    private Mass waterMass;
    @JsonProperty
    private Mass steamMass;
    @JsonProperty
    private Temperature temperature;
    @JsonProperty
    private Pressure pressure;
    @JsonProperty
    private Density steamDensity;
    @JsonProperty
    private Port inputPort = new Port();
    
    
    public Condenser() {
        super();
        waterMass = kilograms(0);
        steamMass = kilograms(0);
        temperature = kelvin(350);
        pressure = pascals(101325);
    }
    
    public Percentage waterLevel() {
        return new Percentage(waterMass.inKilograms() / maximumWaterMass.inKilograms());
    }

    public Temperature temperature() {
        return this.temperature;
    }

    public Pressure pressure() {
        return this.pressure;
    }
    
    
    public void step()
    {
        if (getFailureState() == FailureState.Normal){
            steamMass = steamMass.plus(inputPort.mass);
            pressure = IdealGas.pressure(calculateSteamVolume(), steamMass, temperature);
            
            
            
            waterMass = waterMass.plus(steamMass);
            steamMass = kilograms(0);
            
           
            pressure = IdealGas.pressure(calculateSteamVolume(), steamMass, temperature);
            
            
            Percentage wearDelta = calculateWearDelta();
            setWear(wearDelta);
        }
        
        
    }
    
    /*
     * @Todo subtract water volume
     */
    private Volume calculateSteamVolume()
    {
        return reactorVolume.minus(waterMass.volumeAt(Density.ofLiquidWater()));
    }
    
    @Override
    public Percentage calculateWearDelta()
    {
        return new Percentage(0);
    }
    
}
