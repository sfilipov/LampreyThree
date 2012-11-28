/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

/**
 *
 * @author Yazidi
 */
public class Reactor implements PlantController {

    private int controlRodPosition;
    
    public void moveControlRods(int position) {
        if (position > 100 || position < 0) {
            throw new IllegalArgumentException();
        }
        this.controlRodPosition = position;
    }
    
    public int controlRodPosition() {
        return this.controlRodPosition;
    }

}
