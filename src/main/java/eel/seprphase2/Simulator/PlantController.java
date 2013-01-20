/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.FailureModel.CannotControlException;
import eel.seprphase2.Simulator.FailureModel.CannotRepairException;
import eel.seprphase2.Utilities.Percentage;
import java.io.IOException;

/**
 *
 * @author david
 */
public interface PlantController {
    
    /**
     *
     * @param extracted
     */
    public void moveControlRods(Percentage extracted);
    
    /**
     * 
     */
    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException;
    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException;
    public void repairPump(int pumpNumber) throws KeyNotFoundException,  CannotRepairException;
    public void repairCondenser() throws CannotRepairException;
    public void repairTurbine() throws CannotRepairException;
   
    

}
