/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;

/**
 *
 * @author Yazidi
 */
public class TextInterface {

    private PlantController plantController;
    private TextRenderer textRenderer;

    public TextInterface(PlantController plantController, TextRenderer textRenderer) {
        this.plantController = plantController;
        this.textRenderer = textRenderer;
    }

    public void begin() {
        textRenderer.output("Control Rod Position: " +
                            plantController.controlRodPosition() +
                            "\n");
    }
}
