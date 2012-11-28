/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import java.util.ArrayList;
import static org.junit.Assert.*;


/**
 *
 * @author Yazidi
 */
class MockRenderer implements TextRenderer {
    private ArrayList<String> strings;

    public MockRenderer() {
        strings = new ArrayList<String>();
    }

    @Override
    public void outputLine(String s) {
        strings.add(s);
    }

    public void hasOnly(String expected) {
        assertEquals(1, strings.size());
        assertEquals(expected, strings.get(0));
    }
    
    public void hasNone() {
        assertEquals(0, strings.size());
    }
    
}
