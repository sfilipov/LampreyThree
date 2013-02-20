package lamprey.seprphase3.Extra;

import lamprey.seprphase3.Extra.DynamicViscosity;
import eel.seprphase2.Utilities.Pressure;
import eel.seprphase2.Utilities.Temperature;
import eel.seprphase2.Simulator.PhysicalConstants;
import eel.seprphase2.Utilities.Density;
import lamprey.seprphase3.Extra.Viscosity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author will
 */
public class DynamicViscosityTest {
    
    private double accuracy = 0.00005;

    @Test
    public void DynamicViscosity_correctViscosityRoomTemperatureWater() {
        double expResult = 8.9E-4; // in Pa·s
        Temperature roomTemperature = new Temperature(273.15+25); // 25 C
        Pressure normalPressure = new Pressure(PhysicalConstants.atmosphericPressure);
        Density normalDensity = new Density(); // assumes liquid water
        Viscosity result = DynamicViscosity.dynamicViscosity(roomTemperature,                                                          
                                                          normalDensity);
        assertEquals(expResult, result.inPascalSeconds(), accuracy);
    }
    
    @Test
    public void DynamicViscosity_correctViscosityBoilingWater() {
        double expResult = 2.822E-4; // in Pa·s
        Temperature roomTemperature = new Temperature(373.15); // 100 C
        Pressure normalPressure = new Pressure(PhysicalConstants.atmosphericPressure);
        Density normalDensity = new Density(); // assumes liquid water
        Viscosity result = DynamicViscosity.dynamicViscosity(roomTemperature,                                                            
                                                          normalDensity);
        assertEquals(expResult, result.inPascalSeconds(), accuracy);
    }
    
    @Test
    public void DynamicViscosity_correctViscositySteamAtBoilingPoint() {
        double expResult = 1.22684E-5; // in Pa·s
        Temperature roomTemperature = new Temperature(373.15); // 100 C
        Pressure normalPressure = new Pressure(PhysicalConstants.atmosphericPressure);
        Density normalDensity = new Density(0.597518);
        Viscosity result = DynamicViscosity.dynamicViscosity(roomTemperature,                                                           
                                                          normalDensity);
        assertEquals(expResult, result.inPascalSeconds(), accuracy);
    }
    
    @Test
    public void DynamicViscosity_correctViscositySteamAtHighPressure() {
        double expResult = 0.032E-3; // in Pa·s
        Temperature roomTemperature = new Temperature(811); 
        Pressure normalPressure = new Pressure(3447501);
        Density normalDensity = new Density(56.3896);
        Viscosity result = DynamicViscosity.dynamicViscosity(roomTemperature,                                                           
                                                          normalDensity);
        assertEquals(expResult, result.inPascalSeconds(), accuracy);
    }
    
    
    
    
    
}
