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
public class Valve implements IValve{
    
    @JsonProperty
    private boolean open = true;
    
    @Override
    public void setOpen(boolean Open) {
        open = Open;
       }
    
    @Override
    public boolean getOpen() {
        return open;
       }
}
