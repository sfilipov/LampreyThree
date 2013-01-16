/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Persistence;
import java.io.*;

/**
 *
 * @author James
 */
class SaveGameFile {
    private String fileName;
    private String persistData;
    
    
    public SaveGameFile()
    {
        
    }
    
    
    public SaveGameFile(String fileName, String persistData)
    {
        this.fileName = fileName;
        this.persistData = persistData;
     
        
        try
        {
            CreateSavePath();
            Store();
        }
        catch(Exception e)
        {
            
        }
        
        
    }
    
    public String FilePath()
    {
        return FullSavePath()+fileName;
    }
    
    public void Store() throws IOException
    {
        PrintWriter out = new PrintWriter(FilePath());
        out.print(persistData);
        out.close();
    }
    
    public void CreateSavePath() throws IOException
    {
        
        if(new File(FullSavePath()).mkdirs())
        {
            throw new IOException("Could not create full save path");
        }
    }
    
    public String FullSavePath()
    {
        return System.getProperty("user.home")+System.getProperty("file.separator")+"sepr.teameel.gamesaves"+System.getProperty("file.separator");
    }
    
    public void SaveToDisk()
    {
    
    }
}
