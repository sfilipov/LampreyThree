package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import eel.seprphase2.GameOverException;
import eel.seprphase2.Utilities.Energy;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static eel.seprphase2.Utilities.Units.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Marius
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
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
    private Pump heatsinkToCondenser;
    @JsonProperty
    private String username;
    @JsonProperty
    private HashMap<Integer, Pump> allPumps;
    @JsonProperty
    private HashMap<Integer, Connection> allConnections;
    @JsonProperty
    private HeatSink heatSink;

    /**
     *
     */
    public PhysicalModel() {

        heatSink = new HeatSink();

        allPumps = new HashMap<Integer, Pump>();
        allConnections = new HashMap<Integer, Connection>();

        reactorToTurbine = new Connection(reactor.outputPort(), turbine.inputPort(), 0.05);
        turbineToCondenser = new Connection(turbine.outputPort(), condenser.inputPort(), 0.05);


        condenserToReactor = new Pump(condenser.outputPort(), reactor.inputPort());
        heatsinkToCondenser = new Pump(heatSink.outputPort(), condenser.coolantInputPort());


        allConnections.put(1, reactorToTurbine);
        allConnections.put(2, turbineToCondenser);

        allPumps.put(1, condenserToReactor);
        allPumps.put(2, heatsinkToCondenser);

    }

    @Override
    public String[] listFailedComponents() {
        ArrayList<String> out = new ArrayList<String>();

        /*
         * Iterate through all pumps to get their IDs
         */
        Iterator pumpIterator = allPumps.entrySet().iterator();
        while (pumpIterator.hasNext()) {
            Map.Entry pump = (Map.Entry)pumpIterator.next();

            if (((Pump)pump.getValue()).hasFailed()) {
                out.add("Pump " + pump.getKey());
            }
        }

        /*
         * Check if reactor failed
         */
        if (reactor.hasFailed()) {
            out.add("Reactor");
        }

        /*
         * Check if turbine failed
         */
        if (turbine.hasFailed()) {
            out.add("Turbine");
        }

        /*
         * Check if condenser failed
         */
        if (condenser.hasFailed()) {
            out.add("Condenser");
        }

        return out.toArray(new String[out.size()]);

    }

    /**
     *
     * @param steps
     */
    @Override
    public void step(int steps) throws GameOverException {
        for (int i = 0; i < steps; i++) {
            reactor.step();
            turbine.step();
            condenser.step();
            energyGenerated = joules(energyGenerated.inJoules() + turbine.outputPower());
            reactorToTurbine.step();
            turbineToCondenser.step();
            condenserToReactor.step();
            heatsinkToCondenser.step();

        }
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

    public Mass reactorMinimumWaterMass() {
        return reactor.minimumWaterMass();
    }

    public Mass reactorMaximumWaterMass() {
        return reactor.maximumWaterMass();
    }

    @Override
    public Percentage reactorMinimumWaterLevel() {
        return reactor.minimumWaterLevel();
    }

    @Override
    public void failReactor() {
        reactor.fail();
    }

    @Override
    public void failCondenser() {
        condenser.fail();
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
    public void setReactorToTurbine(boolean open) {
        reactorToTurbine.setOpen(open);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean getReactorToTurbine() {
        return reactorToTurbine.getOpen();
    }

    @Override
    public ArrayList<FailableComponent> components() {
        ArrayList<FailableComponent> c = new ArrayList<FailableComponent>();
        c.add(0, turbine);
        c.add(1, reactor);
        c.add(2, condenser);
        c.add(3, condenserToReactor);
        c.add(4, heatsinkToCondenser);
        return c;
    }

    @Override
    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException {
        if (allConnections.containsKey(valveNumber)) {
            allConnections.get(valveNumber).setOpen(isOpen);
        } else {
            throw new KeyNotFoundException("Valve " + valveNumber + " does not exist");
        }
    }

    @Override
    public void changePumpState(int pumpNumber, boolean isPumping) throws CannotControlException, KeyNotFoundException {
        if (!allPumps.containsKey(pumpNumber)) {
            throw new KeyNotFoundException("Pump " + pumpNumber + " does not exist");
        }

        if (allPumps.get(pumpNumber).hasFailed()) {
            throw new CannotControlException("Pump " + pumpNumber + " is failed");
        }

        allPumps.get(pumpNumber).setStatus(isPumping);
    }

    @Override
    public void repairPump(int pumpNumber) throws KeyNotFoundException, CannotRepairException {
        if (allPumps.containsKey(pumpNumber)) {
            allPumps.get(pumpNumber).repair();


            //These shouldn't need to be changed
            //allPumps.get(pumpNumber).setStatus(true);
            //allPumps.get(pumpNumber).setCapacity(kilograms(3));
            //allPumps.get(pumpNumber).stepWear(new Percentage(0));

        } else {
            throw new KeyNotFoundException("Pump " + pumpNumber + " does not exist");
        }
    }

    @Override
    public void repairCondenser() throws CannotRepairException {
        condenser.repair();
    }

    @Override
    public void repairTurbine() throws CannotRepairException {
        turbine.repair();
    }

    @Override
    public Temperature condenserTemperature() {
        return condenser.getTemperature();
    }

    @Override
    public Pressure condenserPressure() {
        return condenser.getPressure();
    }

    @Override
    public Percentage condenserWaterLevel() {
        return condenser.getWaterLevel();
    }

    @Override
    public boolean turbineHasFailed() {
        return turbine.hasFailed();
    }
    
    public boolean getPumpStatus(int pumpNumber) {
        return allPumps.get(pumpNumber).getStatus();
    }
}
