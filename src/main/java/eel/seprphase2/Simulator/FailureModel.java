package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Manages software and hardware failures.
 *
 * The FailureModel is responsible for inducing software and hardware failures in the plant.
 *
 * Software failures are currently unimplemented; however, as all calls to the PhysicalModel are delegated through the
 * FailureModel, it is easy to alter this delegation in arbitrary ways to simulate control and/or reporting failures.
 *
 * Hardware failures are determined by inspecting the PhysicalModel to determine the status, wear, and operating
 * conditions of the various components, and then instructing the PhysicalModel to mark certain components as having
 * failed.
 *
 * Failures are constrained to one per timestep, which is enabled by the delegation of the step() method through the
 * FailureModel.
 *
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

    private FailureModel() {
    }

    public FailureModel(PhysicalModel physicalModel) {
        this.physicalModel = physicalModel;
    }

    /**
     * Step the PhysicalModel and determine any failures.
     *
     * Also implements reactor safety rules.
     *
     */
    public void step() {
        physicalModel.step(1);
        failStateCheck();
        checkReactorWaterLevel();
        checkCondenserPressure();
        checkTurbineFailure();
    }

    /**
     * Determine failures
     * 
     */
    public void failStateCheck() {
        ArrayList<FailableComponent> components = physicalModel.components();
        int failValue = failChance.nextInt(5000);  //A component that is 100% wear will have a 1 in 50 chance of failing
        int componentsFailChance = 0;
        for (int i = 0; i < components.size(); i++) {
            componentsFailChance += components.get(i).wear().points() / components.size();
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
    public void repairCondenser() throws CannotRepairException {
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
        if (physicalModel.reactorWaterLevel().points() < physicalModel.reactorMinimumWaterLevel().points()) {
            numberOfTimesWaterLevelIsTooLow += 1;
            if (numberOfTimesWaterLevelIsTooLow > reactorOverheatThreshold) {
                physicalModel.failReactor();
            }
        } else {
            numberOfTimesWaterLevelIsTooLow = 0;
        }
    }

    private void checkCondenserPressure() {
        if (physicalModel.condenserPressure().greaterThan(condenserMaxPressure)) {
            physicalModel.failCondenser();
        }
    }

    private void checkTurbineFailure() {
        if (physicalModel.turbineHasFailed()) {
            physicalModel.moveControlRods(percent(0));
        }
    }
}
