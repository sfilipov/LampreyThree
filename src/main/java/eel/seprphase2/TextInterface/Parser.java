/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.FailureModel.CannotControlException;
import eel.seprphase2.Simulator.FailureModel.CannotRepairException;
import eel.seprphase2.Simulator.FailureModel.ControlException;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Utilities.Percentage;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author david
 */
public class Parser {

    private PlantController controller;
    private GameManager manager;
    private TextRenderer renderer;

    Parser(PlantController controller, GameManager manager, TextRenderer renderer) {
        this.controller = controller;
        this.manager = manager;
        this.renderer = renderer;
    }

    public void executeCommand(String commandLine) throws DoNotStep {
        String[] words = commandLine.trim().split(" ");

        String command = words[0];
        ArgumentList arguments = new ArgumentList(command);
        for (int i = 1; i < words.length; i++) {
            arguments.add(words[i]);
        }

        try {
            parseCommand(command, arguments);
        } catch (ArgumentException e) {
            renderer.outputLine("ERROR: " + e.getMessage());
            throw new DoNotStep();
        } catch (KeyNotFoundException e) {
            renderer.outputLine(e.getMessage());
            throw new DoNotStep();
        } catch (ControlException e) {
            renderer.outputLine(e.getMessage());
            throw new DoNotStep();
        }
    }

    private void parseCommand(String command, ArgumentList arguments)
            throws DoNotStep,
                   ArgumentException,
                   KeyNotFoundException,
                   ControlException {

        if (command.isEmpty()) {
            return;
        }
        
        if (command.equals("movecontrolrods")) {
            controller.moveControlRods(arguments.at(0).asPercentage());
        } else if (command.equals("openvalve")) {
            controller.changeValveState(arguments.at(0).asPositiveInteger(), true);
        } else if (command.equals("closevalve")) {
            controller.changeValveState(arguments.at(0).asPositiveInteger(), false);
        } else if (command.equals("pumpon")) {
            controller.changePumpState(arguments.at(0).asPositiveInteger(), true);
        } else if (command.equals("pumpoff")) {
            controller.changePumpState(arguments.at(0).asPositiveInteger(), false);
        } else if (command.equals("repair")) {
            repair(arguments);
        } else if (command.equals("save")) {
            saveGame();
            throw new DoNotStep();
        } else if (command.equals("load")) {
            if (arguments.count() == 1) {
                manager.loadGame(arguments.at(0).asPositiveInteger());
                throw new DoNotStep();
            } else if (arguments.count() == 1) {
                chooseGame();
                throw new DoNotStep();
            }
        } else if (command.equals("help")) {
            showHelp();
            throw new DoNotStep();
        } else {
            renderer.outputLine("Error: Unknown command '" + command + "'");
            throw new DoNotStep();
        }
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        manager.setUsername(username);
    }

    public int chooseAction(String action) {

        try {
            return Integer.parseInt(action);
        } catch (NumberFormatException n) {
            return 0;
        }
    }

    private void chooseGame() throws NumberFormatException {
        renderer.outputLine("Please choose a game to load and enter the following command:");
        renderer.outputLine("load <game id>");
        int i = 0;
        for (String game : manager.listGames()) {
            String[] bits = game.split("\\.");
            Timestamp t = new Timestamp(Long.parseLong(bits[3]));
            Date d = new Date(t.getTime());

            SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            renderer.outputLine(String.format("[%d] Saved: %s", i++, date.format(d)));
        }
    }

    private void saveGame() throws DoNotStep {
        try {
            manager.saveGame();
            renderer.outputLine("Game Saved!");
        } catch (JsonProcessingException err) {
            renderer.outputLine("Unable to save file");
        }
    }

    private void repair(ArgumentList arguments)
            throws KeyNotFoundException,
                   ArgumentException,
                   ControlException {
        if (arguments.at(0).equals("pump")) {
            controller.repairPump(arguments.at(1).asPositiveInteger());
        } else if (arguments.at(0).equals("condenser")) {
            controller.repairCondenser();
        } else if (arguments.at(0).equals("turbine")) {
            controller.repairTurbine();
        } else {
            renderer.outputLine("Invalid Component to repair");
        }
    }

    private void showHelp() {
        renderer.outputLine("Possible Commands:\n" +
                            "movecontrolrods <Percentage>\n" +
                            "openvalve <ValveNumber>\n" +
                            "closevalve <ValveNumber>\n" +
                            "pumpon <PumpNumber>\n" +
                            "pumpoff <PumpNumber>\n" +
                            "repair pump <PumpNumber>\n" +
                            "repair turbine\n" +
                            "repair condenser\n" +
                            "save" +
                            "load <GameNumber>\n" +
                            "\n");
    }
}
