package lamprey.seprphase3.DynSimulator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Base class for all components in the plant that allow fluid or steam to flow through them.
 *
 * @author Will
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public abstract class FlowThroughComponent {

    /**
     * Component that flows into this one.
     */
    @JsonProperty
    public FlowThroughComponent input;
    /**
     * Component that this one flows into.
     */
    @JsonProperty
    public FlowThroughComponent output;
    /**
     * Object representing the flow out of this component.
     */
    @JsonProperty
    private OutputPort outputPort = new OutputPort();
    /**
     * Dictates whether or not this component is pressurised.
     * i.e. Reactor/Condenser
     */
    @JsonProperty
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
