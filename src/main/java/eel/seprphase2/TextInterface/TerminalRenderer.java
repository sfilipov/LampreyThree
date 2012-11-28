/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

/**
 *
 * @author david
 */
public class TerminalRenderer implements TextRenderer {

    @Override
    public void outputLine(String s) {
        System.out.println(s);
    }
}
