package eel.seprphase2.FailureModel;

import eel.seprphase2.Simulator.PhysicalModel;
import java.util.ArrayList;
import java.util.Random;

/**
 * The FailureModel is the class responsible for injecting both software and hardware failures into the reactor 
 * components when a command is executed. It will only induce at most 1 software and 1 hardware failure per step.
 * @author Marius Dumitrescu
 */
public class FailureModel {
    
    PhysicalModel physicalModel = new PhysicalModel();
    private Random failChance = new Random();
    ArrayList<FailableComponent> components;
    
    /**
     * Constructor for the FailureModel. Uses the physicalModel (model of the nuclear power plant) at a parameter
     * @param physicalModel The current physical model for the game. This is what the FailureModel will affect.
     */
    public FailureModel(PhysicalModel physicalModel) {
        this.physicalModel = physicalModel;
        this.components = physicalModel.components;
    }    
    
    /**
     * Step through the failure model. First step the PhysicalModel, then run our check to see if we can fail any 
     * components
     */
    public void step() {
        physicalModel.step(1);
        failStateCheck();
       }
    
    /**
     * Check to see if we can fail any components. We use an accumulator which adds the component's current wear points.
     * If the total wear level is over a random integer, we decide to fail the current component then break. 
     * Only need to fail one Hardware component. 
     * TODO: future teams to add software fail routine.
     */
    public void failStateCheck() {
        int failValue = failChance.nextInt(10000);
        int componentsFailChance = 0;
        for (int i = 0; i < components.size(); i++) {
            componentsFailChance += components.get(i).getWear().points()/components.size();
            if (componentsFailChance > failValue) {
                components.get(i).setFailureState(FailureState.Failed);
            }
            break; //We only want to induce one hardware failure! Break here.
        }
    }
}
