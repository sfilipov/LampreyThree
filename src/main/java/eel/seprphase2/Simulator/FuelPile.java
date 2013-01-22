/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author Yazidi
 */
@JsonTypeName(value = "FuelPile")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class FuelPile {

    private final int maximumOutput = 20000000;
    @JsonProperty
    private Percentage controlRodPosition = new Percentage(0);

    /**
     *
     * @param extracted
     */
    public void moveControlRods(Percentage extracted) {
        controlRodPosition = extracted;

    }

    /**
     *
     * @param seconds
     *
     * @return
     */
    public int output(int seconds) {
        return (int)(maximumOutput * controlRodPosition.ratio() * seconds + 3000000);
    }

    /**
     *
     * @return
     */
    public Percentage controlRodPosition() {
        return this.controlRodPosition;
    }
}
