/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Density;

/**
 *
 * @author James
 */
public class HeatSink extends Component {

    @JsonProperty
    private Port outputPort;

    public HeatSink() {
        outputPort = new Port();
        outputPort.temperature = eel.seprphase2.Utilities.Units.kelvin(308.15);
        outputPort.density = Density.ofLiquidWater();
        outputPort.mass = eel.seprphase2.Utilities.Units.kilograms(10);
    }

    public Port outputPort() {
        return outputPort;
    }
}
