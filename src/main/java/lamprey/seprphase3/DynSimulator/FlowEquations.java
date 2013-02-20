
package lamprey.seprphase3.DynSimulator;

import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Velocity;
import lamprey.seprphase3.Extra.Area;
import lamprey.seprphase3.Utilities.MassFlowRate;
import lamprey.seprphase3.Utilities.VolumetricFlowRate;
import static lamprey.seprphase3.Utilities.Units.kilogramsPerSecond;
import static eel.seprphase2.Utilities.Units.*;

/**
 * Equations for calculating flow rates.
 * 
 * @author will
 */
public class FlowEquations {
   
    public static Velocity velocityFromPressureDiff(Pressure deltaP) {
        Velocity resultingFlow = metresPerSecond(deltaP.inAtmospheres() / 30);
        return resultingFlow;
    }
    
    public static MassFlowRate flowRateFromDensityVelocityArea(Density density, Velocity velocity, Area area) {
        return kilogramsPerSecond(density.inKilogramsPerCubicMetre() 
                                  * velocity.inMetresPerSecond() 
                                  * area.inMetresSquared());
    }
}
