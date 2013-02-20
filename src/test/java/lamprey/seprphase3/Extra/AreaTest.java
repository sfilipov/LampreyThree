package lamprey.seprphase3.Extra;

import lamprey.seprphase3.Extra.Area;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author will
 */
public class AreaTest {
    
    @Test
    public void Area_initialValueShouldBe0IfUnspecified(){
        Area instance = new Area();
        double expResult = 0.0;
        double result = instance.inMetresSquared();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void Area_instantiatedValueIsCorrect() {
        Area instance = new Area(20.10);
        double expResult = 20.10;
        double result = instance.inMetresSquared();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test 
    public void Area_plus() {
        Area instance1 = new Area(20);
        Area instance2 = new Area(10);
        double expResult = 30.0;
        double result = instance1.plus(instance2).inMetresSquared();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void Area_minus() {
        Area instance1 = new Area(20);
        Area instance2 = new Area(10);
        double expResult = 10.0;
        double result = instance1.minus(instance2).inMetresSquared();
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void Area_diameterOfCircleFromArea() {
        double radius = 10;
        Area instance = new Area(Math.PI*Math.pow(radius,2));
        double expResult = 20;
        double result = instance.diameterOfCircluarArea();
        assertEquals(expResult, result, 0.0);
    }
    
    
}
