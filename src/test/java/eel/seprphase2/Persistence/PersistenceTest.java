/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Persistence;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 *
 * @author James
 */
public class PersistenceTest {
    
    public PersistenceTest() {
    }
    
    ArrayList<String> artifacts;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        artifacts = new ArrayList<String>();
    }
    
    @After
    public void tearDown() {
        for(String path:artifacts)
        {
           
            File rem = new File(path);
            if(rem.exists())
            {
                rem.delete();
            }
           
        }
        
    }

    /**
     * Test of SaveGameState method, of class Persistence.
     */
    @Test
    public void shouldGenerateFileNameThatContainsUsername() {
     
        String username = "testing";
        
        String resultFileName = Persistence.GenerateFileName(username);
        
        
        Calendar cal = Calendar.getInstance();
        long timestamp =  cal.getTimeInMillis();
        
        
        String fileName = "sepr.teameel."+ username + "." + timestamp +".nuke";
        
        assertTrue(resultFileName.substring(0,21).equals(fileName.substring(0,21)));
    }
    
    @Test
    public void shouldGenerateFileNameWithNonZeroTimeStamp() {
     
        String username = "testing";
        
        String resultFileName = Persistence.GenerateFileName(username);
        
             
        String fileName = "sepr.teameel."+ username + ".0.nuke";
        
        assertFalse(resultFileName.equals(fileName));
    }
    
    @Test
    public void shouldGenerateFileNameWithNonEmptyTimeStamp() {
     
        String username = "testing";
        
        String resultFileName = Persistence.GenerateFileName(username);
        
        
 
        
        String fileName = "sepr.teameel."+ username + ".0.nuke";
        
        assertFalse(resultFileName.equals(fileName));
    }
    
    @Test
    public void shouldGenerateFileNameWithNearlyUniqueTimestamp() {
     
        String username = "testing";
        String resultFileName = Persistence.GenerateFileName(username);
        String resultFileName2 = Persistence.GenerateFileName(username);
        assertTrue(resultFileName.substring(0,21).equals(resultFileName2.substring(0,21)));
    }
    
    @Test 
    public void shouldCreateASaveGameForSomeUser() {
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(cal.getTimeInMillis());
        String file = Persistence.SaveGameState("test_"+time, "test");
        artifacts.add(file);
        assertTrue(Persistence.GetSaveGames("test_"+time).length==1);
        
    }
    
    
    
    @Test 
    public void shouldCreateASaveGameAndReadCorrectContent() {
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(cal.getTimeInMillis());
        String file = Persistence.SaveGameState("test_"+time, "test");
        
        assertTrue(Persistence.GetSaveGames("test_"+time).length==1);
        artifacts.add(file);
        
        try
        {
            assertEquals(Persistence.LoadGameState(file),"test");
        }
        catch(IOException e)
        {
            fail("Could not read target file");
        }
    }
}
