package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.*;
import static eel.seprphase2.Utilities.Units.*;

/**
 * Fluid flow connection between two components.
 * 
 * Optionally, can be turned on/off to form a valve.
 * 
 * @author David
 */
public class Connection extends Valve {

    @JsonProperty
    private Port first;
    @JsonProperty
    private Port second;
    @JsonProperty
    private double area;
    @JsonProperty
    private Mass buildUp = kilograms(0);

    // default constructor for JSON deserialization
    private Connection() {
        super();
        first = null;
        second = null;
        area = 0;
    }

    public Connection(Port input, Port output, double area) {
        first = input;
        second = output;
        this.area = area;
    }

    /**
     *
     */
    public void step() {
        if (this.getOpen()) {
            second.mass = buildUp.plus(first.mass);
            second.temperature = first.temperature;
            second.pressure = pascals(second.pressure.inPascals() - second.pressure.inPascals() * 4 / 3);
            second.flow = first.flow;
            first.mass = kilograms(0);
            buildUp = kilograms(0);
        } else {
            second.mass = kilograms(0);
            second.temperature = first.temperature;
            second.pressure = pascals(101325);
            second.flow = kilograms(0);
            buildUp = buildUp.plus(first.mass);
        }
    }
}
