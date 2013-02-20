package lamprey.seprphase3.Persistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import lamprey.seprphase3.DynSimulator.PlantModel;

/**
 * Saveable/Loadable game state
 * 
 * @author will
 */
public class SaveGame implements Serializable {

    private PlantModel plantModel;
    private String userName;

    /**
     * Load SaveGame object from save file.
     * @return returns loaded SaveGame object from file.
     */
    public static SaveGame load() throws IOException, FileNotFoundException, ClassNotFoundException {
        Persistence p = new Persistence();
        return p.deserializeSaveGame();
    }
    

    public SaveGame(PlantModel plantModel, String userName) {
        this.plantModel = plantModel;
        this.userName = userName;
    }

    /**
     * Writes this (SaveGame object) out to file.
     * @return true if the save was successful.
     */
    public boolean save() throws FileNotFoundException, IOException {
        Persistence p = new Persistence();
        return p.serializeSaveGame(this);
    }

    public String getUserName() {
        return this.userName;
    }

    public PlantModel getPlantModel() {
        return this.plantModel;
    }
    
}
