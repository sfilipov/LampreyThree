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
    
    // ---------- GENERAL CONFIG ---------- 
    // Room temperature in K (25*C)
    public static final Temperature ROOMTEMPERATURE = kelvin(298.15);
    // -- END  -- GENERAL CONFIG ----------
    
    // ---------- FLOW CONFIG ---------- 
    // Mass flow rate induced by a running pump.
    public static final MassFlowRate PUMP_INDUCEDFLOWRATE = kilogramsPerSecond(50);
    // Default maximum flow rate through a valve (Can be redefined on a per valve basis).
    public static final MassFlowRate VALVE_DEFAULTMAXTHROUGHPUT = kilogramsPerSecond(50);    
    // -- END  -- FLOW CONFIG ----------
 
    // ---------- CONDENSER CONFIG ---------- 
    public static final Mass CONDENSER_INITIALWATERMASS = kilograms(800);
    public static final Volume CONDENSER_VOLUME = cubicMetres(2); 
    // Max steam condensed per second (When it's COLD!) (Kg/s)
    public static final MassFlowRate CONDENSER_MAXSTEAMCONDENSEDPERSEC = kilogramsPerSecond(5);
    public static final Pressure CONDENSER_MAXIMUMPRESSURE = pascals(30662500);
    // -- END  -- CONDENSER CONFIG ---------- 

    // ---------- REACTOR CONFIG ---------- 
    public static final Mass REACTOR_INITIALWATERMASS = kilograms(2500);
    public static final Volume REACTOR_VOLUME = cubicMetres(3);
    public static final Percentage REACTOR_MINIMUMSAFEWATERLEVEL = percent(50);
    public static final Temperature REACTOR_MAXIMUMTEMPERATURE = kelvin(500);
    public static final Pressure REACTOR_MAXIMUMPRESSURE = pascals(30662500);
    public static final double REACTOR_EVAPORATEMULTIPLIER = 15;
    // -- END  -- REACTOR CONFIG ---------- 
    
    
}
