/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.Simulator;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author david
 */
public interface GameManager {

    String[] listGames();

    void loadGame(int gameNumber);

    void saveGame() throws JsonProcessingException;

    void setUsername(String userName);
}
