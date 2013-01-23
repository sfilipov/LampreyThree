package eel.seprphase2.Persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.FailureModel;
import eel.seprphase2.Simulator.PhysicalModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

/**
 * Saveable/Loadable game state
 * 
 * @author David
 */
public class SaveGame {

    @JsonProperty
    private PhysicalModel physicalModel;
    @JsonProperty
    private FailureModel failureModel;
    @JsonProperty
    private String userName;

    private SaveGame() {
    }

    public static SaveGame load(String filename) throws JsonParseException, IOException {
        eel.seprphase2.Persistence.Persistence p = new eel.seprphase2.Persistence.Persistence();
        return p.deserializeSaveGame(FileSystem.readString(filename));
    }

    public SaveGame(PhysicalModel physicalModel, FailureModel failureModel, String userName) {
        this.physicalModel = physicalModel;
        this.failureModel = failureModel;
        this.userName = userName;
    }

    public void save() throws JsonProcessingException, FileNotFoundException, IOException {
        eel.seprphase2.Persistence.Persistence p = new eel.seprphase2.Persistence.Persistence();
        String data = p.serialize(this);
        FileSystem.createSavePath();
        FileSystem.writeString(fileName(), data);
    }

    public String getUserName() {
        return this.userName;
    }

    public PhysicalModel getPhysicalModel() {
        return this.physicalModel;
    }
    
    public FailureModel getFailureModel() {
        return this.failureModel;
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
