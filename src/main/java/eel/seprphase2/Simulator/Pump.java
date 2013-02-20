package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Percentage;
import static eel.seprphase2.Utilities.Units.*;
import java.io.Serializable;
import static lamprey.seprphase3.Utilities.Units.kilogramsPerSecond;
import static lamprey.seprphase3.DynSimulator.GameConfig.PUMP_INDUCEDFLOWRATE;

/**
 *
 * @author Marius
 */
public class Pump extends FailableComponent implements Serializable {

    private boolean status = true;

    public Pump() {
        super();
    }

    public void step() {
        if (status && !hasFailed()) {
            outputPort(null).flowRate = PUMP_INDUCEDFLOWRATE;
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
