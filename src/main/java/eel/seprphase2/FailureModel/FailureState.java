package eel.seprphase2.FailureModel;

/**
 * Currently, hardware components have two failure states, normal and failed. There is scope to add new states if 
 * needed in the future.
 * @author James Thorne
 */
public enum FailureState {
    /**
     * Normal. Everything is working as it should and operation will continue as normal.
     */
    Normal,
    /**
     * Failed. Pumps to act as a blockage if failed, reactor to drain the vessel, condenser will not condense.
     */
    Failed
}
