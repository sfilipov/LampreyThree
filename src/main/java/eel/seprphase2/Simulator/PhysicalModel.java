package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class PhysicalModel implements PlantStatus {

    @JsonProperty
    private Reactor reactor = new Reactor();
    @JsonProperty
    private Turbine turbine = new Turbine();
    @JsonProperty
    private Condenser condenser = new Condenser();
    @JsonProperty
    private Energy energyGenerated = joules(0);
    @JsonProperty
    private Valve reactorToTurbine;
    @JsonProperty
    private Valve bypassValve;
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
    private String currentWornComponent = "";

    /**
     *
     */
    public PhysicalModel() {

        heatSink = new HeatSink();

        allPumps = new HashMap<Integer, Pump>();
        allConnections = new HashMap<Integer, Connection>();

        reactorToTurbine = new Valve();
        bypassValve = new Valve();

        condenserToReactor = new Pump();
        heatsinkToCondenser = new Pump();

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

    @Override
    public Percentage reactorWear() {
        return reactor.wear();
    }

    @Override
    public boolean getReactorToTurbine() {
        return reactorToTurbine.getOpen();
    }

    @Override
    public ArrayList<FailableComponent> failableComponents() {
        ArrayList<FailableComponent> c = new ArrayList<FailableComponent>();
        c.add(0, turbine);
        c.add(1, reactor);
        c.add(2, condenser);
        c.add(3, condenserToReactor);
        c.add(4, heatsinkToCondenser);
        return c;
    }

    @Override
    public Percentage turbineWear() {
        return turbine.wear();
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
    public Percentage condenserWear() {
        return condenser.wear();
    }

    @Override
    public String wornComponent() {
        return currentWornComponent;
    }

    @Override
    public Percentage condenserToReactorWear() {
        return condenserToReactor.wear();
    }

    @Override
    public Percentage heatsinkToCondenserWear() {
        return heatsinkToCondenser.wear();
    }

    @Override
    public boolean turbineHasFailed() {
        return turbine.hasFailed();
    }

    public boolean getPumpStatus(int pumpNumber) {
        return allPumps.get(pumpNumber).getStatus();
    }
}
