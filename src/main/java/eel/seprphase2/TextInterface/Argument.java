package eel.seprphase2.TextInterface;

import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author drm
 */
public class Argument {
    private String value;
    
    public Argument(String value) {
        this.value = value;
    }
    
    public int asInteger() throws ArgumentConversionException {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ArgumentConversionException("'"+value+"' is not a valid integer");
        }
    }
    
    public Percentage asPercentage() throws ArgumentConversionException {
        try {
            return new Percentage(value);
        } catch (IllegalArgumentException e) {
            throw new ArgumentConversionException("'"+value+"' is not a valid percentage");
        }
    }
    
}
