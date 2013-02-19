
package lamprey.seprphase3.DynSimulator;

import eel.seprphase2.Utilities.Density;
import eel.seprphase2.Utilities.Mass;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Velocity;
import lamprey.seprphase3.Extra.Area;
import lamprey.seprphase3.Utilities.MassFlowRate;
import lamprey.seprphase3.Utilities.VolumetricFlowRate;
import static lamprey.seprphase3.Utilities.Units.kilogramsPerSecond;

/**
 * Equations for calculating flow rates.
 * 
 * @author will
 */
public class FlowEquations {
   
    public static MassFlowRate flowRateFromPressure(Pressure deltaP) {
        MassFlowRate flowPer50Atm = kilogramsPerSecond(10);
        MassFlowRate resultingFlow = kilogramsPerSecond(flowPer50Atm.inKilogramsPerSecond() * (Math.log(deltaP.inAtmospheres()/Math.log(50))));
        return resultingFlow;
    }
}
