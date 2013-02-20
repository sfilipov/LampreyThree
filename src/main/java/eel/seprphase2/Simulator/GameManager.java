package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *Interface class that defines the methods that'll be needed in order to save and load the game.
 * @author David
 */
public interface GameManager {
    
    void initGame();

    void loadGame() throws IOException, FileNotFoundException, ClassNotFoundException;

    void saveGame() throws IOException, FileNotFoundException;

    void setUsername(String userName);
}
