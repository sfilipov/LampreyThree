package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author David
 */
public interface GameManager {

    String[] listGames();

    void loadGame(int gameNumber);

    void saveGame() throws JsonProcessingException;

    void setUsername(String userName);
}
