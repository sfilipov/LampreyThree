/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator.FailureModel;

/**
 *
 * @author James
 */
public class CannotRepairException extends ControlException {
    public CannotRepairException (String message) {
        super(message);
    }
}
