package eel.seprphase2.Persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Filesystem interaction
 * 
 * Could be a singleton (only one filesystem) but static methods for convenience.
 * 
 * @author David
 */
public class FileSystem {

    /*
     * Returns the folder on disk in which game files will be saved to 
     * @return Absolute path to folder with trailing slash
     */
    public static String savePath() {
        return System.getProperty("user.home") +
               System.getProperty("file.separator") +
               "sepr.teameel.gamesaves" +
               System.getProperty("file.separator");
    }

    /**
     * Lists all files stored under a player's username in the default save path
     *
     * @param username The Player's username
     *
     * @return A list of file names (not paths) in the directory that match the username
     */
    public static String[] listSaveGames(String username) {
        File saveDir = new File(savePath());
        File[] allFiles = saveDir.listFiles();
        ArrayList<String> acceptableSaveGameFiles = new ArrayList<String>();
        for (File file : allFiles) {
            if (file.isFile()) {
                //sepr.teameel. = 13 chars long
                //if(file.getName().toLowerCase().endsWith(".nuke") && file.getName().startsWith(userName, 13))
                if (file.getName().matches("sepr.teameel." + username + ".([0-9]+).nuke")) {
                    acceptableSaveGameFiles.add(file.getName());
                }

            }
        }
        return acceptableSaveGameFiles.toArray(new String[acceptableSaveGameFiles.size()]);
    }

    /**
     * Make all directories in the savePath
     *
     * @throws IOException Error in creating the directories
     */
    public static void createSavePath() throws IOException {
        if (new File(savePath()).mkdirs()) {
            throw new IOException("Could not create full save path");
        }
    }

    public static void writeString(String fileName, String contents) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(savePath() + fileName);
        out.print(contents);
        out.close();
    }

    public static String readString(String fileName) throws IOException {
        return Utils.readFile(savePath() + fileName);
    }
}
