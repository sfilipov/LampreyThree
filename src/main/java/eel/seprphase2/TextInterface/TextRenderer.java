package eel.seprphase2.TextInterface;

/**
 * Interface provided by all text output modules
 * 
 * Primarily exists to promote testability of UI code.
 * @author Marius
 */
public interface TextRenderer {

    /**
     * Write a line to the output stream.
     * @param s the line to write
     */
    public void outputLine(String s);
}
