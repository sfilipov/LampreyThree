/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

/**
 *
 * @author james
 */
public class Valve implements IValve{
        
    private boolean open = false;

    @Override
    public boolean GetOpen() {
        return open;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void SetOpen(boolean Open) {
        open = Open;
        //throw new UnsupportedOperationException("Not supported yet.");
    }
}
