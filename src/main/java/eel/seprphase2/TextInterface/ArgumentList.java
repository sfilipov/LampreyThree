package eel.seprphase2.TextInterface;

import java.util.ArrayList;

/**
 *
 * @author drm
 */
public class ArgumentList {

    private ArrayList<Argument> arguments;

    public ArgumentList() {
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
                                             index + " arguments but got only " +
                                             arguments.size());
        }
    }

    public void requireExactly(int count) throws ArgumentCountException {
        if (count != arguments.size()) {
            throw new ArgumentCountException("Expected exactly " + count +
                                             " arguments but got " + arguments.size());
        }
    }

    public void requireAtLeast(int count) throws ArgumentCountException {
        if (count > arguments.size()) {
            throw new ArgumentCountException("Expected at least " + count +
                                             " arguments but got only " + arguments.size());
        }
    }
}
