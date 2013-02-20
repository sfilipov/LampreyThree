package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
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
import lamprey.seprphase3.DynSimulator.PlantModel;

/**
 *
 * @author Marius
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class PhysicalModel implements PlantStatus {

    private PlantModel plant;

    public PhysicalModel(PlantModel plant) {
        this.plant = plant;
    }

    @Override
    public String[] listFailedComponents() {
        ArrayList<String> out = new ArrayList<String>();

        /*
         * Iterate through all pumps to get their IDs
         */
        Iterator pumpIterator = plant.pumps().entrySet().iterator();
        while (pumpIterator.hasNext()) {
            Map.Entry pump = (Map.Entry)pumpIterator.next();

            if (((Pump)pump.getValue()).hasFailed()) {
                out.add("Pump " + pump.getKey());
            }
        }

        /*
         * Check if reactor failed
         */
        if (plant.reactor().hasFailed()) {
            out.add("Reactor");
        }

        /*
         * Check if turbine failed
         */
        if (plant.turbine().hasFailed()) {
            out.add("Turbine");
        }

        /*
         * Check if condenser failed
         */
        if (plant.condenser().hasFailed()) {
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
        return plant.reactor().temperature();
    }

    public Mass reactorMinimumWaterMass() {
        return plant.reactor().minimumWaterMass();
    }

    public Mass reactorMaximumWaterMass() {
        return plant.reactor().maximumWaterMass();
    }

    @Override
    public Percentage reactorMinimumWaterLevel() {
        return plant.reactor().minimumWaterLevel();
    }

    @Override
    public void wearReactor() {
        Percentage damage = new Percentage(10);
        reactor.addWear(damage);
    }
    
    @Override
    public void wearCondenser() {
        Percentage damage = new Percentage(10);
        condenser.addWear(damage);
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
        return plant.energyGenerated();
    }

    @Override
    public Percentage controlRodPosition() {
        return plant.reactor().controlRodPosition();
    }

    @Override
    public Pressure reactorPressure() {
        return plant.reactor().pressure();
    }

    @Override
    public Percentage reactorWaterLevel() {
        return plant.reactor().waterLevel();
    }
    @Override
    public Percentage reactorWear() {
        return plant.reactor().wear();
    }

    @Override
    public Boolean getValveState(int valveID) throws KeyNotFoundException {
        if (plant.valves().containsKey(valveID)) {
            return plant.valves().get(valveID).getOpen();
        } else
        {
            throw new KeyNotFoundException("No valve with ID (" + valveID + ") exists!");
	}

    public void setWornComponent(FailableComponent wornComponent) {
        if(wornComponent == null) {            
            currentWornComponent = "";           
        }
        else {
            /*
             * Check if a Valve was worn, if so get its Key.
             */
            Iterator pumpIterator = allPumps.entrySet().iterator();
            while (pumpIterator.hasNext()) {
                Map.Entry pump = (Map.Entry)pumpIterator.next();

                if (((Pump)pump.getValue()).equals(wornComponent)) {
                    currentWornComponent = ("Pump " + pump.getKey());                   
                }
            }

            /*
             * Check if the condenser was worn
             */
            if (wornComponent instanceof Condenser) {
                currentWornComponent = ("Condenser");               
            }
            /*
             * Check if the turbine was worn
             */
            else if (wornComponent instanceof Turbine) {
                currentWornComponent = ("Turbine");               
            }
        }
        
        
    }
    
    @Override
    public Boolean getPumpState(int pumpID) throws KeyNotFoundException {
        if (plant.pumps().containsKey(pumpID)) {
            return plant.pumps().get(pumpID).getStatus();
        } else
        {
            throw new KeyNotFoundException("No pump with ID (" + pumpID + ") exists!");
        }
        
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<FailableComponent> failableComponents() {
        ArrayList<FailableComponent> c = new ArrayList<FailableComponent>();
        c.add(0, plant.turbine());
        c.add(1, plant.reactor());
        c.add(2, plant.condenser());
        c.addAll(plant.pumps().values());
        return c;
    }

    @Override
    public Percentage turbineWear() {
        return plant.turbine().wear();
}
    public void changeValveState(int valveNumber, boolean isOpen) throws KeyNotFoundException {
        if (allConnections.containsKey(valveNumber)) {
            allConnections.get(valveNumber).setOpen(isOpen);
        } else {
            throw new KeyNotFoundException("Valve " + valveNumber + " does not exist");
        }
    }

    @Override
    public Temperature condenserTemperature() {
        return plant.condenser().getTemperature();
}
    public void flipValveState(int valveNumber) throws KeyNotFoundException {
        if (allConnections.containsKey(valveNumber)) {
            boolean isOpen = allConnections.get(valveNumber).getOpen();
            allConnections.get(valveNumber).setOpen(!isOpen);
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
    public Pressure condenserPressure() {
        return plant.condenser().getPressure();
    public void flipPumpState(int pumpNumber) throws CannotControlException, KeyNotFoundException {
        if (!allPumps.containsKey(pumpNumber)) {
            throw new KeyNotFoundException("Pump " + pumpNumber + " does not exist");
        }

        if (allPumps.get(pumpNumber).hasFailed()) {
            throw new CannotControlException("Pump " + pumpNumber + " is failed");
        }
        
        boolean isPumping = allPumps.get(pumpNumber).getStatus();
        allPumps.get(pumpNumber).setStatus(!isPumping);
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
    public Percentage condenserWaterLevel() {
        return plant.condenser().getWaterLevel();
    public void repairCondenser() throws CannotRepairException {
        condenser.repair();
    }

    @Override
    public Percentage condenserWear() {
        return plant.condenser().wear();
    public void repairTurbine() throws CannotRepairException {
        turbine.repair();
    }
    
    @Override
    public String wornComponent() {
        return plant.getCurrentWornComponent();
    public Percentage turbineWear(){
        return turbine.wear();
    }
    
    @Override
    public Percentage getPumpWear(int pumpID) throws KeyNotFoundException {
        if (plant.pumps().containsKey(pumpID)) {
            return plant.pumps().get(pumpID).wear();
        } else
        {
            throw new KeyNotFoundException("No pump with ID (" + pumpID + ") exists!");
        }
    public double getOutputPower() {
        return turbine.outputPower();
    }

    @Override
    public boolean turbineHasFailed() {
        return plant.turbine().hasFailed();
    public Temperature condenserTemperature() {
        return condenser.getTemperature();
    }
    
    @Override
    public int getSoftwareFailureTimeRemaining() {
        throw new UnsupportedOperationException("Not supported yet.");
    public Boolean getPumpState(int pumpNumber) throws KeyNotFoundException  {
        if (!allPumps.containsKey(pumpNumber)) {
            throw new KeyNotFoundException("Pump " + pumpNumber + " does not exist");
        }
        return allPumps.get(pumpNumber).getStatus();
    }
    
    @Override
    public double getOutputPower() {
        return plant.turbine().outputPower();
    public Boolean getValveState(int valveNumber) throws KeyNotFoundException {
        if (allConnections.containsKey(valveNumber)) {
            return allConnections.get(valveNumber).getOpen();
        } else {
            throw new KeyNotFoundException("Valve " + valveNumber + " does not exist");
        }
    }
    

    @Override
    public Pressure reactorMaximumPressure() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Temperature reactorMaximumTemperature() {
        throw new UnsupportedOperationException("Not supported yet.");
	}

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
    public Percentage getPumpWear(int pumpNumber)throws KeyNotFoundException {
        if (!allPumps.containsKey(pumpNumber)) {
            throw new KeyNotFoundException("Pump " + pumpNumber + " does not exist");
        }
        return allPumps.get(pumpNumber).wear();
	}

    public Temperature reactorMaximumTemperature() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean turbineHasFailed() {
        return turbine.hasFailed();
    }
    
    public boolean getPumpStatus(int pumpNumber) {
        return allPumps.get(pumpNumber).getStatus();
    }
}
