package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.GameOverException;
import eel.seprphase2.Utilities.Percentage;
import java.io.IOException;

/**
 * This class is responsible for declaring the methods that will need to be used to CHANGE something in the simulation,
 * be is valveState, control rod position etc. It allows these to be called from the UI (higher up)
 * @author David
 */
public interface PlantController {


    public void moveControlRods(Percentage extracted);
    
    public void setWornComponent(FailableComponent currentWornComponent);

    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException;
    
    public void flipValveState(int valveNumber) throws KeyNotFoundException;

    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException;
    
    public void flipPumpState(int pumpNumber) throws CannotControlException, KeyNotFoundException;

    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException;

    public void repairCondenser() throws CannotRepairException;

    public void repairTurbine() throws CannotRepairException;

    public void failCondenser();

    public void wearReactor();
    
    public void setSoftwareFailureTimeRemaining(int failureTime);
    
    public void wearCondenser();

    public void step(double seconds) throws GameOverException;
}
