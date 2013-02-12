package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.GameOverException;
import eel.seprphase2.Persistence.FileSystem;
import eel.seprphase2.Persistence.SaveGame;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author David
 */
public class Simulator implements PlantController, PlantStatus, GameManager {

    private PhysicalModel physicalModel;
    private FailureModel failureModel;
    private String userName;

    public Simulator() {
        physicalModel = new PhysicalModel();
        failureModel = new FailureModel(physicalModel, physicalModel);
        userName = "";
    }

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public void saveGame() throws JsonProcessingException {
        SaveGame saveGame = new SaveGame(physicalModel, failureModel, userName);
        try {
            saveGame.save();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public void loadGame(int gameNumber) {
        try {
            SaveGame saveGame = SaveGame.load(listGames()[gameNumber]);
            this.physicalModel = saveGame.getPhysicalModel();
            this.failureModel = new FailureModel(physicalModel, physicalModel);
            this.userName = saveGame.getUserName();
        } catch (JsonParseException ex) {
        } catch (IOException ex) {
        }
    }

    @Override
    public String[] listGames() {
        return FileSystem.listSaveGames(userName);
    }

    @Override
    public String[] listFailedComponents() {
        return failureModel.listFailedComponents();
    }

    public void step() throws GameOverException {
        try {
            failureModel.step();
        } catch (GameOverException e) {
            throw new GameOverException("Dear " + userName + ",\n\n" +
                                        "YOU HAVE FAILED\n\n" +
                                        "The reactor vessel has failed catastrophically,\n"+ 
                                        "and everyone within a 100km radius is now either\n " +
                                        "dead or dying of radiation\n" +"poisioning.\n\n" +
                                        "However, you did successfully generate \n" + failureModel.energyGenerated() +
                                        "\nof energy before this occurred.");
        }
    }

    public void failStateCheck() {
        failureModel.failStateCheck();
    }

    @Override
    public void moveControlRods(Percentage extracted) {
        failureModel.moveControlRods(extracted);
    }

    @Override
    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException {
        failureModel.changeValveState(valveNumber, isOpen);
    }

    @Override
    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException {
        failureModel.changePumpState(pumpNumber, isPumping);
    }

    @Override
    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException {
        failureModel.repairPump(pumpNumber);
    }

    @Override
    public void repairCondenser() throws CannotRepairException {
        failureModel.repairCondenser();
    }

    @Override
    public void repairTurbine() throws CannotRepairException {
        failureModel.repairTurbine();
    }

    @Override
    public Percentage controlRodPosition() {
        return failureModel.controlRodPosition();
    }

    @Override
    public Pressure reactorPressure() {
        return failureModel.reactorPressure();
    }

    @Override
    public Temperature reactorTemperature() {
        return failureModel.reactorTemperature();
    }

    @Override
    public Percentage reactorWaterLevel() {
        return failureModel.reactorWaterLevel();
    }
    
    @Override
    public Percentage reactorWear() { 
        return failureModel.reactorWear();
    }

    @Override
    public Energy energyGenerated() {
        return failureModel.energyGenerated();
    }
    
    @Override
    public Percentage condenserToReactorWear() {
        return failureModel.condenserToReactorWear();
    }
    
    @Override
    public Percentage heatsinkToCondenserWear() {
        return failureModel.heatsinkToCondenserWear();
    }

    @Override
    public void setReactorToTurbine(boolean open) {
        failureModel.setReactorToTurbine(open);
    }

    @Override
    public boolean getReactorToTurbine() {
        return failureModel.getReactorToTurbine();
    }
    
    @Override
    public Percentage turbineWear() {
        return failureModel.turbineWear();
    }

    @Override
    public Temperature condenserTemperature() {
        return failureModel.condenserTemperature();
    }

    @Override
    public Pressure condenserPressure() {
        return failureModel.condenserPressure();
    }

    @Override
    public Percentage condenserWaterLevel() {
        return failureModel.condenserWaterLevel();
    }
    
    @Override
    public Percentage condenserWear() {
        return failureModel.condenserWear();
    }

    @Override
    public Percentage reactorMinimumWaterLevel() {
        return failureModel.reactorMinimumWaterLevel();
    }

    @Override
    public void failCondenser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void wearReactor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void step(int steps) throws GameOverException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean turbineHasFailed() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<FailableComponent> components() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
