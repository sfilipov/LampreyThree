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
    /**
     *
     * @param username
     */
    public void setUsername(String username);
    /**
     *
     * @param extracted
     */
    public void moveControlRods(Percentage extracted);
}
