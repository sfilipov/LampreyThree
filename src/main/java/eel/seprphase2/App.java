package eel.seprphase2;

import eel.seprphase2.TextInterface.AsciiArt;
import eel.seprphase2.TextInterface.TerminalRenderer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        while (true) {
            try {
                Game game = new Game();
            } catch (GameOverException e) {
                AsciiArt.mushroomCloud(new TerminalRenderer());
                System.out.println(e.getMessage());
                (new BufferedReader(new InputStreamReader(System.in))).readLine();
            }
        }
    }
}
