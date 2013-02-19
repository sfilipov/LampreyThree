package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lamprey.seprphase3.DynSimulator.BlockableComponent;
import lamprey.seprphase3.Utilities.MassFlowRate;
import static lamprey.seprphase3.Utilities.Units.kilogramsPerSecond;
import static lamprey.seprphase3.DynSimulator.GameConfig.DefaultMaxFlowRateThroughValve;
/**
 *
 * @author james
 */
public class Valve extends BlockableComponent {

    @JsonProperty
    private boolean open;
    @JsonProperty
    private MassFlowRate maxThroughput;

    public Valve() {
        this.open = true;
        this.blocked = false;
        this.maxThroughput = DefaultMaxFlowRateThroughValve;
    }
    
    public Valve(MassFlowRate customMaxThroughput) {
        this.open = true;
        this.blocked = false;
        this.maxThroughput = customMaxThroughput;
    }

    /**
     *
     * @return
     */
    public boolean getOpen() {
        return open;
    }
    
    /**
     *
     * @param Open
     */
    public void setOpen(boolean open) {
        this.open = open;
        this.blocked = !open;
    }
    
    public MassFlowRate maxThroughput() {
        return maxThroughput;
    }
}
