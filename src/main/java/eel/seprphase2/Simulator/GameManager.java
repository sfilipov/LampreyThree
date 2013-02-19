package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *Interface class that defines the methods that'll be needed in order to save and load the game.
 * @author David
 */
public interface GameManager {
    
    String[] listGames();

    void loadGame(int gameNumber);

    void saveGame() throws JsonProcessingException;

    void setUsername(String userName);
}
