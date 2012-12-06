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
    public Random failChance = new Random();

    public FailureModel(PhysicalModel physicalModel) {
        this.physicalModel = physicalModel;
    }

    public void step() {
        physicalModel.step(1);
        failStateCheck();

    }

    public void failStateCheck() {

        ArrayList<FailableComponent> components = new ArrayList<FailableComponent>();
        components.add(physicalModel.reactor);
        if (physicalModel.turbine.getWear().ratio() >= physicalModel.reactor.getWear().ratio()) {
            components.add(0, physicalModel.turbine);
        } else {
            components.add(1, physicalModel.turbine);
        }

        for (int i = 0; i <= 1; i++) {
            if (components.get(i).getFailureState() != FailureState.Failed) {
                components.get(i).setFailureState(setFailState(components.get(i)));
                if (components.get(i).getFailureState() == FailureState.Failed) {
                    i = 1;
                }
            }
        }

    }

    public FailureState setFailState(FailableComponent component) {

        if ((failChance.nextInt(100) * component.getWear().ratio()) < 20) {
            return FailureState.Failed;
        } else {
            return FailureState.Normal;
        }
    }
}
