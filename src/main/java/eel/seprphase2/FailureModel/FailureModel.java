/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.FailureModel;

import eel.seprphase2.Simulator.PhysicalModel;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Yazidi
 */
public class FailureModel {
    
    PhysicalModel physicalModel = new PhysicalModel();
    private Random failChance = new Random();
    private static int componentNumber = 2;
    ArrayList<FailableComponent> components;
    
    public FailureModel(PhysicalModel physicalModel) {
        this.physicalModel = physicalModel;
        this.components = physicalModel.components;
    }    
    
    public void step() {
        physicalModel.step(1);
        failStateCheck();
       }
    
    public void failStateCheck() {
        int failValue = failChance.nextInt(10000);
        int componentsFailChance = 0;
        for (int i = 0; i < componentNumber; i++) {
            componentsFailChance += components.get(i).getWear().points()/componentNumber;
            if (componentsFailChance > failValue) {
                components.get(i).setFailureState(FailureState.Failed);
            }
        }
    }
}
