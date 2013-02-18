/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.DynSimulator;

import eel.seprphase2.GameOverException;
import eel.seprphase2.Simulator.CannotControlException;
import eel.seprphase2.Simulator.CannotRepairException;
import eel.seprphase2.Simulator.Condenser;
import eel.seprphase2.Simulator.FailableComponent;
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.Pump;
import eel.seprphase2.Simulator.Turbine;
import eel.seprphase2.Utilities.Percentage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author will
 */
public class FluidFlowController implements PlantController {
    
    
    
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
    
    @Override
    public void wearReactor() {
        Percentage damage = new Percentage(10);
        reactor.addWear(damage);
    }

    @Override
    public void failCondenser() {
        condenser.fail();
    }
    
    @Override
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
    public void setReactorToTurbine(boolean open) {
        reactorToTurbine.setOpen(open);
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
    
        
  // ----------------------------------- CODE TO CONVERT! -----------------------------
        /**
	 * Highest level method for updating flow. This method calls all other methods
	 * necessary for propagating flow, as well as blockages, throughout the system.
	 * In this order, we:
	 * 		- Set all outputs of all ConnectorPipes to not blocked.
	 * 		- Propagate blockages from all closed valves in the system back to their
	 * 			first preceding ConnectorPipe.
	 * 		- Propagate all blockages throughout the entire system.
	 * 		- Set the flow rate and temperature of all components to zero in 
	 * 			preparation for flow calculation & propagation.
	 * 		- Calculate and propagate the flow from the reactor forward.
	 * 		- Calculate the flow due to the pumps in the system and totals them up at
	 * 			the condenser output.
	 * 		- Propagate the flow out of the condenser forwards.
	 * 		- Propagate flow through all paths in the system.
	 * 		- Transfer steam from the reactor into the condenser.
	 * 		- Transfer water from the condenser into the reactor. 
	 */
	private void updateFlow() {
		setAllConnectorPipesUnblocked();
		blockFromValves();
		blockFromConnectorPipes();
		resetFlowAllComponents();
		
		propagateFlowFromReactor(); // Start propagation of steam flow.
		propagateFlowFromPumpsToCondenser(); // Total up all pump flows at condenser
		propagateFlowFromCondenser();	// Start propagation of water flow.
		propagateFlowFromConnectorPipes();
		moveSteam();
		moveWater(); 
	}
	
	/**
	 * Moves water out of the condenser and into the reactor due to the flow in and
	 * out of the components.
	 */
	private void moveWater()
	{
		Condenser condenser = this.plant.getCondenser();
		Reactor reactor = this.plant.getReactor();
		int waterInCondenser = condenser.getWaterVolume();
		int amountOut = 0;
		int condenserFlowOut = condenser.getFlowOut().getRate();
		// Check if there's enough water in the condenser to fulfil the flow rate.
		amountOut = (waterInCondenser > condenser.getFlowOut().getRate()) ?
						condenserFlowOut: 
						waterInCondenser; // otherwise empty out the condenser!)
		condenser.updateWaterVolume(-amountOut);
		// This should really use reactor's input's flow out but ah well.
		reactor.updateWaterVolume(amountOut);
	}

	/**
	 * Forcefully removes steam from the reactor and places it into the condenser.
	 * Based upon the flow! :) 
	 */
	private void moveSteam()
	{
		Reactor reactor = this.plant.getReactor();
		Condenser condenser = this.plant.getCondenser();
		reactor.updateSteamVolume(-reactor.getFlowOut().getRate());
		condenser.updateSteamVolume(condenser.getInput().getFlowOut().getRate());
	}

	/**
	 * If all paths out of the reactor are blocked and the reactor is 
	 * connected to a ConnectorPipe, then the 'zero flow' will not have been
	 * propagated back to it's flowOut. We therefore need to check whether or not 
	 */
	private void propagateNoFlowBackToReactor()
	{
		Reactor reactor = this.plant.getReactor();
		PlantComponent nextComponent = reactor.getOutput();
		int	nextComponentFlowRate = nextComponent.getFlowOut().getRate();
		// If the next component is a ConnectorPipe we need to
		// remember that the flowOut is divided by the number of active
		// outputs!
		if (nextComponent instanceof ConnectorPipe) 
			nextComponentFlowRate *= ((ConnectorPipe) nextComponent).numOutputs();
		// If the rate of flow out of the first component is zero, propagate this back to the reactor.
		if (nextComponentFlowRate == 0) reactor.getFlowOut().setRate(nextComponentFlowRate);
	}

	/**
	 * Resets all ConnectorPipe paths to unblocked.
	 * We do this to all ConnectorPipes at the beginning of each updatePlant()
	 * before propagating the blockages since valves can change state between 
	 * steps.
	 */
	private void setAllConnectorPipesUnblocked() {
		for (ConnectorPipe cp : this.plant.getConnectorPipes()) {
			cp.resetState();
		}
	}
	
	/**
	 * Iterates through all valves in the system and if they are closed we
	 * propagate the blockage through to the next preceding ConnectorPipe.
	 */
	private void blockFromValves() {
		List<Valve> valves = this.plant.getValves();
		for (Valve v : valves) {
			if (!v.isOpen()) blockToPrecedingConnectorPipe(v);
		}
	}
	
	/**
	 * Iterates through all ConnectorPipes in the system and propagates the blockage,
	 * if all outputs of that ConnectorPipe is blocked.
	 * 
	 * This is done until all blocked ConnectorPipes have had their blockage propagated.
	 */
	private void blockFromConnectorPipes() {
		boolean changed = true;
		List<ConnectorPipe> connectorPipes = this.plant.getConnectorPipes();
		Map<ConnectorPipe, Boolean> hasBeenPropagated = new HashMap<ConnectorPipe, Boolean>();
		while (changed) {
			changed = false;
			// iterate through all connector pipes and check if they're blocked up.
			for (ConnectorPipe c : connectorPipes) {
				// If we're not already keeping track of c, add it to the hashmap
				if (!hasBeenPropagated.containsKey(c)) hasBeenPropagated.put(c, false);
				// If connectorPipe has all of it's outputs blocked
				// And the blockage hasn't been propagated
				if (isConnectorBlocking(c) && !hasBeenPropagated.get(c)) {
					// Block the path leading into it.
					blockPrecedingFromConnectorPipe(c);
					hasBeenPropagated.put(c, true);
					changed = true;
				}
			}
		}
	}
	
	
	/**
	 * Returns true if all outputs of a ConnectorPipe are blocked.
	 * @return true if all outputs of a ConnectorPipe are blocked.
	 */
	private boolean isConnectorBlocking(ConnectorPipe cp) {
		for(Boolean blocked : cp.getOutputsMap().values()) {	
			if (!blocked) return false;
		}
		return true;
	}

	/**
	 * Traces back to the first occurring connector pipe and blocks the path out leading 
	 * to blockedComponent.
	 * We assume checks have been made to ensure blockedComponent is actually blocked.
	 * 
	 * @param blockedComponent component to start from.
	 */
	private void blockToPrecedingConnectorPipe(PlantComponent blockedComponent) {
		PlantComponent currentComponent = blockedComponent.getInput();
		PlantComponent prevComponent = blockedComponent;
		boolean doneBlocking = false;
		while (!doneBlocking) {
			if (currentComponent instanceof ConnectorPipe) {
				((ConnectorPipe) currentComponent).setComponentBlocked(prevComponent);
				doneBlocking = true;
			} else if (currentComponent instanceof Reactor) {
				// No need to do anything here, just stop iterating.
				doneBlocking = true;
			} else {
				prevComponent = currentComponent;
				currentComponent = currentComponent.getInput();
			}
		}
	}
	
	/**
	 * Calls blockPrecedingConnectorPipe() for all input paths into blockedConnector. 
	 * We assume checks have been made to ensure blockedConnector is actually blocked.
	 * 
	 * If an input is a ConnectorPipe, set the output that blockedConnector is connected
	 * to blocked.
	 * 
	 * @param blockedConnector the blocked ConnectorPipe to start from.
	 */
	private void blockPrecedingFromConnectorPipe(ConnectorPipe blockedConnector) {
		List<PlantComponent> multipleInputs = ((ConnectorPipe) blockedConnector).getInputs();
		for (PlantComponent pc : multipleInputs) {
			if (pc instanceof ConnectorPipe) {
				((ConnectorPipe) pc).setComponentBlocked(blockedConnector);
			} else {
				if (pc != null) blockToPrecedingConnectorPipe(pc);
			}
		}
	}
	
	/**
	 * Resets the flow of all components back ready for the flow around the system to be
	 * recalculated for the current state of the plant.
	 */
	private void resetFlowAllComponents() {
		for (PlantComponent pc : this.plant.getPlantComponents()) {
			pc.getFlowOut().setRate(0);
			pc.getFlowOut().setTemperature(0);
		}
	}
	
	/**
	 * Start off propagation of the flow from the reactor to the next 
	 * ConnectorPipe encountered.
	 */
	private void propagateFlowFromReactor()
	{
		int flowRate = calcReactorFlowOut();
		Reactor reactor = this.plant.getReactor();
		Condenser condenser = this.plant.getCondenser();
		// If there's a clear path from the reactor to the condenser then calculate
		// and start off the flow being propagated.
		if (isPathTo(reactor, condenser, true)) {
			reactor.getFlowOut().setRate(flowRate);
			reactor.getFlowOut().setTemperature(reactor.getTemperature());
			limitReactorFlowDueToValveMaxFlow(reactor);
			propagateFlowToNextConnectorPipe(reactor);
		} else {
			// Otherwise, all paths are blocked & don't bother.
		}
	}
	
	/**
	 * Sums up the maximum flow possible through all valves that have a clear backward
	 * path to the reactor and if this maximum flow is greater than the amount of steam 
	 * wanting to come out of the reactor due to pressue, the rate is limited. 
	 * 
	 * @param reactor the reactor to limit.
	 */
	private void limitReactorFlowDueToValveMaxFlow(Reactor reactor)
	{
		int maxFlow = 0;
		for (Valve v : this.plant.getValves()) {
			// If there is a path backwards from this valve to the reactor.
			// Also implying that it is actually in front of the reactor.
			if (isPathTo(v, reactor, false)) {
				// increase the maximum flow allowed out of the reactor.
				maxFlow += v.getMaxSteamFlow();
			}
		}
		if (reactor.getFlowOut().getRate() > maxFlow) reactor.getFlowOut().setRate(maxFlow);
	}

	/**
	 * Calculate and return the flow of steam out of the reactor due to the difference in
	 * steam volume between the reactor and condenser.
	 * 
	 * This method ignores any blockages, these are dealt with when the flow is propagated
	 * around the system.
	 *  
	 * @return rate of flow of steam out of the reactor.
	 */
	private int calcReactorFlowOut() {
		Reactor reactor = this.plant.getReactor();
		Condenser condenser = this.plant.getCondenser();
		int steamDiff = Math.abs(reactor.getSteamVolume() - condenser.getSteamVolume());
		int flowRate;
		if (steamDiff > this.plant.getMaxSteamFlowRate()) {
			flowRate = this.plant.getMaxSteamFlowRate();
		} else {
			flowRate = steamDiff;
		}
		if (reactor.getSteamVolume() < flowRate) flowRate = reactor.getSteamVolume();
		return flowRate;
	}
	
	/**
	 * Iterates through connector pipes, calculates their flow out & if it has changed,
	 * propagate this new flow forward to the next connector pipe.
	 * Do this until nothing in the system changes 
	 * (Inspired by bubble sort's changed flag... "Good Ol' Bubble Sort!")
	 */
	private void propagateFlowFromConnectorPipes()
	{
		boolean changed = true;
		int oldRate;
		List<ConnectorPipe> connectorPipes = this.plant.getConnectorPipes();
		while (changed) {
			changed = false;
			// iterate through all connector pipes and update their rate.
			for (ConnectorPipe c : connectorPipes) {
				oldRate = c.getFlowOut().getRate();
				calcConnectorFlowOut(c);
				if (oldRate != c.getFlowOut().getRate()) {
					propagateFlowFromConnectorPipe(c);
					changed = true;
				}
			}
		}
	}
	
	/**
	 * Propagates the flow rate and temperature to every component from startComponent
	 * until a ConnectorPipe is encountered.
	 * 
	 * @param startComponent Component to start the propagation from.
	 */
	private void propagateFlowToNextConnectorPipe(PlantComponent startComponent) {
		PlantComponent prevComponent;
		// If startComponent.isPressurised() (=> it is a reactor or condenser) start from here, not its input. 
		prevComponent = (startComponent.isPressurised()) ? startComponent : startComponent.getInput();
		PlantComponent currComponent = startComponent;
		boolean donePropagating = false;
		while (!donePropagating) {
			if (currComponent instanceof ConnectorPipe) {
				donePropagating = true;
			} else if (currComponent instanceof Condenser) {
				donePropagating = true;
			} else {
				currComponent.getFlowOut().setRate(prevComponent.getFlowOut().getRate());
				currComponent.getFlowOut().setTemperature(prevComponent.getFlowOut().getTemperature());
				prevComponent = currComponent;
				currComponent = currComponent.getOutput();
			}
		}
	}
	
	/**
	 * Propagates calls the appropriate methods for all unblocked outputs of 
	 * startConnectorPipe in order to propagate flow through the system.  
	 * 
	 * @param startConnectorPipe The ConnectorPipe to propagate flow onward from.
	 */
	private void propagateFlowFromConnectorPipe(ConnectorPipe startConnectorPipe) {
		Map<PlantComponent, Boolean> outputs = startConnectorPipe.getOutputsMap();
		for (PlantComponent pc : outputs.keySet()) {
			// If the output is not blocked.
			if (!outputs.get(pc)) {
				if (pc instanceof ConnectorPipe) {
					propagateFlowFromConnectorPipe((ConnectorPipe) pc);
				} else {
					propagateFlowToNextConnectorPipe(pc);
				}
			}
		}
	}
	
	
	/**
	 * Update the Flow out of a connector to reflect it's inputs and outputs.
	 * @param connector the connector to update.
	 */
	private void calcConnectorFlowOut(ConnectorPipe connector) {
		ArrayList<PlantComponent> inputs = connector.getInputs();
		int totalFlow = 0;
		int avgTemp = 0;
		int numOutputs = connector.numOutputs();
		int numInputs = 0;
		for (PlantComponent input : inputs) {
			if (input != null) {
				totalFlow += input.getFlowOut().getRate();
				avgTemp += input.getFlowOut().getTemperature();
				numInputs++;
			}
		}
		totalFlow = (numOutputs != 0) ? totalFlow / numOutputs : 0; // average the flow across all active outputs.
		avgTemp = (numInputs != 0) ? avgTemp / numInputs : 0;
		connector.getFlowOut().setRate(totalFlow);
		connector.getFlowOut().setTemperature(avgTemp);
	}
	
	/**
	 * Set's off the propagation from the condenser to the next ConnectorPipe from 
	 * it's output.
	 */
	private void propagateFlowFromCondenser()
	{
		Condenser condenser = this.plant.getCondenser();
		condenser.getFlowOut().setTemperature(condenser.getTemperature());
		propagateFlowToNextConnectorPipe(condenser);
	}

	/**
	 * Tracks back from a pump and if there is a clear path to the condenser
	 * adds the flow increase at this pump to the flow out of the condenser.
	 * 
	 * This method does not support multiple condensers.
	 */
	private void propagateFlowFromPumpsToCondenser()
	{
		Condenser condenser = this.plant.getCondenser();
		// Iterate through all pumps and start tracking back through the system
		for (Pump p : this.plant.getPumps()) {
			// If the pump is broken, move onto the next one.
			if (!this.plant.getFailedComponents().contains(p) && p.getInput() != null) {
				increaseCondenserFlowOutFromPump(p);
			}
		}
		// Finally.. Make sure the flow out of the condenser will not take us into negative volume.
		int condenserWaterVolume = condenser.getWaterVolume();
		int condenserFlowOut = condenser.getFlowOut().getRate();
		if (condenserFlowOut > condenserWaterVolume) condenser.getFlowOut().setRate(condenserWaterVolume);
	}
	
	/**
	 * Gets the flowRate due to this pump from it's current rpm.
	 * Then checks if there is a path from Pump p to the connector (backwards)
	 * and if there is, we add the flow rate due to this pump to the flow rate out of
	 * the condenser.
	 * 
	 * @param p Pump to increase the flow out of the condenser due to.
	 */
	private void increaseCondenserFlowOutFromPump(Pump p) {
		int flowRate = calcFlowFromPumpRpm(p);
		Condenser condenser = this.plant.getCondenser();
		// If there's a clear path to the condenser from p then add the flowRate of this pump
		// to the flowOut rate of the condenser.
		if (isPathTo(p, condenser, false)) {
			int condenserFlowOut = condenser.getFlowOut().getRate();
			condenser.getFlowOut().setRate(condenserFlowOut + flowRate);
		}
	}
	
	/**
	 * Returns true if there exists a path from start to goal that is not blocked and does not 
	 * pass through a pressurised component (Reactor/Condenser) in the direction that is specified.
	 * 
	 * If forwards = true then the path with be traced using outputs, otherwise inputs.
	 * 
	 * @param start Component to start from.
	 * @param goal Component to attempt to reach.
	 * @param forwards Direction of the path
	 * @return true if there exists a path from start to goal that is not blocked and does not 
	 * pass through a pressurised component in the direction that is specified.
	 */
	private boolean isPathTo(PlantComponent start, PlantComponent goal, boolean forwards) {
		List<PlantComponent> possiblePaths;
		ConnectorPipe cp;
		
		PlantComponent current = start;
		PlantComponent next = (forwards) ? start.getOutput() : start.getInput();
		while(!current.equals(goal)) {
			// If we're at any other component than a ConnectorPipe, then advance to the next
			// component in the system in the direction we want.
			if (!(next instanceof ConnectorPipe)) {
				current = next;
				next = (forwards) ? current.getOutput() : current.getInput();
			} else {
				cp = (ConnectorPipe) next;
				if (!forwards) {
					// If we're travelling backwards check if this path back is blocked
					if (cp.getOutputsMap().get(current)) return false;
				}
				// I say, I say, we've got ourselves a ConnectorPipe!
				possiblePaths = (forwards) ? cp.getOutputs() : cp.getInputs();
				for (PlantComponent possibleNext : possiblePaths) {
					/* Check if we're moving forwards, check that the ConnectorPipe output
					 * we're leaving from isn't blocked. If it is we don't move that way.
					 */
					if (forwards) {
						if (!cp.getOutputsMap().get(possibleNext)) {
							// return isPathTo(possibleNext1, ...) || ... || isPathTo(possibleNextN,...)
							if (isPathTo(possibleNext, goal, forwards)) return true;
						}
					} else {
						// return isPathTo(possibleNext1, ...) || ... || isPathTo(possibleNextN,...)
						if (isPathTo(possibleNext, goal, forwards)) return true;
					}
				}
				// All paths out of this connector pipe are blocked, no paths available :(
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Calculates the flow through a pump based upon it's rpm.
	 * The flow is linearly correlated to the rpm.
	 * @param p The pump to calculate the rpm of.
	 * @return The flow rate through pump, p.
	 */
	private int calcFlowFromPumpRpm(Pump p)
	{
		int maxRpm = p.getMaxRpm();
		return (int) Math.round(this.plant.getMaxWaterFlowRatePerPump() * (1 - (new Double((maxRpm - p.getRpm())/new Double(maxRpm)))));
	}
    
    
    
}
