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
public class Valve extends Component implements IValve {
    
    @JsonProperty
    private boolean open = true;
    
    /**
     *
     * @return
     */
    @Override
    public boolean getOpen() {
        return open;
       }

    /**
     *
     * @param Open
     */
    @Override
    public void setOpen(boolean Open) {
        open = Open;
       }
}
