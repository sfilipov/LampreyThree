package eel.seprphase2.Utilities;

import static java.lang.Math.round;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.*;

/**
 *
 * @author David
 */
@JsonTypeName(value = "Percentage")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@class")
public class Percentage {

    private static final Pattern pattern = Pattern.compile("^([0-9]+(\\.[0-9])?)%?$");
    @JsonProperty
    private final double percentagePoints;

    // default constructor required for serialization
    /**
     *
     */
    public Percentage() {
        percentagePoints = 0;
    }

    /**
     *
     * @param percentagePoints
     */
    public Percentage(double percentagePoints) {
        if (!isValidPercentage(percentagePoints)) {
            throw new IllegalArgumentException("The argument (" +
                                               percentagePoints +
                                               ") is outside the valid range" +
                                               " for percentages [0 - 100]");
        }
        this.percentagePoints = percentagePoints;
    }

    /**
     *
     * @param ratio
     *
     * public Percentage(double ratio) { this((int)round(ratio * 100)); }
     */
    /**
     *
     * @param representation
     */
    public Percentage(String representation) {
        this(pointsFromString(representation));
    }

    /**
     *
     * @return
     */
    public double points() {
        return this.percentagePoints;
    }

    /**
     *
     * @return
     */
    public double ratio() {
        return this.percentagePoints / 100.0;
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Percentage plus(Percentage other) {
        double result = percentagePoints + other.percentagePoints;
        // cope with FP error causing out-of-range sums
        if (!isValidPercentage(result)) {
            result = Math.round(result);
        }
        return new Percentage(result);
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Percentage minus(Percentage other) {
        double result = percentagePoints - other.percentagePoints;
        // cope with FP error causing out-of-range sums
        if (!isValidPercentage(result)) {
            result = Math.round(result);
        }
        return new Percentage(result);
    }   

    @Override
    public String toString() {
        return Format.toOneDecimalPlace(percentagePoints) + "%";
    }

    @Override
    public int hashCode() {
        return ((int)round(this.percentagePoints));
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

    /**
     *
     * @param points
     *
     * @return
     */   
    public static boolean isValidPercentage(double points) {
        return points <= 100 && points >= 0;
    }

    /**
     *
     * @param representation
     *
     * @return
     */
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
