/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import java.text.DecimalFormat;

/**
 *
 * @author drm
 */
public class Format {

    public static String toThreeDecimalPlaces(double number) {
        DecimalFormat form = new DecimalFormat("#.###");
        return form.format(number);
    }
}
