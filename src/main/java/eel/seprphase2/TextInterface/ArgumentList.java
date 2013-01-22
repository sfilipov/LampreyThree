package eel.seprphase2.TextInterface;

import java.util.ArrayList;

/**
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

    public void add(String argument) {
        arguments.add(new Argument(argument));
    }

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

    public void requireExactly(int count) throws ArgumentCountException {
        if (count != arguments.size()) {
            throw new ArgumentCountException("Expected exactly " + count +
                                             " argument(s) but got " + arguments.size());
        }
    }

    public void requireAtLeast(int count) throws ArgumentCountException {
        if (count > arguments.size()) {
            throw new ArgumentCountException("Expected at least " + count +
                                             " argument(s) to command" +
                                             command +
                                             " but got only " + arguments.size());
        }
    }

    public int count() {
        return arguments.size();
    }
}
