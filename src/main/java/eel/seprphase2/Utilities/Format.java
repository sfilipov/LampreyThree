package eel.seprphase2.Utilities;

import java.text.DecimalFormat;

/**
 * Utility functions for rounded decimal representations of floating-point numbers
 *
 * @author David
 */
public class Format {

    /**
     * Format a number to 3 decimal places
     *
     * Only shows the number of decimal places which are needed (trailing zeroes are not shown)
     *
     * @param number
     *
     * @return the number formatted to 3dp
     */
    public static String toThreeDecimalPlaces(double number) {
        DecimalFormat form = new DecimalFormat("#.###");
        return form.format(number);
    }

    /**
     * Format a number to one decimal place
     *
     * Only shows the number of decimal places which are needed (trailing zeroes are not shown)
     *
     * @param number
     *
     * @return the number formatted to 3dp
     */
    public static String toOneDecimalPlace(double number) {
        DecimalFormat form = new DecimalFormat("#.#");
        return form.format(number);
    }
}
