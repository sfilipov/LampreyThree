/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.FailureModel.FailableComponent;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;
import java.util.ArrayList;

/**
 *
 * @author Yazidi
 */
public class PhysicalModel implements PlantController, PlantStatus {

    @JsonProperty
    private Reactor reactor = new Reactor();
    @JsonProperty
    private Turbine turbine = new Turbine();
    @JsonProperty
    private Condenser condenser = new Condenser();
    @JsonProperty
    private Energy energyGenerated = joules(0);
    @JsonProperty
    private Connection reactorToTurbine;
    @JsonProperty
    private Connection turbineToCondenser;
    @JsonProperty
    private Pump condenserToReactor;
    @JsonProperty
    private Pump reactorToCondenser;
    @JsonProperty
    public String username;
    /**
     *
     */
    @JsonIgnore
    public ArrayList<FailableComponent> components;
    /**
     *
     */
    public PhysicalModel() {
        
        
        components = new ArrayList<FailableComponent>();
        components.add(0, turbine);
        components.add(1, reactor);
        components.add(2, condenser);
        reactorToTurbine = new Connection(reactor.outputPort(), turbine.inputPort(), 0.04);
        turbineToCondenser = new Connection(turbine.outputPort(), condenser.inputPort(), 0.04);
        condenserToReactor = new Pump(condenser.outputPort(), reactor.inputPort());
        reactorToCondenser = new Pump(reactor.outputPort(), condenser.inputPort());
        reactorToCondenser.setStatus(false);
    }
    
    /**
     *
     * @param steps
     */
    public void step(int steps) {
        for (int i = 0; i < steps; i++) {
            reactor.step();
            turbine.step();
            condenser.step();
            energyGenerated = joules(energyGenerated.inJoules() + turbine.outputPower());
            reactorToTurbine.step();
            turbineToCondenser.step();
            condenserToReactor.step();
            reactorToCondenser.step();
        }
    }
    
    /**
     *
     * @param username
     */
    @Override
    public void setUsername(String username){
        this.username = username;
    }

    /**
     *
     * @param percent
     */
    @Override
    public void moveControlRods(Percentage percent) {
        reactor.moveControlRods(percent);
    }

    /**
     *
     * @return
     */
    @Override
    public Temperature reactorTemperature() {
        return reactor.temperature();
    }

    /**
     *
     * @return
     */
    @Override
    public Energy energyGenerated() {
        return energyGenerated;
    }

    /**
     *
     * @return
     */
    @Override
    public Percentage controlRodPosition() {
        return reactor.controlRodPosition();
    }

    /**
     *
     * @return
     */
    @Override
    public Pressure reactorPressure() {
        return reactor.pressure();
    }

    /**
     *
     * @return
     */
    @Override
    public Percentage reactorWaterLevel() {
        return reactor.waterLevel();
    }
   
    /**
     *
     * @param open
     */
    @Override
    public void setReactorToTurbine(boolean open){
        reactorToTurbine.setOpen(open);
    }
    
    /**
     *
     * @return
     */
    @Override
    public boolean getReactorToTurbine(){
        return reactorToTurbine.getOpen();
    }
            
}
