/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author james
 */
public class Valve {

    @JsonProperty
    private boolean open = true;

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
    public void setOpen(boolean Open) {
        open = Open;
    }
}
