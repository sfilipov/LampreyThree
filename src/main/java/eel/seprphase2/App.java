package eel.seprphase2;

import eel.seprphase2.FailureModel.FailureModel;
import eel.seprphase2.Simulator.PhysicalModel;
import eel.seprphase2.Simulator.Reactor;
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
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TerminalRenderer renderer = new TerminalRenderer();
        TerminalReader reader = new TerminalReader();
        PhysicalModel physicalModel = new PhysicalModel();
        FailureModel failureModel = new FailureModel(physicalModel);
        TextInterface ti = new TextInterface(physicalModel, physicalModel, renderer, reader);
        ti.askForUsername();
        while (true) {
            ti.showStatus();
            ti.processCommand();
            failureModel.step();            
        }
    }
}
