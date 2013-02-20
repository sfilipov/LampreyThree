package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lamprey.seprphase3.DynSimulator.BlockableComponent;
import lamprey.seprphase3.Utilities.MassFlowRate;
import static lamprey.seprphase3.Utilities.Units.kilogramsPerSecond;
import static lamprey.seprphase3.DynSimulator.GameConfig.VALVE_DEFAULTMAXTHROUGHPUT;
/**
 *
 * @author james
 */
public class Valve extends BlockableComponent implements Serializable {

    private boolean open;
    private MassFlowRate maxThroughput;

    public Valve() {
        this.open = true;
        this.blocked = false;
        this.maxThroughput = VALVE_DEFAULTMAXTHROUGHPUT;
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
