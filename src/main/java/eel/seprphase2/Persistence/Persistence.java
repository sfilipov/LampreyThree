package eel.seprphase2.Persistence;
import java.io.IOException;
import java.util.Calendar;

/**
 * Persistence class is a wrapper class that manages saving to disk. Use saveGameState to generate a new save file for
 * a player's username and loadGameState with the unique name to load it again. 
 * @author James Thorne
 */
public class Persistence {
    /**
     * saveGameState will create a new randomly generated file containing the player's username and save the persistData
     * to it.
     * @param username The players username
     * @param persistData Model data that is serialized.
     * @return
     */
    public static String saveGameState(String username, String persistData)
    {
        String fileName = generateFileName(username);       //Create a unique file name
        SaveGameFile file = new SaveGameFile(fileName);     //Create that file
        file.save(persistData);                             //And save the data
        return fileName;                                    //Because file name is unique, we return the value.
    }
    
    
    /**
     * loadGameState will open a save file by fileName and return the string that it contains.
     * @param fileName The name of the file in the user's save directory.
     * @return The contents of the stored file
     * @throws IOException For file read error etc
     */
    public static String loadGameState(String fileName) throws IOException
    {
        SaveGameFile file = new SaveGameFile(fileName);
        return file.load();   
    }
    
    /**
     * generateFileName generates a new unique file name using getTimeInMillis
     * @param username The player's username, prepended to the file
     * @return The newly generated random file name
     */
    public static String generateFileName(String username)
    {
        Calendar cal = Calendar.getInstance();
        return "sepr.teameel."+ username + "." + cal.getTimeInMillis() +".nuke";
    }
    
    /**
     * Pass-through to get saved games from data source
     * @see SaveGameFile
     * @param username
     * @return List of save game files
     */
    public static String[] getSaveGames(String username)
    {
        return SaveGameFile.listSaveGames(username);
    }
}
