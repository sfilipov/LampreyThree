package eel.seprphase2;

import eel.seprphase2.Simulator.Reactor;
import eel.seprphase2.TextInterface.TerminalRenderer;
import eel.seprphase2.TextInterface.TextInterface;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        TerminalRenderer tr = new TerminalRenderer();
        Reactor r = new Reactor();
        TextInterface ti = new TextInterface(r, tr);
        DataInputStream ds = new DataInputStream(System.in);
        while (true) {
            ti.showStatus();
            ti.processCommand(ds.readLine());
        }
    }
}
