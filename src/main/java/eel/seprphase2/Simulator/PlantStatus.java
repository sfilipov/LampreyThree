package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import java.util.ArrayList;

/**
 *
 * This class is responsible for declaring the methods that will need to be used to VIEW the status of something
 * in the simulation, be is reactor pressure/temp, control rod position etc. 
 * It allows these to be called from the UI (higher up)
 * @author David
 */
public interface PlantStatus {

    public Percentage controlRodPosition();

    public Pressure reactorPressure();

    public Temperature reactorTemperature();

    public Percentage reactorWaterLevel();

    public Percentage reactorWear();

    public Energy energyGenerated();

    public Boolean getPumpState(int pumpNumber) throws KeyNotFoundException;
    
    public Boolean getValveState(int valveNumber) throws KeyNotFoundException;
                                
    public Percentage getPumpWear(int pumpNumber)throws KeyNotFoundException;
        
    public int getSoftwareFailureTimeRemaining();
    
    public Percentage turbineWear();
    
    public String wornComponent();

    public Temperature condenserTemperature();

    public Pressure condenserPressure();

    public Percentage condenserWaterLevel();
    
    public Percentage condenserWear();
    
    public double getOutputPower();

    public Percentage reactorMinimumWaterLevel();
    
    public Pressure reactorMaximumPressure();
    
    public Temperature reactorMaximumTemperature();

    public String[] listFailedComponents();

    public boolean turbineHasFailed();

    public ArrayList<FailableComponent> failableComponents();
   
    public String getUsername();
    
}
