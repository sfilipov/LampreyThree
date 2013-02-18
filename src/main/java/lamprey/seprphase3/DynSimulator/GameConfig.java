package lamprey.seprphase3.DynSimulator;

import lamprey.seprphase3.Utilities.MassFlowRate;
import static lamprey.seprphase3.Utilities.Units.*;

/**
 * Various configurable constants that affect gameplay.
 * All in one place for easy configuration - aren't I nice? ;)
 * @author will
 */
public class GameConfig {
    
    // Mass flow rate induced by a running pump.
    public static final MassFlowRate FlowRateInducedByPumps = kilogramsPerSecond(0.5);
    
}
