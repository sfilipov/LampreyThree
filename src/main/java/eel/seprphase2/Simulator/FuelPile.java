package eel.seprphase2.Simulator;

import eel.seprphase2.Utilities.Percentage;
import static eel.seprphase2.Utilities.Units.percent;
import java.io.Serializable;
/**
 *
 * @author Marius
 */
public class FuelPile implements Serializable {

    private final int maximumOutput = 20000000;
    private Percentage controlRodPosition;

    public FuelPile() {
        controlRodPosition = new Percentage(0);
    }
    
    /**
     *
     * @param extracted
     */
    public void moveControlRods(Percentage extracted) {
        controlRodPosition = extracted;

    }

    /**
     *
     * @param seconds
     *
     * @return
     */
    public double output(double seconds) {
        if (!controlRodPosition.equals(percent(0))) {
            return (double)(maximumOutput * controlRodPosition.ratio() * seconds + 3000000);
        } else {
            return 0.0;
        }
    }

    /**
     *
     * @return
     */
    public Percentage controlRodPosition() {
        return this.controlRodPosition;
    }
}
