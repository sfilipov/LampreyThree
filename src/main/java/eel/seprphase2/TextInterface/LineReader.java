package eel.seprphase2.TextInterface;

/**
 * Interface for all modules implementing line-oriented input
 * 
 * Primarily exists to promote testability of UI code
 * 
 * @author David
 */
public interface LineReader {

    /**
     * Read a line from the input
     * @return the string read from the input
     */
    public String readLine();
}
