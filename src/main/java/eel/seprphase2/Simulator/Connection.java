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
    private Port input;
    @JsonProperty
    private Port output;
    @JsonProperty
    private double area;
    @JsonProperty
    private Mass buildUp = kilograms(0);

    // default constructor for JSON deserialization
    private Connection() {
        super();
        input = null;
        output = null;
        area = 0;
    }

    public Connection(Port input, Port output, double area) {
        this.input = input;
        this.output = output;
        this.area = area;
    }

    /**
     *
     */
    public void step() {
        if (this.getOpen()) {
            output.mass = buildUp.plus(input.mass);
            output.temperature = input.temperature;
            output.pressure = pascals(output.pressure.inPascals() - output.pressure.inPascals() * 4 / 3);
            output.flow = input.flow;
            input.mass = kilograms(0);
            buildUp = kilograms(0);
        } else {
            output.mass = kilograms(0);
            output.temperature = input.temperature;
            output.pressure = pascals(101325);
            output.flow = kilograms(0);
            buildUp = buildUp.plus(input.mass);
        }
    }
}
