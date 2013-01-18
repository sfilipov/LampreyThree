/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Utilities.Percentage;

/**
 *
 * @author david
 */
public class Parser {

    private static PlantController controller;
    private TextRenderer renderer;

    Parser(PlantController controller, TextRenderer renderer) {
        this.controller = controller;
        this.renderer = renderer;
    }

    void parseCommand(String command) {
        String[] words = command.split(" ");
        if (words[0].equals("movecontrolrods")) {
            if (words.length != 2) {
                renderer.outputLine("Error: wrong number of arguments to command '" +
                                    command + "'");
                return;
            }
            if (!Percentage.isValidPercentage(words[1])) {
                renderer.outputLine("Error: '" +
                                    words[1] +
                                    "' is not a valid percentage.");
                return;
            }
            controller.moveControlRods(new Percentage(words[1]));
        } else {
            renderer.outputLine("Error: Unknown command '" + words[0] + "'");
        }
    }
    
    /**
     *
     * @param username
     */
    public void setUsername(String username)
    {
        controller.setUsername(username);
    }
    
    /**
     *
     * @return
     */
    public static PlantController returnController()
    {
        return controller;
    }
}
