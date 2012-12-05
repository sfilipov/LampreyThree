/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.*;
import static org.junit.Assert.*;

import static eel.seprphase2.Utilities.Units.*;
import org.junit.Ignore;

/**
 *
 * @author drm511
 */
public class PersistenceTest {
    
    Persistence persistence;
    
    @Before
    public void setUp() {
        persistence = new Persistence();
    }
    
    @Test
    public void shouldSerializePercentage() throws JsonProcessingException {
        String result = persistence.serialize(percent(50));
        assertEquals("{\"percentagePoints\":50}", result);
    }
    
    @Ignore @Test
    public void shouldSerializeDensity() throws JsonProcessingException {
        String result = persistence.serialize(kilogramsPerCubicMetre(10));
        assertEquals("(\"\"", "");
    }
    
    @Ignore @Test
    public void shouldSerializePort() throws JsonProcessingException {
        String result = persistence.serialize(new Port());
        assertNotSame("", result);
    }
}
