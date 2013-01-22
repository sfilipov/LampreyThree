/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
