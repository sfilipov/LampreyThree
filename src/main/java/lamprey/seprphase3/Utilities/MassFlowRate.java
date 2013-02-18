/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.Utilities;

import eel.seprphase2.Utilities.Format;

/**
 *
 * @author will
 */
public class MassFlowRate {

    // in kg/s
    private double kilogramsPerSecond;

    public MassFlowRate() {
        kilogramsPerSecond = 0;
    }

    /**
     *
     * @param kilogramsPerSecond
     */
    public MassFlowRate(double kilogramsPerSecond) {
        this.kilogramsPerSecond = kilogramsPerSecond;
    }

    /**
     *
     * @return
     */
    public double inKilogramsPerSecond() {
        return kilogramsPerSecond;
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public MassFlowRate plus(MassFlowRate other) {
        return new MassFlowRate(this.kilogramsPerSecond + other.inKilogramsPerSecond());
    }

    /**
     *
     * @param other
     *
     * @return
     */
    public MassFlowRate minus(MassFlowRate other) {
        return new MassFlowRate(this.kilogramsPerSecond - other.inKilogramsPerSecond());
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(this.kilogramsPerSecond) + " kg/s";
    }

    @Override
    public int hashCode() {
        return (int)this.kilogramsPerSecond;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MassFlowRate other = (MassFlowRate)obj;
        if (Double.doubleToLongBits(this.kilogramsPerSecond) != Double.doubleToLongBits(other.inKilogramsPerSecond())) {
            return false;
        }
        return true;
    }
}
