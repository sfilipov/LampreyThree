/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author david
 */
public interface PlantController {
    /**
     *
     * @param username
     */
    public void setUsername(String username);
    /**
     *
     * @param extracted
     */
    public void moveControlRods(Percentage extracted);
    
    /**
     * 
     */
    public void changeValveState(int valveNumber, boolean isOpen);
    public void changePumpState(int pumpNumber, boolean isPumping);
    public void repairPump(int pumpNumber);
    public void repairReactor();
    public void repairTurbine();
    public void repairCondenser();
    
    public void saveGame() throws JsonProcessingException;
    public void loadGame();
    public String[] listGames();
}
