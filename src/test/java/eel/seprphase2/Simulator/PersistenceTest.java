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
    
    @Test
    public void shouldSerializeDensity() throws JsonProcessingException {
        String result = persistence.serialize(kilogramsPerCubicMetre(10));
        assertEquals("{\"kilogramsPerCubicMetre\":10.0}", result);
    }
    
    @Test
    public void shouldSerializeTemperature() throws JsonProcessingException {
        String result = persistence.serialize(kelvin(300));
        assertEquals("{\"degreesKelvin\":300.0}",result);
    }
    
    @Test
    public void shouldSerializeMass() throws JsonProcessingException {
        String result = persistence.serialize(kilograms(40));
        assertEquals("{\"kilograms\":40.0}",result);
    }
    
    @Test
    public void shouldSerializeEnergy() throws JsonProcessingException {
        String result = persistence.serialize(joules(5));
        assertEquals("{\"joules\":5.0}",result);
    }
    
    @Test
    public void shouldSerializePort() throws JsonProcessingException {
        String result = persistence.serialize(new Port());
        assertNotSame("", result);
    }
    
    @Test
    public void shouldSerializeFuelPile() throws JsonProcessingException {
        String result = persistence.serialize(new FuelPile());
        assertEquals("{\"controlRodPosition\":{\"percentagePoints\":0}}", result);
    }
    
    @Test
    public void shouldSerializeReactor() throws JsonProcessingException {
        String result = persistence.serialize(new Reactor());
        assertNotSame("", result);
    }
    
    @Test
    public void shouldSerializePhysicalModel() throws JsonProcessingException {
        String result = persistence.serialize(new PhysicalModel());
        assertNotSame("",result);
    }
    
}
