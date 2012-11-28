/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author Yazidi
 */
class MockController implements PlantController {
    public Percentage controlRodPosition;

    public void moveControlRods(Percentage extracted) {
        this.controlRodPosition = extracted;
    }

}
