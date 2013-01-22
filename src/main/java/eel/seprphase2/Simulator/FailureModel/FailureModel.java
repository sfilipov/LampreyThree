package eel.seprphase2.Simulator.FailureModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PhysicalModel.PhysicalModel;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import java.util.ArrayList;
import java.util.Random;
import static eel.seprphase2.Utilities.Units.*;

/**
 * The FailureModel is the class responsible for injecting both software and hardware failures into the reactor 
 * components when a command is executed. It will only induce at most 1 software and 1 hardware failure per step.
 * @author Marius Dumitrescu
 */
public class FailureModel implements PlantController, PlantStatus {
    
    @JsonProperty
    PhysicalModel physicalModel;
    private Random failChance = new Random();
    @JsonProperty
    private int numberOfTimesWaterLevelIsTooLow;
    private final int reactorOverheatThreshold = 8;
    private final Pressure condenserMaxPressure = new Pressure(30662500);

    private FailureModel() {}
    
    /**
     * Constructor for the FailureModel. Uses the physicalModel (model of the nuclear power plant) at a parameter
     * @param physicalModel The current physical model for the game. This is what the FailureModel will affect.
     */
    public FailureModel(PhysicalModel physicalModel) {
        this.physicalModel = physicalModel;
    }    
    
    /**
     * Step through the failure model. First step the PhysicalModel, then run our check to see if we can fail any 
     * components
     */
    public void step() {
        physicalModel.step(1);
        failStateCheck();
        checkReactorWaterLevel();
        checkCondenserPressure();
        checkTurbineFailure();
    }
    
    
    
    /**
     * Check to see if we can fail any components. We use an accumulator which adds the component's current wear points.
     * If the total wear level is over a random integer, we decide to fail the current component then break. 
     * Only need to fail one Hardware component. 
     * TODO: future teams to add software fail routine.
     */
    public void failStateCheck() {
        ArrayList<FailableComponent> components = physicalModel.components();
        int failValue = failChance.nextInt(5000);  //A component that is 100% wear will have a 1 in 50 chance of failing
        int componentsFailChance = 0;
        for (int i = 0; i < components.size(); i++) {
            componentsFailChance += components.get(i).getWear().points()/components.size();
            if (componentsFailChance > failValue) {
                components.get(i).fail();
                 break; //We only want to induce one hardware failure! Break here.
            }
           
        }
    }
    
    @Override
    public String[] listFailedComponents() {
        return physicalModel.listFailedComponents();
    }

    @Override
    public void moveControlRods(Percentage extracted) {
        physicalModel.moveControlRods(extracted);
    }

    @Override
    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException {
        physicalModel.changeValveState(valveNumber, isOpen);
    }

    @Override
    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException {
        physicalModel.changePumpState(pumpNumber, isPumping);
    }

    @Override
    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException {
        physicalModel.repairPump(pumpNumber);
    }

    @Override
    public void repairCondenser() throws CannotRepairException{
        physicalModel.repairCondenser();
    }

    @Override
    public void repairTurbine() throws CannotRepairException {
        physicalModel.repairTurbine();
    }

    @Override
    public Percentage controlRodPosition() {
        return physicalModel.controlRodPosition();
    }

    @Override
    public Pressure reactorPressure() {
        return physicalModel.reactorPressure();
    }

    @Override
    public Temperature reactorTemperature() {
        return physicalModel.reactorTemperature();
    }

    @Override
    public Percentage reactorWaterLevel() {
        return physicalModel.reactorWaterLevel();
    }

    @Override
    public Energy energyGenerated() {
        return physicalModel.energyGenerated();
    }

    @Override
    public void setReactorToTurbine(boolean open) {
        physicalModel.setReactorToTurbine(open);
    }

    @Override
    public boolean getReactorToTurbine() {
        return physicalModel.getReactorToTurbine();
    }

    @Override
    public Temperature condenserTemperature() {
        return physicalModel.condenserTemperature();
    }

    @Override
    public Pressure condenserPressure() {
        return physicalModel.condenserPressure();
    }

    @Override
    public Percentage condenserWaterLevel() {
        return physicalModel.condenserWaterLevel();
    }

    @Override
    public Percentage reactorMinimumWaterLevel() {
        return physicalModel.reactorMinimumWaterLevel();
    }

    private void checkReactorWaterLevel() {
        if(physicalModel.reactorWaterLevel().points()<physicalModel.reactorMinimumWaterLevel().points())
        {
            numberOfTimesWaterLevelIsTooLow +=1;
            if(numberOfTimesWaterLevelIsTooLow>reactorOverheatThreshold)
            {
                physicalModel.failReactor();
            }
        }
        else
        {
            numberOfTimesWaterLevelIsTooLow = 0;
        }
    }

    private void checkCondenserPressure() {
        if(physicalModel.condenserPressure().greaterThan(condenserMaxPressure))
        {
            physicalModel.failCondenser();
        }
    }
    
    private void checkTurbineFailure() {
        if (physicalModel.turbineHasFailed()) {
            physicalModel.moveControlRods(percent(0));
        }
    }
    
}
