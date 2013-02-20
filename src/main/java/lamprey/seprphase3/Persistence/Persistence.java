package lamprey.seprphase3.Persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lamprey.seprphase3.DynSimulator.PlantModel;

/**
 * Proper serialization, none of this Json crap! ;)
 *
 * @author will
 */
public class Persistence {

    private static final String saveFileName = "backyardReactor.lulz";

    /**
     * Serializes a SaveGame object and saves to a file in the working directory.
     *
     * @return true if saving a game was successful, false otherwise.
     */
    public boolean serializeSaveGame(SaveGame gameState) throws IOException {
        FileOutputStream fileOut = null;
        ObjectOutputStream out = null;
        try {
            fileOut = new FileOutputStream(saveFileName);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(gameState);
        } finally {
            out.close();
            fileOut.close();
        }
        return true;
    }

    /**
     * Reads in a SaveGame object from a saved game file and returns said object.
     *
     * @return SaveGame game state object.
     */
    public SaveGame deserializeSaveGame() throws FileNotFoundException, IOException, ClassNotFoundException {
        SaveGame gameState = null;
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        System.out.println("Deserializing!");
        try {
            File f = new File(saveFileName);
            if (f.exists()) {
                System.out.println("File exists!");
                fileIn = new FileInputStream(f);
                in = new ObjectInputStream(fileIn);
                gameState = (SaveGame)in.readObject();
            } else {
                System.out.println("File does not exist!");
                throw new FileNotFoundException("Save file not found.");
            }
        } finally {
            in.close();
            fileIn.close();
        }
        return gameState;
    }
}
