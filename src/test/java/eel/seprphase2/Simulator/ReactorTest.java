package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Yazidi
 */
public class ReactorTest {

    private final Reactor reactor = new Reactor();

    @Test
    public void controlRodsShouldStartAt0Percent() {
        assertEquals(new Percentage(0), reactor.controlRodPosition());
    }

    @Test
    public void controlRodsShouldMove() {
        reactor.moveControlRods(new Percentage(50));
        assertEquals(new Percentage(50), reactor.controlRodPosition());
    }

    @Test
    public void initialWaterLevelShouldBe100() {
        assertEquals(new Percentage(100), reactor.waterLevel());
    }

    @Test
    public void initialTemperatureShouldBe25Degrees() {
        assertEquals(new Temperature(25), reactor.temperature());
    }

    @Test
    public void initialPressureShouldBe() {
        assertEquals(new Pressure(101325), reactor.pressure());
    }
}
