package eel.seprphase2.TextInterface;

import java.util.ArrayList;

/**
 * Provide an error-handling, bounds-checked argument list for user commands
 * 
 * Throws exceptions with user-helpful error messages
 * 
 * @author David
 */
public class ArgumentList {

    private final String command;
    private ArrayList<Argument> arguments;

    public ArgumentList(String command) {
        this.command = command;
        arguments = new ArrayList<Argument>();
    }

    /**
     * Add an argument to the list
     * @param argument the string representing the argument
     */
    public void add(String argument) {
        arguments.add(new Argument(argument));
    }

    /**
     * Return the argument at a position, doint error checking
     * @param index the zero-based index of the argument to retrieve
     * @return the argument at index
     * @throws ArgumentCountException when there are insufficient arguments
     */
    public Argument at(int index) throws ArgumentCountException {
        try {
            return arguments.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new ArgumentCountException("Expected at least " +
                                             (index + 1) +
                                             " argument(s) to command '" +
                                             command +
                                             "' but got only " +
                                             arguments.size());
        }
    }

    /**
     * Count arguments in list
     * @return the number of arguments in the list
     */
    public int count() {
        return arguments.size();
    }
}
