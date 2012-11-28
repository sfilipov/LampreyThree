/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author Yazidi
 */
public class Reactor implements PlantController {

    private Percentage controlRodPosition;

    public void moveControlRods(Percentage extracted) {
        this.controlRodPosition = extracted;
    }

    public Percentage controlRodPosition() {
        return this.controlRodPosition;
    }

    public Percentage waterLevel() {
        return new Percentage(50);
    }
}
