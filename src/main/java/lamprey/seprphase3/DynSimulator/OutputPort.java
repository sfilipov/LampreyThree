package lamprey.seprphase3.DynSimulator;

import eel.seprphase2.Utilities.Temperature;
import lamprey.seprphase3.Utilities.MassFlowRate;

/**
 * Represents the output flow out of a component.
 * @author will
 */
public class OutputPort {
    
    public MassFlowRate flowRate;
    public Temperature temperature;
    
    public OutputPort() {
        this.flowRate = new MassFlowRate(0);
        this.temperature = new Temperature(0);
    }
    
    public OutputPort(MassFlowRate massFlowRate, Temperature temperature) { 
        this.flowRate = massFlowRate;
        this.temperature = temperature;
    }
    
}
