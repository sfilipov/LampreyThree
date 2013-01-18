/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class TerminalReader implements LineReader {

    /**
     *
     * @return
     */
    @Override
    public String readLine() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(TerminalReader.class.getName()).log(Level.SEVERE,
                                                                 null, ex);
        }
        return line;
    }
}
