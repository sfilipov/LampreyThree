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
public class Connection extends Valve{

    @JsonProperty
    private Port first;
    @JsonProperty
    private Port second;
    @JsonProperty
    private double area;

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

    public void step() {
    
    second.mass = first.mass;   
    second.temperature = first.temperature;
    second.pressure = pascals(second.pressure.inPascals() - second.pressure.inPascals()*4/3);
    
            
    
    }
}
