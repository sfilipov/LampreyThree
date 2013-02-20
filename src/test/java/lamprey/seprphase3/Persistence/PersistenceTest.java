package lamprey.seprphase3.Persistence;

import eel.seprphase2.Simulator.GameOverException;
import eel.seprphase2.Simulator.PhysicalModel;
import org.junit.*;
import static org.junit.Assert.*;

import static eel.seprphase2.Utilities.Units.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lamprey.seprphase3.DynSimulator.FluidFlowController;
import lamprey.seprphase3.DynSimulator.PlantModel;
import org.junit.Ignore;

/**
 *
 * @author David
 */
public class PersistenceTest {
    private Persistence persistence;
    private PhysicalModel statusBefore;
    private PhysicalModel statusAfter;
    private FluidFlowController controller;
    private PlantModel before;
    private PlantModel after;
    private SaveGame saveGame;
    


    @Before
    public void setUp() {
        persistence = new Persistence();
        before = new PlantModel();
        controller = new FluidFlowController(before);
    }

//    @Test
//    public void shouldSerializePercentage() throws JsonProcessingException {
//        String result = persistence.serialize(percent(50));
//        assertEquals("{\"@class\":\"Percentage\",\"percentagePoints\":50.0}", result);
//    }
//
//    @Test
//    public void shouldSerializeDensity() throws JsonProcessingException {
//        String result = persistence.serialize(kilogramsPerCubicMetre(10));
//        assertEquals("{\"@class\":\"Density\",\"kilogramsPerCubicMetre\":10.0}", result);
//    }
//
//    @Test
//    public void shouldSerializeTemperature() throws JsonProcessingException {
//        String result = persistence.serialize(kelvin(300));
//        assertEquals("{\"@class\":\"Temperature\",\"degreesKelvin\":300.0}", result);
//    }
//
//    @Test
//    public void shouldSerializeMass() throws JsonProcessingException {
//        String result = persistence.serialize(kilograms(40));
//        assertEquals("{\"@class\":\"Mass\",\"kilograms\":40.0}", result);
//    }
//
//    @Test
//    public void shouldSerializeEnergy() throws JsonProcessingException {
//        String result = persistence.serialize(joules(5));
//        assertEquals("{\"@class\":\"Energy\",\"joules\":5.0}", result);
//    }
//
//    @Test
//    public void shouldSerializePort() throws JsonProcessingException {
//        String result = persistence.serialize(new Port());
//        assertNotSame("", result);
//    }
//
//    @Test
//    public void shouldSerializeFuelPile() throws JsonProcessingException {
//        String result = persistence.serialize(new FuelPile());
//        assertEquals(
//                "{\"@class\":\"FuelPile\",\"controlRodPosition\":{\"@class\":\"Percentage\",\"percentagePoints\":0.0}}",
//                result);
//    }
//
//    @Test
//    public void shouldSerializeReactor() throws JsonProcessingException {
//        String result = persistence.serialize(new Reactor());
//        assertNotSame("", result);
//    }
//
//    @Test
//    public void shouldSerializePlantModel() throws JsonProcessingException {
//        String result = persistence.serialize(new PlantModel());
//        assertNotSame("", result);
//    }
//
//    @Test
//    public void shouldPersistPercentage() throws JsonProcessingException, IOException {
//        Percentage before = percent(50);
//        String result = persistence.serialize(before);
//        Percentage after = persistence.deserializePercentage(result);
//        assertEquals(before, after);
//        assertNotSame(before, after);
//    }

    @Test
    public void shouldPersistPlantModel() throws IOException {
        shouldPersistConsistently();
    }

    @Test
    public void shouldPersistChangesToControlRodPosition() throws IOException {
        controller.moveControlRods(percent(57));
        shouldPersistConsistently();
    }

    @Test
    public void shouldPersistConditions() throws GameOverException {
        controller.moveControlRods(percent(67));
        controller.step(100); // changes the state of the plant, bare.
        shouldPersistConsistently();
    }

    private void backAndForthPersistence() {
        try {
            saveGame = new SaveGame(before, "");
            assertTrue(saveGame.save());
            after = SaveGame.load().getPlantModel();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void shouldPersistConsistently() {
        backAndForthPersistence();
        assertNotNull(after);
        assertNotSame(before, after);
        statusBefore = new PhysicalModel(before);
        statusAfter = new PhysicalModel(after);
        assertEquals(statusBefore.controlRodPosition(), statusAfter.controlRodPosition());
        assertEquals(statusBefore.energyGenerated(), statusAfter.energyGenerated());
        assertEquals(statusBefore.reactorPressure(), statusAfter.reactorPressure());
        assertEquals(statusBefore.reactorTemperature(), statusAfter.reactorTemperature());
        assertEquals(statusBefore.reactorWaterLevel(), statusAfter.reactorWaterLevel());
    }
}
