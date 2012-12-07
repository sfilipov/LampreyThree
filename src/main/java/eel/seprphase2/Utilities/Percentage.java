/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Utilities;

import static java.lang.Math.round;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.*;

/**
 *
 * @author drm
 */
@JsonTypeName(value="Percentage")
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, property="@class")
public class Percentage {

    private static final Pattern pattern = Pattern.compile("^([0-9]+)%?$");
    @JsonProperty
    private final int percentagePoints;

    // default constructor required for serialization
    public Percentage() {
        percentagePoints = 0;
    }
    
    public Percentage(int percentagePoints) {
        if (!isValidPercentage(percentagePoints)) {
            throw new IllegalArgumentException("The argument (" +
                                               percentagePoints +
                                               ") is outside the valid range" +
                                               " for percentages [0 - 100]");
        }
        this.percentagePoints = percentagePoints;
    }

    public Percentage(double ratio) {
        this((int)round(ratio * 100));
    }

    public Percentage(String representation) {
        this(pointsFromString(representation));
    }

    public int points() {
        return this.percentagePoints;
    }

    public double ratio() {
        return this.percentagePoints / 100.0;
    }
    
    public Percentage plus(Percentage other) {
        return new Percentage(percentagePoints + other.percentagePoints);
    }

    public Percentage minus(Percentage other) {
        return new Percentage(percentagePoints - other.percentagePoints);
    }

    @Override
    public String toString() {
        return percentagePoints + "%";
    }

    @Override
    public int hashCode() {
        return this.percentagePoints;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Percentage other = (Percentage)obj;
        if (this.percentagePoints != other.percentagePoints) {
            return false;
        }
        return true;
    }

    public static boolean isValidPercentage(int points) {
        return points <= 100 && points >= 0;
    }

    public static boolean isValidPercentage(String representation) {
        return isWellFormedPercentage(representation) &&
               isValidPercentage(pointsFromString(representation));
    }

    private static boolean isWellFormedPercentage(String representation) {
        Matcher m = pattern.matcher(representation);
        return m.matches();
    }

    private static int pointsFromString(String representation) {
        Matcher m = pattern.matcher(representation);
        if (!m.matches()) {
            throw new IllegalArgumentException(
                    "The string '" +
                    representation +
                    "' is not a well-formed percentage.");
        }
        return Integer.parseInt(m.group(1));
    }
}
