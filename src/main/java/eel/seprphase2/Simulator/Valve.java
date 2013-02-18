package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lamprey.seprphase3.DynSimulator.BlockableComponent;

/**
 *
 * @author james
 */
public class Valve extends BlockableComponent {

    @JsonProperty
    private boolean open;

    public Valve() {
        this.open = true;
        this.blocked = false;
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
}
