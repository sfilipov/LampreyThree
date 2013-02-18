/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.DynSimulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Simulator.Condenser;
import eel.seprphase2.Simulator.HeatSink;
import eel.seprphase2.Simulator.Pump;
import eel.seprphase2.Simulator.Reactor;
import eel.seprphase2.Simulator.Turbine;
import eel.seprphase2.Simulator.Valve;
import java.util.HashMap;

/**
 *
 * @author will
 */
public class PlantModel {

    @JsonProperty
    private Reactor reactor;
    @JsonProperty
    private Turbine turbine;
    @JsonProperty
    private Condenser condenser;
    @JsonProperty
    private Valve reactorToTurbine;
    @JsonProperty
    private Valve bypassValve;
    @JsonProperty
    private Pump condenserToReactor;
    @JsonProperty
    private Pump heatsinkToCondenser;
    @JsonProperty
    private HeatSink heatSink;
    @JsonProperty
    private Junction splitAfterReactor;
    @JsonProperty
    private Junction joinBeforeCondenser;
    @JsonProperty
    private HashMap<Integer, Pump> allPumps;
    @JsonProperty
    private HashMap<Integer, Valve> allValves;  

    public PlantModel() {
        instantiateComponents();
        setupLists();
        setupComponentReferences();
    }

    private void instantiateComponents() {
        reactor = new Reactor();
        turbine = new Turbine();
        condenser = new Condenser();
        reactorToTurbine = new Valve();
        bypassValve = new Valve();
        condenserToReactor = new Pump();
        heatsinkToCondenser = new Pump();
        heatSink = new HeatSink();
        splitAfterReactor = new Junction();
        joinBeforeCondenser = new Junction();
    }
    
    
    private void setupLists() {
        allPumps = new HashMap<Integer, Pump>();
        allPumps.put(1, condenserToReactor);
        allPumps.put(2, heatsinkToCondenser);
        
        allValves = new HashMap<Integer, Valve>();
        allValves.put(1, reactorToTurbine);
        allValves.put(2, bypassValve);
    }

    private void setupComponentReferences() {
        setupInputOutputReferences(reactor, splitAfterReactor);
        // Path through turbine
        setupInputOutputReferences(splitAfterReactor, reactorToTurbine);
        setupInputOutputReferences(reactorToTurbine, turbine);
        setupInputOutputReferences(turbine, joinBeforeCondenser);
        // Path through bypass valve
        setupInputOutputReferences(splitAfterReactor, bypassValve);
        setupInputOutputReferences(bypassValve, joinBeforeCondenser);
        // Condenser through to reactor
        setupInputOutputReferences(joinBeforeCondenser, condenser);
        setupInputOutputReferences(condenser, condenserToReactor);
        setupInputOutputReferences(condenserToReactor, reactor);
    }

    /**
     * Takes two FlowThroughComponents and creates the input/output references between them.
     *
     * @param from FlowThroughComponent that flow is coming out of.
     * @param to   FlowThroughComponent that flow is moving into.
     */
    private void setupInputOutputReferences(FlowThroughComponent from, FlowThroughComponent to) {
        if (from instanceof Junction) {
            ((Junction)from).addOutput(to);
        } else {
            from.output = to;
        }
        if (to instanceof Junction) {
            ((Junction)to).addInput(from);
        } else {
            to.input = from;
        }
    }

    public Reactor reactor() {
        return reactor;
    }

    public Turbine turbine() {
        return turbine;
    }

    public Condenser condenser() {
        return condenser;
    }

    public HeatSink heatSink() {
        return heatSink;
    }
    
    public HashMap<Integer, Pump> pumps() {
        return allPumps;
    }
    
    public HashMap<Integer, Valve> valves() {
        return allValves;
    }

}
