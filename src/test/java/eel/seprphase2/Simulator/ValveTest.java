/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;


import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author james
 */
public class ValveTest {
    
   
    private class MockValve implements IValve
    {
        private boolean open = false;

        @Override
        public boolean GetOpen() {
            return open;
            //throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void SetOpen(boolean Open) {
            open = Open;
            //throw new UnsupportedOperationException("Not supported yet.");
        }
        
        
    }
    
    
 
    
    @Test
    public void shouldInitialiseValve() {
        IValve valve = new Valve();
        assertEquals(false, valve.GetOpen());
        
    }

    @Test
    public void shouldSetClosed() {
        IValve valve = new Valve();
        valve.SetOpen(false);
        assertEquals(false, valve.GetOpen());
        
        
    }
    
    @Test
    public void shouldSetOpen() {
        IValve valve = new Valve();
        valve.SetOpen(true);
        assertEquals(true, valve.GetOpen());
        
        
    }
    
    
}
