/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;

/**
 *
 * @author Yazidi
 */
class MockController implements PlantController {
    public int position;

    public void moveControlRods(int position) {
        this.position = position;
    }

    public int controlRodPosition() {
        return position;
    }
    
}
