/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator.FailureModel;

/**
 *
 * @author James
 */
public class CannotControlException extends Exception {
    public CannotControlException(String message)
    {
        super(message);
    }
}
