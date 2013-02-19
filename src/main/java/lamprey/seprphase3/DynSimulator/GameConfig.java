package lamprey.seprphase3.DynSimulator;

import eel.seprphase2.Utilities.*;
import lamprey.seprphase3.Utilities.*;
import static lamprey.seprphase3.Utilities.Units.*;
import static eel.seprphase2.Utilities.Units.*;

/**
 * Various configurable constants that affect gameplay.
 * All in one place for easy configuration - aren't I nice? ;)
 * @author will
 */
public class GameConfig {
    
    // Mass flow rate induced by a running pump.
    public static final MassFlowRate FlowRateInducedByPumps = kilogramsPerSecond(10);
    
    // Default maximum flow rate through a valve.
    public static final MassFlowRate DefaultMaxFlowRateThroughValve = kilogramsPerSecond(40);
    
    // Amount of water in condenser.
    public static final Mass initialWaterInCondenser = kilograms(300);
    
    // Amount of water in Reactor
    public static final Mass initialWaterInReactor = kilograms(1000);
    
    // Max steam condensed per second (When it's COLD!) (Kg/s)
    public static final MassFlowRate MaxSteamCondensedPerSecond = kilogramsPerSecond(5);
    
    // Room temperature in K (25*C)
    public static final Temperature RoomTemperature = kelvin(273.15 + 25);
    
}
