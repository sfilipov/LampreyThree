package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.GameOverException;
import eel.seprphase2.Utilities.Percentage;
import java.io.IOException;

/**
 *
 * @author David
 */
public interface PlantController {

    /**
     *
     * @param extracted
     */
    public void moveControlRods(Percentage extracted);
    
    public void setWornComponent(FailableComponent currentWornComponent);

    /**
     *
     */
    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException;

    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException;

    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException;

    public void repairCondenser() throws CannotRepairException;

    public void repairTurbine() throws CannotRepairException;

    public void failCondenser();

    public void wearReactor();

    public void setReactorToTurbine(boolean open);

    public void step(int steps) throws GameOverException;
}
