package lamprey.seprphase3.DynSimulator;

import eel.seprphase2.Simulator.Port;

/**
 * Base class for all components in the plant that allow fluid or steam to flow through them.
 *
 * @author Will
 */
public abstract class FlowThroughComponent {

    /**
     * Component that flows into this one.
     */
    FlowThroughComponent input;
    /**
     * Component that this one flows into.
     */
    FlowThroughComponent output;
    /**
     * Object representing the flow out of this component.
     */
    Port outputPort;
    /**
     * Dictates whether or not this component is pressurised.
     * i.e. Reactor/Condenser
     */
    boolean pressurised;
}
