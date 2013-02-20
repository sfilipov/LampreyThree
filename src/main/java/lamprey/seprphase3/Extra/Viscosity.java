package lamprey.seprphase3.Extra;

import eel.seprphase2.Utilities.Format;

/**
 * @author will
 */
public class Viscosity {

    // in Pa·s
    private double pascalSeconds;

    public Viscosity() {
        pascalSeconds = 0;
    }

    /**
     *
     * @param pascalSeconds
     */
    public Viscosity(double pascalSeconds) {
        this.pascalSeconds = pascalSeconds;
    }

    /**
     *
     * @return
     */
    public double inPascalSeconds() {
        return pascalSeconds;
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Viscosity plus(Viscosity other) {
        return new Viscosity(this.pascalSeconds + other.inPascalSeconds());
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public Viscosity minus(Viscosity other) {
        return new Viscosity(this.pascalSeconds - other.inPascalSeconds());
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(this.pascalSeconds) + " Pa·s";
    }

    @Override
    public int hashCode() {
        return (int)this.pascalSeconds;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Viscosity other = (Viscosity)obj;
        if (Double.doubleToLongBits(this.pascalSeconds) != Double.doubleToLongBits(other.inPascalSeconds())) {
            return false;
        }
        return true;
    }
}

