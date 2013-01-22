package eel.seprphase2.Simulator;

/**
 * Exception thrown when trying to control a failed component
 *
 * This exception is thrown by the Physical Model when trying to control a Failed Component.
 *
 * @author James
 */
public class CannotControlException extends ControlException {

    public CannotControlException(String message) {
        super(message);
    }
}
