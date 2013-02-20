package eel.seprphase2.Persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.FailureModel;
import eel.seprphase2.Simulator.PhysicalModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import lamprey.seprphase3.DynSimulator.PlantModel;

/**
 * Saveable/Loadable game state
 * 
 * @author David
 */
public class SaveGame {

    @JsonProperty
    private PlantModel plantModel;
    @JsonProperty
    private String userName;

    private SaveGame() {
    }

    /*
     * This method loads all the games under this filename. The filename is the user name,
     * only allowing a user to load games that are saved using the same user name as they are
     * currently using
     */
    public static SaveGame load(String filename) throws JsonParseException, IOException {
        eel.seprphase2.Persistence.Persistence p = new eel.seprphase2.Persistence.Persistence();
        return p.deserializeSaveGame(FileSystem.readString(filename));
    }
    

    public SaveGame(PlantModel plantModel, String userName) {
        this.plantModel = plantModel;
        this.userName = userName;
    }

    /*
     * Saves the game using the method fileName() to make the filename. fileName() takes the users
     * user name and makes a unique String, allowing unique saves
     */
    public void save() throws JsonProcessingException, FileNotFoundException, IOException {
        eel.seprphase2.Persistence.Persistence p = new eel.seprphase2.Persistence.Persistence();
        String data = p.serialize(this);
        FileSystem.createSavePath();
        FileSystem.writeString(fileName(), data);
    }

    public String getUserName() {
        return this.userName;
    }

    public PlantModel getPlantModel() {
        return this.plantModel;
    }
    
    /**
     * generateFileName generates a new unique file name using getTimeInMillis
     *
     * @return The newly generated random file name
     */
    private String fileName() {
        Calendar cal = Calendar.getInstance();
        return "sepr.teameel." + userName + "." + cal.getTimeInMillis() + ".nuke";
    }
}
