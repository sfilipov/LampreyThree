/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

/**
 *
 * @author david
 */
public class Temperature {

    private int degreesCelsius;

    public Temperature(int degreesCelsius) {
        this.degreesCelsius = degreesCelsius;
    }

    int degreesCelsius() {
        return this.degreesCelsius;
    }

    @Override
    public String toString() {
        return degreesCelsius + " degrees C";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Temperature other = (Temperature)obj;
        if (this.degreesCelsius != other.degreesCelsius) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.degreesCelsius;
    }
}
