/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;

/**
 *
 * @author david
 */
public class Parser {

    private PlantController controller;
    private TextRenderer renderer;

    Parser(PlantController controller, TextRenderer renderer) {
        this.controller = controller;
        this.renderer = renderer;
    }

    void parseCommand(String command) {
        String[] words = command.split(" ");
        if (words[0].equals("movecontrolrods")) {
            controller.moveControlRods(Integer.parseInt(words[1]));
        } else {
            renderer.output("Error: Unknown command '" + words[0] + "'");
        }

    }
}
