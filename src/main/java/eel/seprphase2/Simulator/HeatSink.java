/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Units;
import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Temperature;
import eel.seprphase2.Utilities.Mass;

/**
 *
 * @author James
 */
public class HeatSink {
    private Port outputPort;
    
    public HeatSink() {
        outputPort = new Port();
        outputPort.temperature = eel.seprphase2.Utilities.Units.kelvin(308.15);
        outputPort.density = Density.ofLiquidWater();
        outputPort.mass = eel.seprphase2.Utilities.Units.kilograms(10);
    }
    
    public Port outputPort() {
        return outputPort;
    }

}
