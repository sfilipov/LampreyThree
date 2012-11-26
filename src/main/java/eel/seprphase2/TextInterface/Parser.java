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

    Parser(PlantController plantController) {
        controller = plantController;
    }

    void parseCommand(String command) {
        String[] words = command.spli(" ");
    }
}
