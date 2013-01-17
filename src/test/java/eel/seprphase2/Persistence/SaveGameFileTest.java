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
import static org.junit.Assert.*;
import eel.seprphase2.Persistence.Utils;
import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.ArrayList;
/**
 *
 * @author James
 */
public class SaveGameFileTest {
    ArrayList<String> artifacts;
    public SaveGameFileTest() {
    }
    
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
     * Test of SaveToDisk method, of class SaveGameFile.
    
    @Test
    public void testSaveToDisk() {
   
        SaveGameFile instance = new SaveGameFile("filename","data");
        instance.SaveToDisk();
    
    }*/
    
    @Test
    public void shouldCreateDir()
    {
        SaveGameFile instance = new SaveGameFile();
        File f = new File(instance.savePath());
        
        try
        {
            instance.createSavePath();
        }
        catch(Exception e)
        {
            fail(e.getMessage());
        }
        
        assertTrue(f.exists());
    }
    
    @Test
    public void shouldNotBreakIfTryingToCreateTheSameFolderTwice()
    {
        SaveGameFile instance = new SaveGameFile();
        File f = new File(instance.savePath());
        
        try
        {
            instance.createSavePath();
        }
        catch(Exception e)
        {
            fail(e.getMessage());
        }
        
        assertTrue(f.exists());
        
        
        try
        {
            instance.createSavePath();
        }
        catch(Exception e)
        {
            fail(e.getMessage());
        }
        
        assertTrue(f.exists());
    }
    
    @Test
    public void shouldCreateAnEmptyFile()
    {
        
        Calendar cal = Calendar.getInstance();
        long time = cal.getTimeInMillis();
        SaveGameFile instance = new SaveGameFile("test_"+time);
        instance.save("");
        artifacts.add(instance.filePath());
        assertTrue(new File(instance.filePath()).exists());
    }
    
    @Test
    public void shouldCreateAFileWithContent()
    {
        
        Calendar cal = Calendar.getInstance();
        long time = cal.getTimeInMillis();
        SaveGameFile instance = new SaveGameFile("test_"+time);
        instance.save("hello");
        artifacts.add(instance.filePath());
        
        assertTrue(new File(instance.filePath()).exists());
        
        try
        {
            assertEquals(Utils.readFile(instance.filePath()),"hello");
        }
        catch(Exception e)
        {
            fail("Reading file failed - " +e.getMessage());
        }
        
    }
    
    @Test
    public void shouldReadSameContent()
    {
        
        Calendar cal = Calendar.getInstance();
        long time = cal.getTimeInMillis();
        SaveGameFile instance = new SaveGameFile("test_"+time);
        instance.save("hello");
        artifacts.add(instance.filePath());
        assertTrue(new File(instance.filePath()).exists());
        
        
        
        try
        {
            assertEquals(instance.load(),"hello");
        }
        catch(Exception e)
        {
            fail("Reading file failed - " + e.getMessage());
        }
        
    }
    
    @Test
    public void shouldReadandWriteLongContent()
    {
        SecureRandom random = new SecureRandom();

        
        String rdm = new BigInteger(2500, random).toString(2);
  
        Calendar cal = Calendar.getInstance();
        long time = cal.getTimeInMillis();
        SaveGameFile instance = new SaveGameFile("test_"+time);
        instance.save(rdm);
        artifacts.add(instance.filePath());
        assertTrue(new File(instance.filePath()).exists());
        
        try
        {
            assertEquals(instance.load(),rdm);
        }
        catch(Exception e)
        {
            fail("Reading file failed");
        }
        
    }
    
    @Test
    public void shouldListOneFileWithARandomUserName()
    {
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(cal.getTimeInMillis());
        
        
        SaveGameFile instance = new SaveGameFile("sepr.teameel."+time+".0.nuke");
        instance.save("");
        
        assertTrue(SaveGameFile.listSaveGames(time).length==1);
    }
    
    
    @Test
    public void shouldListTwoFIlesWithARandomAndSequentialUserName()
    {
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(cal.getTimeInMillis());
        
        
        SaveGameFile instance1 = new SaveGameFile("sepr.teameel."+time+".0.nuke");
        instance1.save("");
        SaveGameFile instance2 = new SaveGameFile("sepr.teameel."+time+".1.nuke");
        instance2.save("");
        
        artifacts.add(instance1.filePath());
        artifacts.add(instance2.filePath());
        
        assertTrue(SaveGameFile.listSaveGames(time).length==2);
    }
    
    
    @Test
    public void shouldListNoFilesWithARandomUserName()
    {
        
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(cal.getTimeInMillis());
        assertTrue(SaveGameFile.listSaveGames(time).length==0);
    }
    
    
}
