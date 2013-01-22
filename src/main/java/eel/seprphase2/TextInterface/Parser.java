/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.ControlException;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Utilities.Percentage;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author David
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
            print("ERROR: " + e.getMessage());
            throw new DoNotStep();
        } catch (KeyNotFoundException e) {
            print(e.getMessage());
            throw new DoNotStep();
        } catch (ControlException e) {
            print(e.getMessage());
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
            Percentage position = arguments.at(0).asPercentage();
            controller.moveControlRods(position);
            print("Moved control rods to " + position);
        } else if (command.equals("openvalve")) {
            int valveNumber = arguments.at(0).asPositiveInteger();
            controller.changeValveState(valveNumber, true);
            print("Opened valve " + valveNumber);
        } else if (command.equals("closevalve")) {
            int valveNumber = arguments.at(0).asPositiveInteger();
            controller.changeValveState(valveNumber, false);
            print("Closed valve " + valveNumber);
        } else if (command.equals("pumpon")) {
            int pumpNumber = arguments.at(0).asPositiveInteger();
            controller.changePumpState(pumpNumber, true);
            print("Turned on pump " + pumpNumber);
        } else if (command.equals("pumpoff")) {
            int pumpNumber = arguments.at(0).asPositiveInteger();
            controller.changePumpState(pumpNumber, false);
            print("Turned off pump " + pumpNumber);
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
            print("Error: Unknown command '" + command + "'");
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
        print("Please choose a game to load and enter the following command:");
        print("load <game id>");
        int i = 0;
        for (String game : manager.listGames()) {
            String[] bits = game.split("\\.");
            Timestamp t = new Timestamp(Long.parseLong(bits[3]));
            Date d = new Date(t.getTime());

            SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            print(String.format("[%d] Saved: %s", i++, date.format(d)));
        }
    }

    private void saveGame() throws DoNotStep {
        try {
            manager.saveGame();
            print("Game Saved!");
        } catch (JsonProcessingException err) {
            print("Unable to save file");
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
            print("Invalid Component to repair");
        }
    }

    private void showHelp() {
        print("Possible Commands:\n" +
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

    private void print(String output) {
        renderer.outputLine(output);
    }
}
