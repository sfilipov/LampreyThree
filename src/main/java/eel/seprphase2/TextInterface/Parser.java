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
            int position = Integer.parseInt(words[1]);
            if (position > 100) {
                renderer.output("Error: Cannot move control rods above 100");
            } else if (position < 0) {
                renderer.output("Error: Cannot move control rods below 0");
            } else {
                controller.moveControlRods(position);
            }
        } else {
            renderer.output("Error: Unknown command '" + words[0] + "'");
        }
    }
}
