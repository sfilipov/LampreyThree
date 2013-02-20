package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import eel.seprphase2.Utilities.Percentage;
import static eel.seprphase2.Utilities.Units.percent;
/**
 *
 * @author Marius
 */
@JsonTypeName(value = "FuelPile")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class FuelPile {

    private final int maximumOutput = 20000000;
    @JsonProperty
    private Percentage controlRodPosition = new Percentage(0);

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
