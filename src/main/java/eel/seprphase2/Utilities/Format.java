package eel.seprphase2.Utilities;

import java.text.DecimalFormat;

/**
 *
 * @author David
 */
public class Format {

    /**
     *
     * @param number
     *
     * @return
     */
    public static String toThreeDecimalPlaces(double number) {
        DecimalFormat form = new DecimalFormat("#.###");
        return form.format(number);
    }

    public static String toOneDecimalPlace(double number) {
        DecimalFormat form = new DecimalFormat("#.#");
        return form.format(number);
    }
}
