/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;

/**
 *
 * @author david
 */
public interface PlantStatus {

    /**
     *
     * @return
     */
    public Percentage controlRodPosition();

    /**
     *
     * @return
     */
    public Pressure reactorPressure();

    /**
     *
     * @return
     */
    public Temperature reactorTemperature();

    /**
     *
     * @return
     */
    public Percentage reactorWaterLevel();
    
    /**
     *
     * @return
     */
    public Energy energyGenerated();
    
    /**
     *
     * @param open
     */
    public void setReactorToTurbine(boolean open);
    
    /**
     *
     * @return
     */
    public boolean getReactorToTurbine();
    
    public Temperature condenserTemperature();
    
    public Pressure condenserPressure();
    public Percentage condenserWaterLevel();
}
