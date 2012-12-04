/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Yazidi
 */
public class PhysicalModel {

    private Reactor reactor = new Reactor();
    private Turbine turbine = new Turbine();
    private Energy energyGenerated = joules(0);
    
    public void step(int steps) {
        for (int i = 0; i < steps; i++) {
            reactor.step();
            turbine.setFlowVelocity(reactor.outputFlowVelocity());
            turbine.step();
            energyGenerated = joules(energyGenerated.inJoules() + turbine.outputPower());
        }
    }

    public void moveControlRods(Percentage percent) {
        reactor.moveControlRods(percent);
    }

    public Temperature reactorTemperature() {
        return reactor.temperature();
    }

    public Energy outputEnergy() {
        return energyGenerated;
    }

}
