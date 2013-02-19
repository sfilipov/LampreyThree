package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import java.util.ArrayList;

/**
 *
 * @author David
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
    
    public Percentage reactorWear();
    
    /**
     *
     * @return
     */
    
    public Energy energyGenerated();

    /**
     *
     * @return
     */
    
    public Percentage pumpWear(int pumpID) throws KeyNotFoundException;
    
    public boolean isValveOpen(int valveID) throws KeyNotFoundException;
    
    public boolean getPumpStatus(int pumpID) throws KeyNotFoundException;
    
    public Percentage turbineWear();
    
    public String wornComponent();

    public Temperature condenserTemperature();

    public Pressure condenserPressure();

    public Percentage condenserWaterLevel();
    
    public Percentage condenserWear();

    public Percentage reactorMinimumWaterLevel();

    public String[] listFailedComponents();

    public boolean turbineHasFailed();

    public ArrayList<FailableComponent> failableComponents();
   
}
