/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

/**
 * Used to passed information about which component the mechanic is supposed to repair. A value of this
 * enumeration is passed to the repair method in the mechanic
 * 
 * @author Simeon
 */
public enum CurrentlyRepairing {
    Condenser, Turbine, Pump1, Pump2, None;
}
