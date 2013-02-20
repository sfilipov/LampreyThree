package lamprey.seprphase3.DynSimulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Temperature;
import java.io.Serializable;
import lamprey.seprphase3.Utilities.MassFlowRate;

/**
 * Represents the output flow out of a component.
 * @author will
 */
public class OutputPort implements Serializable {
    
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
    
    public Mass flownThroughInTime(double seconds) {
        return flowRate.massFlowForTime(seconds);
    }
    
}
