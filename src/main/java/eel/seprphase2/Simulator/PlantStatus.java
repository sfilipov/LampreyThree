/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;

/**
 *
 * @author david
 */
public interface PlantStatus {

    public Percentage controlRodPosition();

    public Pressure pressure();

    public Temperature temperature();

    public Percentage waterLevel();
}
