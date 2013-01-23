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
public class FileSystemTest {
    ArrayList<String> artifacts;
    public FileSystemTest() {
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
    
    @Test
    public void shouldCreateDir()
    {
        FileSystem instance = new FileSystem();
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
        FileSystem instance = new FileSystem();
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
    public void shouldListTwoFIlesWithARandomAndSequentialUserName()
    {
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(cal.getTimeInMillis());
        
        
        Persistence p = new Persistence();
        p.serialize(this);
       
        artifacts.add(instance1.filePath());
        artifacts.add(instance2.filePath());
        
        assertTrue(FileSystem.listSaveGames(time).length==2);
    }
    
    
    @Test
    public void shouldListNoFilesWithARandomUserName()
    {
        
        Calendar cal = Calendar.getInstance();
        String time = String.valueOf(cal.getTimeInMillis());
        assertTrue(FileSystem.listSaveGames(time).length==0);
    }
    
    
}