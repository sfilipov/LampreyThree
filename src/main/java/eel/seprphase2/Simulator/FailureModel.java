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
 * Hardware failures.
 *
 * The FailureModel is responsible for all the hardware failures in the game.* 
 *
 * Hardware failures are determined by inspecting the PhysicalModel to determine the status, wear, and operating
 * conditions of the various components, and then instructing the PhysicalModel to mark certain components as having
 * failed.
 * The main checks the class makes is checking if any components have failed(due to their wear reaching), if so setting them to failed and adding
 * them to an Array of failed components. It then deals with random wear of components, finally it checks if the reactor and condenser
 * are within their limits(Pressure, Temperature and Water Level for the Reactor, and pressure for the Condenser).
 * Finally it initiates any safety procedures if the turbine has failed.
 * 
 *
 * Random wear of a component constrained to one per timestep, which is enabled by the delegation of the step() method through the
 * FailureModel.
 *
 * @author Marius Dumitrescu
 */
public class FailureModel implements PlantController, PlantStatus {

    PlantController controller;
    PlantStatus status;
    private Random failChance = new Random();
    private final Pressure condenserMaxPressure = new Pressure(30662500);
    private final int lengthOfSoftwareFailures = 25;
    private final Percentage wearToDamagedComponents = new Percentage(20);

    private FailureModel() {
    }

    public FailureModel(PlantController plantController,
                        PlantStatus plantStatus) {
        this.controller = plantController;
        this.status = plantStatus;
    }

    /**
     * Step the PhysicalModel and determine any failures.
     *
     * Also implements reactor safety rules.
     *
     */
    @Override
    public void step(double seconds) throws GameOverException {
        controller.step(seconds);
        failStateCheck();
        randomWearCheck();
        failStateCheck(); // Requires a second check, because randomWearCheck may have cause a component to reach 100% wear
        checkReactorWaterLevel();
        checkReactorPressure();
        checkReactorTemperature();
        checkCondenserPressure();
        failStateCheck(); // Requires a third check, because randomWearCheck may have cause a component to reach 100% wear
        checkTurbineFailure();
        checkForSoftwareFailure();
    }

    /**
     * Determine if a FailableComponent has failed, which occurs when its wear is 100, if it has, its state is set to failed
     *
     */
    public void failStateCheck() {
             
        for (FailableComponent component: status.failableComponents()) {  
            if (component.wear().toString().equals("100%")) {
                component.fail();               
            }
        }
    }
     /**
     * Determines if random damage has occurred to any of the functioning(non-failed) components.
     * If damage has occurred to multiple components, only one of these is selected(randomly) to receive the wear
     * the wear is then added to the component
     */
    public void randomWearCheck(){
        ArrayList<FailableComponent> wornComponents = new ArrayList<FailableComponent>();        
        int componentFailChance = 0;   
        int faults = 0;
        
        //This section checks if any components have been worn randomly, if so they are added to the wornComponents ArrayList
        for (FailableComponent component: status.failableComponents()) {
            if(component.wear().toString().equals("100%") || component instanceof Reactor ) {
            }                       
            else{
                componentFailChance = failChance.nextInt(1000);
                if(componentFailChance<=1) {  // As doing nextInt(1000). Less than 50 is 5% chance, using 1000 to give more options of failure chance.
                    wornComponents.add(component);
		    faults++;
                }                
            }
        }
        //If more than one component has been selected to be worn, only of these is selected randomly from the array
        if(faults > 0) {
            int selection = failChance.nextInt(faults);
            FailableComponent failedComponent = wornComponents.get(selection);            
            failedComponent.addWear(wearToDamagedComponents );
            // The current wornComponent is set to the one that has just received wear
            setWornComponent(failedComponent);
	}
        else {
            //If no components received wear this step, the current wornComponent is set to null
            setWornComponent(null);
        }
    }  
    /*
     * This method checks if there is a software failure, if there is it sets softwareFailuretimeRemaining to a number,
     * this number is decremented each time step
     * It cannot run if softwareFailureTimeRemaining is above 0, as this means there is an existing software failure, and we
     * do not want two happening at once, making one massive failure.
     */
    
    public void checkForSoftwareFailure() {
        if(status.getSoftwareFailureTimeRemaining() == 0) {
            //Makes a new random number from 0-1000
            int chanceOfFailure = failChance.nextInt(1000);
            //Check if it is above 990 (1% chance to fail per time step)
            if (chanceOfFailure>990) {
                controller.setSoftwareFailureTimeRemaining(lengthOfSoftwareFailures);
            }
        }
    }
        
   
    @Override
    public String[] listFailedComponents() {
        return status.listFailedComponents();
    }

    @Override
    public void moveControlRods(Percentage extracted) {
        controller.moveControlRods(extracted);
    }

    @Override
    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException {
        controller.changeValveState(valveNumber, isOpen);
    }

    @Override
    public void flipValveState(int valveNumber) throws KeyNotFoundException {
        controller.flipValveState(valveNumber);
    }
    
