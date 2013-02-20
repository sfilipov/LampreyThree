package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Density;
import static eel.seprphase2.Utilities.Units.*;
import lamprey.seprphase3.DynSimulator.OutputPort;
import static lamprey.seprphase3.Utilities.Units.kilogramsPerSecond;
import static lamprey.seprphase3.DynSimulator.GameConfig.ROOMTEMPERATURE;

/**
 *
 * @author James
 */
public class HeatSink {

    @JsonProperty
    private OutputPort outputPort;

    public HeatSink() {
        outputPort = new OutputPort();
        outputPort.temperature = ROOMTEMPERATURE;
        outputPort.flowRate = kilogramsPerSecond(2);
    }

    public OutputPort outputPort() {
        return outputPort;
    }
}
