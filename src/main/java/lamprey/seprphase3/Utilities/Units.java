/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.Utilities;

/**
 *
 * @author will
 */
public class Units {
    
     /**
     *
     * @param kilogramsPerSecond
     *
     * @return
     */
    public static MassFlowRate kilogramsPerSecond(double kilogramsPerSecond) {
        return new MassFlowRate(kilogramsPerSecond);
    }
    
    
}
