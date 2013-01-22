package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Simulator.Component;
import eel.seprphase2.Utilities.Percentage;

/**
 * A FailableComponent is one that takes on wear/damage attributes. This class allows a component to adopt a failure
 * state and will accrue damage over time. The calculateWear delta must be overridden by any child class that derives
 * this
 *
 * @author Marius Dumetrescu
 */
public abstract class FailableComponent extends Component {

    @JsonProperty
    private boolean hasFailed;      //The state of the component
    @JsonProperty
    private Percentage wear;                //Current wear level - capped at 100%

    /**
     * Constructor for the FailableComponent. Sets default percentage to 0 and a normal FailureState
     */
    public FailableComponent() {
        //Initialize to a normal state
        hasFailed = false;
        wear = new Percentage(0);
    }

    public boolean hasFailed() {
        return hasFailed;
    }

    public void fail() {
        hasFailed = true;
        stepWear();
    }

    public void repair() throws CannotRepairException {
        if (!hasFailed) {
            throw new CannotRepairException("This component cannot be repaired");
        }

        hasFailed = false;
        wear = new Percentage(0);
    }

    /**
     * CalculateWearDelta must be overridden by any child classes. It should be used to calculate a minute change in
     * wear level for the component to be added onto it. This is normally calculated within a method's step() routine.
     *
     * @return Percentage. The number of percentage points to add to the current wear level.
     */
    protected abstract Percentage calculateWearDelta();

    /**
     * Returns the component's current wear level as a percentage.
     *
     * @return The component's wear level.
     */
    public Percentage wear() {
        return wear;
    }

    /**
     * stepWear will increase the wear of the component from a delta value. The value will be capped at 100 and 0
     *
     * @param delta The number of percentage points to increase the FailableComponent's wear level by between 0 and 100
     */
    public void stepWear() {
        Percentage wearDelta = calculateWearDelta();
        if ((wear.points() + wearDelta.points()) < 100) {
            wear = wear.plus(wearDelta);
        } else {
            wear = new Percentage(100);     //Cap at 100%
        }
        if (wearDelta.points() == 0) {
            wear = new Percentage(0);        //Cap at 0%
        }

    }
}
