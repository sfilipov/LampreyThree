/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonParseException;
import eel.seprphase2.Simulator.PhysicalModel.PhysicalModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Persistence.FileSystem;
import eel.seprphase2.Simulator.FailureModel.CannotControlException;
import eel.seprphase2.Simulator.FailureModel.FailureModel;
import eel.seprphase2.Persistence.SaveGame;
import eel.seprphase2.Simulator.FailureModel.CannotRepairException;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class Simulator implements PlantController, PlantStatus, GameManager {

    private FailureModel failureModel;
    private String userName;

    public Simulator() {
        PhysicalModel physicalModel = new PhysicalModel();
        failureModel = new FailureModel(physicalModel);
        userName = "";
    }
    
    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public void saveGame() throws JsonProcessingException {
        SaveGame saveGame = new SaveGame(failureModel, userName);
        try {
            saveGame.save();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void loadGame(int gameNumber) {
        try {
            SaveGame saveGame = SaveGame.load(listGames()[gameNumber]);
            this.failureModel = saveGame.getFailureModel();
            this.userName = saveGame.getUserName();
        } catch (JsonParseException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String[] listGames() {
        return FileSystem.listSaveGames(userName);
    }
    
    public String[] listFailedComponents() {
        return failureModel.listFailedComponents();
    }

    public void step() {
        failureModel.step();
    }

    public void failStateCheck() {
        failureModel.failStateCheck();
    }

    public void moveControlRods(Percentage extracted) {
        failureModel.moveControlRods(extracted);
    }

    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException {
        failureModel.changeValveState(valveNumber, isOpen);
    }

    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException {
        failureModel.changePumpState(pumpNumber, isPumping);
    }

    public void repairPump(int pumpNumber) throws KeyNotFoundException,CannotRepairException  {
        failureModel.repairPump(pumpNumber);
    }

    public void repairCondenser() throws CannotRepairException {
        failureModel.repairCondenser();
    }

    public void repairTurbine() throws CannotRepairException  {
        failureModel.repairTurbine();
    }

    public Percentage controlRodPosition() {
        return failureModel.controlRodPosition();
    }

    public Pressure reactorPressure() {
        return failureModel.reactorPressure();
    }

    public Temperature reactorTemperature() {
        return failureModel.reactorTemperature();
    }

    public Percentage reactorWaterLevel() {
        return failureModel.reactorWaterLevel();
    }

    public Energy energyGenerated() {
        return failureModel.energyGenerated();
    }

    public void setReactorToTurbine(boolean open) {
        failureModel.setReactorToTurbine(open);
    }

    public boolean getReactorToTurbine() {
        return failureModel.getReactorToTurbine();
    }

    public Temperature condenserTemperature() {
        return failureModel.condenserTemperature();
    }

    public Pressure condenserPressure() {
        return failureModel.condenserPressure();
    }

    public Percentage condenserWaterLevel() {
        return failureModel.condenserWaterLevel();
    }

    public Percentage reactorMinimumWaterLevel() {
        return failureModel.reactorMinimumWaterLevel();
    }

}
