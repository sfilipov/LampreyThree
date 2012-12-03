package eel.seprphase2;

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

    public static void main(String[] args) throws IOException {
        TerminalRenderer renderer = new TerminalRenderer();
        TerminalReader reader = new TerminalReader();
        Reactor reactor = new Reactor();
        TextInterface ti = new TextInterface(reactor, reactor, renderer, reader);
        while (true) {
            ti.showStatus();
            ti.processCommand();
            reactor.step();
        }
    }
}