    @Override
    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException {
        controller.changePumpState(pumpNumber, isPumping);
    }

    @Override
    public void flipPumpState(int pumpNumber) throws CannotControlException, KeyNotFoundException {
        controller.flipPumpState(pumpNumber);
    }
    
    @Override
    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException {
        controller.repairPump(pumpNumber);
    }

    @Override
    public void repairCondenser() throws CannotRepairException {
        controller.repairCondenser();
    }

    @Override
    public void repairTurbine() throws CannotRepairException {
        controller.repairTurbine();
    }

    @Override
    public Percentage controlRodPosition() {
        return status.controlRodPosition();
    }

    @Override
    public Pressure reactorPressure() {
        return status.reactorPressure();
    }

    @Override
    public Temperature reactorTemperature() {
        return status.reactorTemperature();
    }

    @Override
    public Percentage reactorWaterLevel() {
        return status.reactorWaterLevel();
    }

    @Override
    public Energy energyGenerated() {
        return status.energyGenerated();
    }
    
    @Override
    public Percentage turbineWear() {
        return status.turbineWear();
    }
    
    @Override
    public int getSoftwareFailureTimeRemaining() {
        return status.getSoftwareFailureTimeRemaining();
    }
    
    @Override 
    public Percentage reactorWear() {
        return status.reactorWear();
    }
    
    @Override
    public double getOutputPower() {
        return status.getOutputPower();
    }
    
    @Override
    public Percentage getPumpWear(int pumpNumber)throws KeyNotFoundException { 
        return status.getPumpWear(pumpNumber);
    }    
   
    @Override
    public Temperature condenserTemperature() {
        return status.condenserTemperature();
    }

    @Override
    public Pressure condenserPressure() {
        return status.condenserPressure();
    }

    @Override
    public Percentage condenserWaterLevel() {
        return status.condenserWaterLevel();
    }
    
    @Override    
    public Percentage condenserWear() { 
        return status.condenserWear();
    }
    
    @Override
    public Boolean getPumpState(int pumpNumber) throws KeyNotFoundException  { 
        return status.getPumpState(pumpNumber);
    }
    
    @Override
    public Boolean getValveState(int valveNumber) throws KeyNotFoundException  { 
        return status.getValveState(valveNumber);
    }

    @Override
    public Percentage reactorMinimumWaterLevel() {
        return status.reactorMinimumWaterLevel();
    }
    
    @Override
    public Pressure reactorMaximumPressure() {
        return status.reactorMaximumPressure();
    }
    
    @Override
    public Temperature reactorMaximumTemperature() {
        return status.reactorMaximumTemperature();
    }
    
    @Override
    public String wornComponent() { 
        return status.wornComponent();
    }

    @Override
    public void failCondenser() {
        controller.failCondenser();
    }
    
    @Override
    public void setSoftwareFailureTimeRemaining(int failureTime) {
        controller.setSoftwareFailureTimeRemaining(failureTime);
    }
    
    @Override
    public void setWornComponent(FailableComponent currentWornComponent) {
        controller.setWornComponent(currentWornComponent);
    }

    @Override
    public void wearReactor() {        
        controller.wearReactor();
    }
    @Override
    public void wearCondenser() {
        controller.wearCondenser();
    }

    @Override
    public boolean turbineHasFailed() {
        return status.turbineHasFailed();
    }

    @Override
    public ArrayList<FailableComponent> failableComponents() {
        return status.failableComponents();
    }
    
    /**
     * This method checks the reactor is not above its minimumWaterLevel, if it is
     * it receives wear     *
     */
    private void checkReactorWaterLevel() {
        if (status.reactorWaterLevel().points() < status.reactorMinimumWaterLevel().points()) {
                controller.wearReactor();  
        }
    }
    /**
     * This method checks the reactor is not above its MaximumTemperature, if it is
     * it receives wear     *
     */
    private void checkReactorTemperature() {
        if (status.reactorTemperature().greaterThan(status.reactorMaximumTemperature())) {
                controller.wearReactor();  
        }
    }
    /**
     * This method checks the reactor is not below its MaximumPressure, if it is
     * it receives wear     *
     */

    private void checkReactorPressure() {
        if (status.reactorPressure().greaterThan(status.reactorMaximumPressure())) {
                controller.wearReactor();  
        }
    }

     /**
     * This method checks the condenser is not above its maximumPressure, if it is
     * it receives wear     *
     */
    private void checkCondenserPressure() {
        if (status.condenserPressure().greaterThan(condenserMaxPressure)) {
            controller.wearCondenser();
        }
    }
    /**
     * This method checks if the turbine has failed, if it has the controlRods are moved to position 0
     * as set by the safety requirements
     */
    private void checkTurbineFailure() {
        if (status.turbineHasFailed()) {
            controller.moveControlRods(percent(0));
        }
    }

    @Override
    public String getUsername() {
        return status.getUsername();
    }
}
