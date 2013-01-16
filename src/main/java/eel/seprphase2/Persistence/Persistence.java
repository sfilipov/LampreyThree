/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Persistence;
import java.util.Calendar;
/**
 *
 * @author James
 */
public class Persistence {
    public static String SaveGameState(String username, String persistData)
    {
        String fileName = GenerateFileName(username);
        SaveGameFile file = new SaveGameFile(fileName, persistData);
        return fileName;
    }
    
    public static String GenerateFileName(String username)
    {
        Calendar cal = Calendar.getInstance();
        return "sepr.teameel."+ username + "." + cal.getTimeInMillis() +".nuke";
    }
}
