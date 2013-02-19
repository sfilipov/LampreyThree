package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Percentage;
import static eel.seprphase2.Utilities.Units.*;

/**
 *
 * @author Marius
 */
public class Turbine extends FailableComponent {

    @JsonProperty
    private double outputPower;

    /**
     *
     */
    public Turbine() {
        super();
    }

    /**
     *
     */
    public void step(double seconds) {
        if (hasFailed()) {
            outputPower = 0;
            stepWear();
            return;
        }
        outputPower = this.outputPort(null).flowRate.massFlowForTime(seconds).inKilograms() * 1000; //convert to grams. 
        stepWear();

    }

    /**
     *
     * @return
     */
    public double outputPower() {
        return outputPower;
    }

    /**
     *
     * @return
     */
    @Override
    public Percentage calculateWearDelta() {
        return new Percentage(1);
    }
}
