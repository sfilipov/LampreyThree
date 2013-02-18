package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Percentage;
import static eel.seprphase2.Utilities.Units.*;
import static lamprey.seprphase3.Utilities.Units.*;
import static lamprey.seprphase3.DynSimulator.GameConfig.FlowRateInducedByPumps;

/**
 *
 * @author Marius
 */
public class Pump extends FailableComponent {

    @JsonProperty
    private boolean status = true;

    private Pump() {
        super();
    }

    public void step() {
        if (status && !hasFailed()) {
            outputPort(null).flowRate = FlowRateInducedByPumps;
        } else {
            outputPort(null).flowRate = kilogramsPerSecond(0);
        }
    }

    @Override
    public Percentage calculateWearDelta() {
        return percent(1);
    }

    public void setStatus(boolean newStatus) {
        status = newStatus;
    }

    public boolean getStatus() {
        return status;
    }
}
