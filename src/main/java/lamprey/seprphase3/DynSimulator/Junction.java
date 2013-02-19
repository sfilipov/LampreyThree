package lamprey.seprphase3.DynSimulator;

import eel.seprphase2.Utilities.*;
import static eel.seprphase2.Utilities.Units.kelvin;
import static lamprey.seprphase3.Utilities.Units.kilogramsPerSecond;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import lamprey.seprphase3.Utilities.MassFlowRate;

/**
 *
 * @author will
 */
public class Junction extends FlowThroughComponent {

    private HashMap<FlowThroughComponent, BlockablePort> outputPorts;
    private ArrayList<FlowThroughComponent> inputs;

    public Junction() {
        this.outputPorts = new HashMap<FlowThroughComponent, BlockablePort>();
        this.inputs = new ArrayList<FlowThroughComponent>();
    }

    /**
     * Connects an output to this Junction. Also creates a new outputPort for it.
     *
     * @param component the component to connect as an output.
     */
    public void addOutput(FlowThroughComponent output) {
        this.outputPorts.put(output, new BlockablePort());
    }

    /**
     * Adds an input to this Junction.
     *
     * @param input Component to connect as an input to this Junction.
     */
    public void addInput(FlowThroughComponent input) {
        this.inputs.add(input);
    }

    /**
     * Returns a list of inputs connected to this Junction.
     *
     * @return a list of inputs connected to this Junction.
     */
    public ArrayList<FlowThroughComponent> inputs() {
        return this.inputs;
    }

    /**
     * Returns a list of outputs connected to this Junction. Only returns FlowThroughComponents, not their respective
     * port.
     *
     * @return a list of outputs connected to this Junction.
     */
    public ArrayList<FlowThroughComponent> outputs() {
        return new ArrayList(this.outputPorts.keySet());
    }

    /**
     * Returns a reference to the OutputPort object corresponding to the attached contextComponent.
     *
     * @param contextComponent
     *
     * @return a reference to the OutputPort object corresponding to the attached contextComponent.
     *
     * @throws IllegalArgumentException
     */
    @Override
    public OutputPort outputPort(FlowThroughComponent contextComponent) throws IllegalArgumentException {
        if (this.outputPorts.containsKey(contextComponent)) {
            return this.outputPorts.get(contextComponent);
        } else {
            throw new IllegalArgumentException("Attempt to retrieve outputPort reference for a " +
                                               "component not attached to this junction.");
        }
    }
    
    public HashMap<FlowThroughComponent, BlockablePort> outputPortMap() {
        return outputPorts;
    }
    
    public boolean isBlocked(FlowThroughComponent componentToCheck) {
        return this.outputPorts.get(componentToCheck).blocked;
    }

    public void block(FlowThroughComponent componentToBlock) {
        if (this.outputPorts.containsKey(componentToBlock)) {
            BlockablePort attachedPort = this.outputPorts.get(componentToBlock);
            attachedPort.blocked = true;
        } else {
            throw new IllegalArgumentException("Attempt to retrieve outputPort reference for a " +
                                               "component not attached to this junction.");
        }
    }

    /**
     * Returns the number of non-blocked outputs.
     *
     * @return the number of non-blocked outputs.
     */
    public int numOutputs() {
        int totalOutputs = 0;
        for (Entry<FlowThroughComponent, BlockablePort> entry : this.outputPorts.entrySet()) {
            if (!entry.getValue().blocked) {
                totalOutputs++;
            }
        }
        return totalOutputs;
    }

    /**
     * Resets all outputs to the not-blocked state.
     */
    public void resetState() {
        for (Entry<FlowThroughComponent, BlockablePort> entry : this.outputPorts.entrySet()) {
            entry.getValue().blocked = false;
        }
    }
    
    /**
     * Update the output ports to reflect inputs.
     *
     */
    public void updateOutputPorts() {
        MassFlowRate avgFlow = kilogramsPerSecond(0);
        Temperature avgTemp = kelvin(0);
        int numOutputs = numOutputs();
        int numInputs = 0;
        for (FlowThroughComponent inputComponent : inputs) {
            if (inputComponent != null) {
                avgFlow = avgFlow.plus(inputComponent.outputPort(this).flowRate);
                avgTemp = avgTemp.plus(inputComponent.outputPort(this).temperature);
                numInputs++;
            }
        }
        // average the flow across all active outputs.
        avgFlow = kilogramsPerSecond((numOutputs != 0) ? avgFlow.inKilogramsPerSecond() / numOutputs : 0); 
        avgTemp = kelvin((numInputs != 0) ? avgTemp.inKelvin() / numInputs : 0);
        for (BlockablePort bp : outputPorts.values()) {
            if (!bp.blocked) {
                bp.flowRate = avgFlow;
                bp.temperature = avgTemp;
            }
        }
    }

    /**
     * A port that 'knows' if the path ahead is blocked.
     */
    private class BlockablePort extends OutputPort {

        public boolean blocked;

        public BlockablePort() {
            super();
            blocked = false;
        }
    }
}
