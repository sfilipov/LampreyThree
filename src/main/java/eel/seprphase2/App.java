package eel.seprphase2;

import eel.seprphase2.Simulator.FailureModel;
import eel.seprphase2.Simulator.PhysicalModel;
import eel.seprphase2.Simulator.Reactor;
import eel.seprphase2.Simulator.Simulator;
import eel.seprphase2.TextInterface.DoNotStep;
import eel.seprphase2.TextInterface.TerminalReader;
import eel.seprphase2.TextInterface.TerminalRenderer;
import eel.seprphase2.TextInterface.TextInterface;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

    /**
     * The main entry point for the application
     *
     * @param args
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Game game = new Game();
    }
}
