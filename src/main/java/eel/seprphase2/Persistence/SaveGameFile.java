package eel.seprphase2.Persistence;
import java.io.*;
import java.util.ArrayList;

/**
 * SaveGameFile is a persistence provider that saves the game file to the user's file system.
 * @author James Thorne
 */
class SaveGameFile implements ISaveGame {
    private String fileName;
    private String persistData;
    
    /**
     * Default constructor used for Unit testing
     * Not to be used in production
     */
    public SaveGameFile()
    {
        
    }
    
    /**
     * Constructor stores the fileName for later use
     * @param fileName Name (not path) of the file to be written to
     */    
    public SaveGameFile(String fileName)
    {
        this.fileName = fileName; 
    }
    
    
    /**
     * Save stores the given string to the file system
     * @param persistData Data to be saved to disk
     */
    @Override
    public void save(String persistData)
    {
        this.persistData = persistData;
        
        try
        {
            createSavePath();
            store();
        }
        catch(Exception e)
        {
            
        }
    }
    
    /**
     * Load a save game from the disk and return it as a string
     * @return The contents of the save game file
     * @throws IOException Read error or file not found
     */
    @Override
    public String load() throws IOException
    {
        return Utils.readFile(filePath());
    }
    

    /**
     * Append the save path to the file name
     * @return Absolute path to save file
     */
    @Override
    public String filePath()
    {
        return savePath()+fileName;
    }
    
    /**
     * Writes the persistData string to disk using a PrintWriter
     * @throws IOException An error in writing the file, thrown by PrintWriter
     */
    private void store() throws IOException
    {
        PrintWriter out = new PrintWriter(filePath());
        out.print(persistData);
        out.close();
    }
    
    /**
     * Make all directories in the savePath
     * @throws IOException Error in creating the directories
     */
    public void createSavePath() throws IOException
    {
        
        if(new File(savePath()).mkdirs())
        {
            throw new IOException("Could not create full save path");
        }
    }
    
    /*
     * Returns the folder on disk in which game files will be saved to 
     * @return Absolute path to folder with trailing slash
     */
    public static String savePath()
    {
        return System.getProperty("user.home")+System.getProperty("file.separator")+"sepr.teameel.gamesaves"+System.getProperty("file.separator");
    }
    
    
   /**
    * Lists all files stored under a player's username in the default save path
    * @param username The Player's username
    * @return A list of file names (not paths) in the directory that match the username
    */
    public static String[] listSaveGames(String username) {
        File saveDir = new File(savePath());
        File[] allFiles = saveDir.listFiles(); 
        ArrayList<String> acceptableSaveGameFiles = new ArrayList<String>();
        for(File file : allFiles)
        {
            if(file.isFile())
            {
                //sepr.teameel. = 13 chars long
                //if(file.getName().toLowerCase().endsWith(".nuke") && file.getName().startsWith(userName, 13))
                if(file.getName().matches("sepr.teameel."+username+".([0-9]+).nuke") )
                {
                    acceptableSaveGameFiles.add(file.getName());
                }
                
            }
        }
        return acceptableSaveGameFiles.toArray(new String[acceptableSaveGameFiles.size()]);
    }
}
