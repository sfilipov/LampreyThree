/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class TerminalRendererTest {

    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Test of output method, of class TerminalRenderer.
     */
    @Test
    public void testOutput() {
        TerminalRenderer tr = new TerminalRenderer();
        tr.outputLine("Test message");
        assertEquals("Test message" + System.getProperty("line.separator"), outContent.toString());
    }
}
