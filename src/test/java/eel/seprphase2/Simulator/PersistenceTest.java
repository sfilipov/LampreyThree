/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import eel.seprphase2.Utilities.Percentage;
import org.junit.*;
import static org.junit.Assert.*;

import static eel.seprphase2.Utilities.Units.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Ignore;

/**
 *
 * @author drm511
 */
public class PersistenceTest {

    Persistence persistence;
    PhysicalModel before;
    PhysicalModel after;
    
    
    @Before
    public void setUp() {
        persistence = new Persistence();
        before = new PhysicalModel();
    }

    @Test
    public void shouldSerializePercentage() throws JsonProcessingException {
        String result = persistence.serialize(percent(50));
        assertEquals("{\"@class\":\"Percentage\",\"percentagePoints\":50}", result);
    }

    @Test
    public void shouldSerializeDensity() throws JsonProcessingException {
        String result = persistence.serialize(kilogramsPerCubicMetre(10));
        assertEquals("{\"@class\":\"Density\",\"kilogramsPerCubicMetre\":10.0}", result);
    }

    @Test
    public void shouldSerializeTemperature() throws JsonProcessingException {
        String result = persistence.serialize(kelvin(300));
        assertEquals("{\"@class\":\"Temperature\",\"degreesKelvin\":300.0}", result);
    }

    @Test
    public void shouldSerializeMass() throws JsonProcessingException {
        String result = persistence.serialize(kilograms(40));
        assertEquals("{\"@class\":\"Mass\",\"kilograms\":40.0}", result);
    }

    @Test
    public void shouldSerializeEnergy() throws JsonProcessingException {
        String result = persistence.serialize(joules(5));
        assertEquals("{\"@class\":\"Energy\",\"joules\":5.0}", result);
    }

    @Test
    public void shouldSerializePort() throws JsonProcessingException {
        String result = persistence.serialize(new Port());
        assertNotSame("", result);
    }

    @Test
    public void shouldSerializeFuelPile() throws JsonProcessingException {
        String result = persistence.serialize(new FuelPile());
        assertEquals("{\"@class\":\"FuelPile\",\"controlRodPosition\":{\"@class\":\"Percentage\",\"percentagePoints\":0}}", result);
    }

    @Test
    public void shouldSerializeReactor() throws JsonProcessingException {
        String result = persistence.serialize(new Reactor());
        assertNotSame("", result);
    }

    @Test
    public void shouldSerializePhysicalModel() throws JsonProcessingException {
        String result = persistence.serialize(new PhysicalModel());
        assertNotSame("", result);
    }

    @Ignore    @Test
    public void shouldDeserializePhysicalModel() throws JsonParseException, JsonMappingException, IOException {
        PhysicalModel physicalModel = persistence.deserializePhysicalModel("{\"reactor\":{\"failureState\":\"Normal\",\"wear\":{\"@class\":\"Percentage\",\"percentagePoints\":0},\"fuelPile\":{\"@class\":\"FuelPile\",\"controlRodPosition\":{\"@class\":\"Percentage\",\"percentagePoints\":0}},\"waterMass\":{\"@class\":\"Mass\",\"kilograms\":1000.0},\"steamMass\":{\"@class\":\"Mass\",\"kilograms\":0.0},\"temperature\":{\"@class\":\"Temperature\",\"degreesKelvin\":350.0},\"pressure\":{\"pascals\":101325.0},\"steamDensity\":null,\"outputPort\":{\"@id\":1,\"density\":{\"@class\":\"Density\",\"kilogramsPerCubicMetre\":1000.0},\"pressure\":{\"pascals\":101325.0},\"temperature\":{\"@class\":\"Temperature\",\"degreesKelvin\":300.0},\"mass\":{\"@class\":\"Mass\",\"kilograms\":0.0}}},\"turbine\":{\"failureState\":\"Normal\",\"wear\":{\"@class\":\"Percentage\",\"percentagePoints\":0},\"outputPower\":0.0,\"inputPort\":{\"@id\":2,\"density\":{\"@class\":\"Density\",\"kilogramsPerCubicMetre\":1000.0},\"pressure\":{\"pascals\":101325.0},\"temperature\":{\"@class\":\"Temperature\",\"degreesKelvin\":300.0},\"mass\":{\"@class\":\"Mass\",\"kilograms\":0.0}},\"outputPort\":{\"@id\":3,\"density\":{\"@class\":\"Density\",\"kilogramsPerCubicMetre\":1000.0},\"pressure\":{\"pascals\":101325.0},\"temperature\":{\"@class\":\"Temperature\",\"degreesKelvin\":300.0},\"mass\":{\"@class\":\"Mass\",\"kilograms\":0.0}}},\"energyGenerated\":{\"@class\":\"Energy\",\"joules\":0.0},\"reactorToTurbine\":{\"first\":1,\"second\":2,\"area\":0.04}}");
        assertNotNull(physicalModel);
    }
    
    @Test
    public void shouldPersistPercentage() throws JsonProcessingException, IOException {
        Percentage before = percent(50);
        String result = persistence.serialize(before);
        Percentage after = persistence.deserializePercentage(result);
        assertEquals(before, after);
        assertNotSame(before, after);
    }
    
    @Test
    public void shouldPersistPhysicalModel() throws JsonProcessingException, IOException {
        shouldPersistConsistently();
    }

    @Test
    public void shouldPersistChangesToControlRodPosition() throws IOException, JsonProcessingException {
        before.moveControlRods(percent(57));
        shouldPersistConsistently();
    }
    
    @Test
    public void shouldPersistConditions() {
        before.moveControlRods(percent(67));
        before.step(100);
        shouldPersistConsistently();
    }
    
    private void backAndForthPersistence() {
        try {
            String representation = persistence.serialize(before);
            after = persistence.deserializePhysicalModel(representation);
        } catch (JsonParseException ex) {
            Logger.getLogger(PersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonMappingException ex) {
            Logger.getLogger(PersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void shouldPersistConsistently() {
        backAndForthPersistence();
        assertNotNull(after);
        assertNotSame(before, after);
        assertEquals(before.controlRodPosition(), after.controlRodPosition());
        assertEquals(before.energyGenerated(), after.energyGenerated());
        assertEquals(before.reactorPressure(), after.reactorPressure());
        assertEquals(before.reactorTemperature(), after.reactorTemperature());
        assertEquals(before.reactorWaterLevel(), after.reactorWaterLevel());
    }
    
}
