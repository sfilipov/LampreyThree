/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Yazidi
 */
public class PhysicalModel implements PlantController, PlantStatus {

    @JsonProperty
    public Reactor reactor = new Reactor();
    @JsonProperty
    public Turbine turbine = new Turbine();
    @JsonProperty
    private Energy energyGenerated = joules(0);
    @JsonProperty
    private Connection reactorToTurbine;
    
    public PhysicalModel() {
        reactorToTurbine = new Connection(reactor.outputPort(), turbine.inputPort(), 0.04);
    }
    
    public void step(int steps) {
        for (int i = 0; i < steps; i++) {
            reactor.step();
            turbine.step();
            energyGenerated = joules(energyGenerated.inJoules() + turbine.outputPower());
            reactorToTurbine.step();
        }
    }

    @Override
    public void moveControlRods(Percentage percent) {
        reactor.moveControlRods(percent);
    }

    @Override
    public Temperature reactorTemperature() {
        return reactor.temperature();
    }

    @Override
    public Energy energyGenerated() {
        return energyGenerated;
    }

    @Override
    public Percentage controlRodPosition() {
        return reactor.controlRodPosition();
    }

    @Override
    public Pressure reactorPressure() {
        return reactor.pressure();
    }

    @Override
    public Percentage reactorWaterLevel() {
        return reactor.waterLevel();
    }
}
