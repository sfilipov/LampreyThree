/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.Utilities;

import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Format;
import java.io.Serializable;

/**
 * @author will
 */
public class VolumetricFlowRate implements Serializable {
   
    private double cubicMetresPerSecond;
    
    public VolumetricFlowRate() {
        cubicMetresPerSecond = 0;
    }
    
    public VolumetricFlowRate(double cubicMetresPerSecond) {
        this.cubicMetresPerSecond = cubicMetresPerSecond;
    }
    
    public double inCubicMetresPerSecond() {
        return this.cubicMetresPerSecond;
    }
    
    public MassFlowRate massFlowRateForDensity(Density density) {
        return new MassFlowRate(this.cubicMetresPerSecond * density.inKilogramsPerCubicMetre());
    }
    
    public VolumetricFlowRate plus(VolumetricFlowRate other) {
        return new VolumetricFlowRate(this.cubicMetresPerSecond + other.inCubicMetresPerSecond());
    }

    public VolumetricFlowRate minus(VolumetricFlowRate other) {
        return new VolumetricFlowRate(this.cubicMetresPerSecond - other.inCubicMetresPerSecond());
    }

    @Override
    public String toString() {
        return Format.toThreeDecimalPlaces(this.cubicMetresPerSecond) + " m^3/s";
    }

    @Override
    public int hashCode() {
        return (int)this.cubicMetresPerSecond;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VolumetricFlowRate other = (VolumetricFlowRate)obj;
        if (Double.doubleToLongBits(this.cubicMetresPerSecond) != Double.doubleToLongBits(other.inCubicMetresPerSecond())) {
            return false;
        }
        return true;
    }
}
