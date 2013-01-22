package eel.seprphase2.TextInterface;

/**
 *
 * @author David
 */
public class TerminalRenderer implements TextRenderer {

    /**
     *
     * @param s
     */
    @Override
    public void outputLine(String s) {
        System.out.println(s);
    }
}
