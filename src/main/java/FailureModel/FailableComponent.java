/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FailureModel;

/**
 *
 * @author Yazidi
 */
public abstract class FailableComponent {
    private FailureState failureState;
    
    public FailableComponent()
    {
        failureState = FailureState.Normal;
    }
    
    public FailureState getFailureState()
    {
        return this.failureState;
    }
    
    public void setFailureState(FailureState newFailureState){
        this.failureState=newFailureState;
    }
}
