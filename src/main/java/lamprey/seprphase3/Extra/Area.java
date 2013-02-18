/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.Extra;

import eel.seprphase2.Utilities.Format;

/**
 *
 * @author will
 */
public class Area {

    private double metresSquared;

    public Area() {
        metresSquared = 0.0;
    }

    public Area(double metresSquared) {
        this.metresSquared = metresSquared;
    }

    public double inMetresSquared() {
        return this.metresSquared;
    }
    
    public double diameterOfCircluarArea() {
        return 2 * Math.sqrt(this.metresSquared/Math.PI);
    }

    public Area plus(Area other) {
        return new Area(this.metresSquared + other.inMetresSquared());
    }

    public Area minus(Area other) {
        return new Area(this.metresSquared - other.inMetresSquared());
    }
    
    @Override
    public String toString() {
        return Format.toOneDecimalPlace(this.metresSquared) + " m^2";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Area other = (Area)obj;
        if (Double.doubleToLongBits(this.metresSquared) != Double
                .doubleToLongBits(other.metresSquared)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return (int)this.metresSquared;
    }
}
