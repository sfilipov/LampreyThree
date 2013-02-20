package lamprey.seprphase3.DynSimulator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author will
 */
public abstract class BlockableComponent extends FlowThroughComponent {
    
    /**
     * Whether or not this component is blocked.
     */
    public boolean blocked;
    
    
}
