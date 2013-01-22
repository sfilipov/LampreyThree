package eel.seprphase2.Simulator;

/**
 * Exception thrown when trying to repair an unrepairable component
 *
 *
 * Thrown when trying to repair a component which has not failed.
 *
 * @author James
 */
public class CannotRepairException extends ControlException {

    public CannotRepairException(String message) {
        super(message);
    }
}
