/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.FailureModel;

import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author Yazidi
 */
public abstract class FailableComponent {
    private FailureState failureState;
    private Percentage wear;
        
    public FailableComponent()
    {
        failureState = FailureState.Normal;
        wear = new Percentage(0);
    }
    
    public FailureState getFailureState()
    {
        return this.failureState;
    }
    
    public void setFailureState(FailureState newFailureState){
        this.failureState=newFailureState;
    }

    public abstract Percentage calculateWearDelta();

    public Percentage getWear() {
        return wear;
    }
    
    public void setWear(Percentage wearDelta)
    {
       if (wear.points() + wearDelta.points() < 100) {
            wear = wear.plus(wearDelta);
        }
       else {
            wear = new Percentage(100);
        }
    }
}
