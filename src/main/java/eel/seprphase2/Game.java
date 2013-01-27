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

    public Game() throws GameOverException, QuitGameException {
        renderer = new TerminalRenderer();
        reader = new TerminalReader();

        simulator = new Simulator();


        ti = new TextInterface(simulator, simulator, simulator, renderer, reader);

        ti.showWelcomeMessage();
        ti.askForUsername();

        int menuChoice = ti.askForAction();
        if (menuChoice == 1) {
            ti.showIntroText();
        } else if (menuChoice == 2) {
            ti.showSavedGames();
        } else if (menuChoice == 3) {
            renderer.outputLine("");
            renderer.outputLine("Thanks For Playing!");
            renderer.outputLine("");
            System.exit(0);
        } else {
            renderer.outputLine("Invalid menu item!");
            throw new QuitGameException();
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
