/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2;

import eel.seprphase2.Simulator.Simulator;
import eel.seprphase2.TextInterface.DoNotStep;
import eel.seprphase2.TextInterface.TerminalReader;
import eel.seprphase2.TextInterface.TerminalRenderer;
import eel.seprphase2.TextInterface.TextInterface;

/**
 *
 * @author James
 */
public class Game {

    private TerminalRenderer renderer;
    private TerminalReader reader;
    private Simulator simulator;
    private TextInterface ti;

    public Game() throws GameOverException {
        renderer = new TerminalRenderer();
        reader = new TerminalReader();

        simulator = new Simulator();


        ti = new TextInterface(simulator, simulator, simulator, renderer, reader);

        ti.showWelcomeMessage();
        ti.askForUsername();

        if (ti.askForAction() == 1) {
            ti.showIntroText();
        } else {

            ti.showSavedGames();

        }

        ti.showStatus();
        while (true) {

            try {
                ti.processCommand();
                simulator.step();
            } catch (DoNotStep n) {
            }
            // show a blank line
            ti.showStatus();
            System.out.println("");
        }
    }
}
