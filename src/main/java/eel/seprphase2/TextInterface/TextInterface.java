/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;

/**
 *
 * @author Yazidi
 */
public class TextInterface {

    private PlantController plantController;
    private PlantStatus plantStatus;
    private TextRenderer textRenderer;
    private LineReader lineReader;

    public TextInterface(PlantController plantController,
                         PlantStatus plantStatus,
                         TextRenderer textRenderer,
                         LineReader lineReader) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.textRenderer = textRenderer;
        this.lineReader = lineReader;
    }

    public void showStatus() {
        textRenderer.outputLine("Control Rod Position: " +
                                plantStatus.controlRodPosition());
        textRenderer.outputLine("Reactor Temperature: " + plantStatus
                .temperature());
        textRenderer.outputLine("Reactor Pressure: " + plantStatus.pressure());
        textRenderer.outputLine("Water Level: " + plantStatus.waterLevel());
    }

    public void processCommand() {
        Parser parser = new Parser(plantController, textRenderer);
        parser.parseCommand(lineReader.readLine());
    }
}
