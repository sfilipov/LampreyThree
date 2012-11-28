/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantStatus;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;

/**
 *
 * @author david
 */
public class MockStatus implements PlantStatus {

    public Percentage controlRodPosition;
    public Pressure pressure;
    public Temperature temperature;
    public Percentage waterLevel;
    
    @Override
    public Percentage controlRodPosition() {
        return controlRodPosition;
    }

    @Override
    public Pressure pressure() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Temperature temperature() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Percentage waterLevel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
