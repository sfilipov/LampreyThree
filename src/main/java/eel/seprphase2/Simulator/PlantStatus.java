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
    public boolean getReactorToTurbine();
    
    public Percentage turbineWear();

    public Temperature condenserTemperature();

    public Pressure condenserPressure();

    public Percentage condenserWaterLevel();
    
    public Percentage condenserWear();

    public Percentage reactorMinimumWaterLevel();

    public String[] listFailedComponents();

    public boolean turbineHasFailed();

    public ArrayList<FailableComponent> components();
}
