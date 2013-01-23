package eel.seprphase2.TextInterface;

/**
 * Concrete TextRenderer for use with Standard Output
 * @author David
 */
public class TerminalRenderer implements TextRenderer {

    /**
     * Write a line to the terminal
     * @param s the line to write
     */
    @Override
    public void outputLine(String s) {
        System.out.println(s);
    }
}
