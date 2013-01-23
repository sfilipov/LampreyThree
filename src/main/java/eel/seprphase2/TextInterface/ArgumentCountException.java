package eel.seprphase2.TextInterface;

/**
 * Exception thrown when a command is called with the wrong number of arguments
 * @author david
 */
public class ArgumentCountException extends ArgumentException {

    public ArgumentCountException(String msg) {
        super(msg);
    }
}
