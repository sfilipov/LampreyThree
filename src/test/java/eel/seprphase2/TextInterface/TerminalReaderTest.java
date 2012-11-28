/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import java.io.ByteArrayInputStream;
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
public class TerminalReaderTest {
    
    public TerminalReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private void setInputToString(String s) {
        System.setIn(new ByteArrayInputStream(s.getBytes()));
    }
    
    
    /**
     * Test of readLine method, of class TerminalReader.
     */
    @Test
    public void testReadLine() {
        TerminalReader tr = new TerminalReader();
        setInputToString("test line\n");
        assertEquals("test line", tr.readLine());
    }
    
}
