/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

/**
 *
 * @author david
 */
public class MockReader implements LineReader {

    String nextLine = "";
    
    public void giveLine(String line) {
        nextLine = line;
    }
    
    @Override
    public String readLine() {
        return nextLine;
    }
    
}
