package lamprey.seprphase3.DynSimulator;

import java.util.List;

/**
 *
 * @author will
 */
public class Junction {
    
    private List<PortMap> outputPorts;
    
    public Junction() {
        
    }
    
    /**
     * Internal class that maps an attached component to it's individual 
     * output port and stores whether or not that port is blocked.
     */
    private class PortMap {
        
        private FlowThroughComponent attachedComponent;
        private OutputPort port;
        private boolean blocked;
        
        public PortMap(FlowThroughComponent component, OutputPort port) {
            this.attachedComponent = component;
            this.port = port;
            this.blocked = false;
        }
        
    }
    
}
