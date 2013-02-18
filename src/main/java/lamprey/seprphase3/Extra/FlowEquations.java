
package lamprey.seprphase3.Extra;

import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Velocity;
import lamprey.seprphase3.Utilities.MassFlowRate;
import lamprey.seprphase3.Utilities.VolumetricFlowRate;

/**
 * Equations for calculating flow rates.
 * 
 * @author will
 */
public class FlowEquations {
   
    /**
     * Returns the mass flow rate in a pipe given the density, cross sectional area and velocity.
     * @param density
     * @param crossSectionalArea
     * @param velocity
     * @return mass flow rate.
     */
    public static MassFlowRate massFlowRate(Density density, Area crossSectionalArea, Velocity velocity) {
        return new MassFlowRate(density.inKilogramsPerCubicMetre() * crossSectionalArea.inMetresSquared() * velocity.inMetresPerSecond());
    }
    
    
    public static Velocity velocityInPipe(Pressure pressure, Area crossSectionalArea, double pipeLength, Mass gasInPipe) {
        double v = (pressure.inPascals() * crossSectionalArea.inMetresSquared() * pipeLength) / gasInPipe.inKilograms();
        return new Velocity(v);
    }
    
    public static VolumetricFlowRate flowRate(Pressure deltaP, Area a1, Area a2, Density d) {
        return new VolumetricFlowRate(a2.inMetresSquared() * Math.sqrt(((2*deltaP.inPascals())/d.inKilogramsPerCubicMetre())/(1-Math.pow((a2.inMetresSquared()/a1.inMetresSquared()),2))));
    }
    
}
