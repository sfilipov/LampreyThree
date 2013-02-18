package lamprey.seprphase3.DynSimulator;

/**
 * Base class for all components in the plant that allow fluid or steam to flow through them.
 *
 * @author Will
 */
public abstract class FlowThroughComponent {

    /**
     * Component that flows into this one.
     */
    public FlowThroughComponent input;
    /**
     * Component that this one flows into.
     */
    public FlowThroughComponent output;
    /**
     * Object representing the flow out of this component.
     */
    private OutputPort outputPort;
    /**
     * Dictates whether or not this component is pressurised.
     * i.e. Reactor/Condenser
     */
    public boolean pressurised = false;
    
    /**
     * Getter for outputPort.
     * Takes a reference to a FlowThroughComponent for context,
     * however this is only needed in the Junction class as it
     * keeps a separate Port instance for each connected output.
     */
    public OutputPort outputPort(FlowThroughComponent contextComponent) {
        return outputPort;
    }
}
