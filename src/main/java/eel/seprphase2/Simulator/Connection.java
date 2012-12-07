/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import eel.seprphase2.Utilities.*;
import static eel.seprphase2.Utilities.Units.*;


/**
 *
 * @author david
 */
public class Connection {

    @JsonProperty
    private Port first;
    @JsonProperty
    private Port second;
    @JsonProperty
    private double area;

    
    // default constructor for JSON deserialization
    private Connection() {
        first = null;
        second = null;
        area = 0;
    }
    
    public Connection(Port first, Port second, double area) {
        this.first = first;
        this.second = second;
        this.area = area;
    }

    public void step() {
        Port input, output;
        if (first.pressure.greaterThan(second.pressure)) {
            input = first;
            output = second;
        } else {
            input = second;
            output = first;
        }
        Pressure deltaPressure = input.pressure.minus(output.pressure);
        Velocity flowVelocity = Bernoulli.velocity(deltaPressure, input.density);
        Mass deltaMass = kilograms(input.density.inKilogramsPerCubicMetre() *
                                   flowVelocity.inMetresPerSecond() *
                                   area);
        input.mass = input.mass.minus(deltaMass);
        output.mass = output.mass.plus(deltaMass);
        output.pressure = input.pressure;
    }
}
