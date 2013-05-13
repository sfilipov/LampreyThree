package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lamprey.seprphase3.Persistence.SaveGame;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import lamprey.seprphase3.DynSimulator.FluidFlowController;
import lamprey.seprphase3.DynSimulator.PlantModel;

/**
 *
 * @author David
 */
public class Simulator implements PlantController, PlantStatus, GameManager {

    private PlantModel plant;
    private PhysicalModel physicalModel;
    private FailureModel failureModel;
    private FluidFlowController fluidFlowController;
    private String userName;

    public Simulator() {
        initGame();
        userName = "";
    }
    
    public void initGame() {
        plant = new PlantModel();
        physicalModel = new PhysicalModel(plant);
        fluidFlowController = new FluidFlowController(plant);
        failureModel = new FailureModel(fluidFlowController, physicalModel);
    }

    @Override
    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Override
    public void saveGame() throws IOException, FileNotFoundException {
        SaveGame saveGame = new SaveGame(plant, userName);
        saveGame.save();
    }

    @Override
    public void loadGame() throws IOException, FileNotFoundException, ClassNotFoundException {
        SaveGame saveGame = SaveGame.load();
        this.plant = saveGame.getPlantModel();
        this.userName = saveGame.getUserName();
    }

    @Override
    public String[] listFailedComponents() {
        return failureModel.listFailedComponents();
    }

    @Override
    public void step(double seconds) throws GameOverException {
        try {
            failureModel.step(seconds);
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
    public void flipValveState(int valveNumber) throws KeyNotFoundException {
        failureModel.flipValveState(valveNumber);
    }

    @Override
    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException {
        failureModel.changePumpState(pumpNumber, isPumping);
    }

    @Override
    public void flipPumpState(int pumpNumber) throws CannotControlException, KeyNotFoundException {
        failureModel.flipPumpState(pumpNumber);
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
    public String wornComponent() {
         return failureModel.wornComponent();
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
    public void setWornComponent(FailableComponent currentWornComponent) {
        failureModel.setWornComponent(currentWornComponent);
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
    public Boolean getPumpState(int pumpNumber) throws KeyNotFoundException {
        return failureModel.getPumpState(pumpNumber);
    }
    
    @Override
    public Boolean getValveState(int valveNumber) throws KeyNotFoundException {
        return failureModel.getValveState(valveNumber);
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
    public int getSoftwareFailureTimeRemaining() {
        return failureModel.getSoftwareFailureTimeRemaining();
    }
    
    @Override
    public Percentage getPumpWear(int pumpNumber)throws KeyNotFoundException {
        return failureModel.getPumpWear(pumpNumber);
    }
    
    @Override
    public Percentage turbineWear() {
        return failureModel.turbineWear();
    }
    
    @Override
    public double getOutputPower() {
        return failureModel.getOutputPower();
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
    public Temperature reactorMaximumTemperature() {
        return failureModel.reactorMaximumTemperature();
    }

    @Override
    public void failCondenser() {
        failureModel.failCondenser();
    }

    @Override
    public void wearReactor() {
        failureModel.wearReactor();
    }

    @Override
    public boolean turbineHasFailed() {
        return failureModel.turbineHasFailed();
    }

    @Override
    public ArrayList<FailableComponent> failableComponents() {
        return failureModel.failableComponents();
    }

    @Override
    public void setSoftwareFailureTimeRemaining(int failureTime) {
        failureModel.setSoftwareFailureTimeRemaining(failureTime);
    }

    @Override
    public void wearCondenser() {
        failureModel.wearCondenser();
    }

    @Override
    public Pressure reactorMaximumPressure() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getUsername() {
        return this.userName;
    }
}
