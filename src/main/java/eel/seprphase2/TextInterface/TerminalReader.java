package eel.seprphase2.TextInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Concrete implementation of LineReader for use with Standard Input
 * @author David
 */
public class TerminalReader implements LineReader {

    /**
     * Read a line from the terminal
     * @return the line read from the terminal
     */
    @Override
    public String readLine() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException ex) {
        }
        return line;
    }
}
