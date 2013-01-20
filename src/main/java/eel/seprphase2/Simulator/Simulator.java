/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.FailureModel.CannotControlException;
import eel.seprphase2.FailureModel.FailureModel;
import eel.seprphase2.Persistence.GameState;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;

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
        GameState gameState = new GameState();
        gameState.failureModel = failureModel;
        gameState.userName = userName;
        Persistence p = new Persistence();

        String r = p.serialize(gameState);
        eel.seprphase2.Persistence.Persistence.saveGameState(userName, r);
    }

    @Override
    public void loadGame(int gameNumber) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String[] listGames() {
        return eel.seprphase2.Persistence.Persistence.getSaveGames(userName);
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

    public void repairPump(int pumpNumber) throws KeyNotFoundException {
        failureModel.repairPump(pumpNumber);
    }

    public void repairCondenser() {
        failureModel.repairCondenser();
    }

    public void repairTurbine() {
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
