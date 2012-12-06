/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author Yazidi
 */
public class FuelPile {

    private final int maximumOutput = 10000000;
    @JsonProperty
    private Percentage controlRodPosition = new Percentage(0);

    public void moveControlRods(Percentage extracted) {
        controlRodPosition = extracted;

    }

    public int output(int seconds) {
        return (int)(maximumOutput * controlRodPosition.ratio() * seconds);
    }
    
    public Percentage controlRodPosition() {
        return this.controlRodPosition;
    }
    
}
