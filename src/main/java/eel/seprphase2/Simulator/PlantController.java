/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author david
 */
public interface PlantController {
    public void setUsername(String username);
    public void moveControlRods(Percentage extracted);
}
