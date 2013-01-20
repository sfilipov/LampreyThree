/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.FailureModel.CannotControlException;
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

    void parseCommand(String command) throws DoNotStep {
        String[] words = command.split(" ");
        
        if (words[0].equals("movecontrolrods")) {
            if (words.length != 2) {
                renderer.outputLine("Error: wrong number of arguments to command '" +
                                    words[0] + "'");
                throw new DoNotStep();
            }
            if (!Percentage.isValidPercentage(words[1])) {
                renderer.outputLine("Error: '" +
                                    words[1] +
                                    "' is not a valid percentage.");
                throw new DoNotStep();
            }
            controller.moveControlRods(new Percentage(words[1]));
        } else if(words[0].equals("openvalve")) {
                if(words.length != 2) {
                    renderer.outputLine("Error: wrong number of arguments to command '" +
                                    words[0] + "'");
                    throw new DoNotStep();
                }
                try
                {
                    if(Integer.parseInt(words[1])<=0){
                        renderer.outputLine("Error: '" +
                                        words[1] +
                                        "' is not a valid valve number.");
                        throw new DoNotStep();
                    }
                } 
                catch (NumberFormatException e)
                {
                    renderer.outputLine("Error: '" +
                                        words[1] +
                                        "' is not a valid valve number.");
                        throw new DoNotStep();
                }
                
                try
                {
                    controller.changeValveState(Integer.parseInt(words[1]), true);
                }
                catch (KeyNotFoundException e)
                {
                    renderer.outputLine(e.getMessage());
                    throw new DoNotStep();
                }
        } else if (words[0].equals("closevalve")) {
            if(words.length != 2) {
                renderer.outputLine("Error: wrong number of arguments to command '" +
                                    words[0] + "'");
                throw new DoNotStep();
            }
            try
            {
                if(Integer.parseInt(words[1])<=0){
                    renderer.outputLine("Error: '" +
                                    words[1] +
                                    "' is not a valid valve number.");
                    throw new DoNotStep();
                }
            } 
            catch (NumberFormatException e)
            {
                renderer.outputLine("Error: '" +
                                    words[1] +
                                    "' is not a valid valve number.");
                    throw new DoNotStep();
            }
            
            try
            {
                controller.changeValveState(Integer.parseInt(words[1]), false);
            }
            catch (KeyNotFoundException e)
            {
                renderer.outputLine(e.getMessage());
                throw new DoNotStep();
            }
        } else if(words[0].equals("pumpon")) {
            if(words.length !=2) {
                renderer.outputLine("Error: wrong number of arguments to command '" +
                                words[0] + "'");
                throw new DoNotStep();
            }
            try
            {
                if(Integer.parseInt(words[1])<=0){
                    renderer.outputLine("Error: '" +
                                    words[1] +
                                    "' is not a valid pump number.");
                    throw new DoNotStep();
                }
            } 
            catch (NumberFormatException e)
            {
                renderer.outputLine("Error: '" +
                                    words[1] +
                                    "' is not a valid pump number.");
                throw new DoNotStep();
            }
            
            try
            {
                controller.changePumpState(Integer.parseInt(words[1]), true);
            }
            catch(CannotControlException e)
            {
                renderer.outputLine(e.getMessage());
                throw new DoNotStep();
            }
            catch (KeyNotFoundException e)
            {
                renderer.outputLine(e.getMessage());
                throw new DoNotStep();
            }
        } else if(words[0].equals("pumpoff")) {
            if(words.length !=2) {
                renderer.outputLine("Error: wrong number of arguments to command '" +
                                words[0] + "'");
                throw new DoNotStep();
            }
            
            
            try
            {
                if(Integer.parseInt(words[1])<=0){
                    renderer.outputLine("Error: '" +
                                    words[1] +
                                    "' is not a valid pump number.");
                    throw new DoNotStep();
                }
            } 
            catch (NumberFormatException e)
            {
                renderer.outputLine("Error: '" +
                                    words[1] +
                                    "' is not a valid pump number.");
                throw new DoNotStep();
            }
            
            try
            {
                controller.changePumpState(Integer.parseInt(words[1]), false);  
            }
            catch(CannotControlException e)
            {
                renderer.outputLine(e.getMessage());
                throw new DoNotStep();
            }
            catch (KeyNotFoundException e)
            {
                renderer.outputLine(e.getMessage());
                throw new DoNotStep();
            }
            
        } else if(words[0].equals("repair")) {
            if(words[1].equals("pump")) {
                if(words.length != 3) {
                    renderer.outputLine("Error: wrong number of arguments to command '" +
                          words[0] + "'");
                    throw new DoNotStep();
                }
                
                try
                {
                    if(Integer.parseInt(words[2])<=0){
                        renderer.outputLine("Error: '" +
                                        words[2] +
                                        "' is not a valid number.");
                        throw new DoNotStep();
                    }
                } 
                catch (NumberFormatException e)
                {
                    renderer.outputLine("Error: '" +
                                        words[2] +
                                        "' is not a valid number.");
                        throw new DoNotStep();
                }
                
                try
                {
                    controller.repairPump(Integer.parseInt(words[2]));
                }
                catch (KeyNotFoundException e)
                {
                    renderer.outputLine(e.getMessage());
                    throw new DoNotStep();
                }
                    
            } else if(words[1].equals("condenser")) {
                if(words.length != 2) {
                    renderer.outputLine("Error: wrong number of arguments to command '" +
                          words[0] + "'");
                    throw new DoNotStep();
                }
                controller.repairCondenser();
            } else if (words[1].equals("turbine")) {
                if(words.length != 2) {
                    renderer.outputLine("Error: wrong number of arguments to command '" +
                          words[0] + "'");
                    throw new DoNotStep();
                }
                controller.repairTurbine();
            } else {
                renderer.outputLine("Invalid Component");
            }
        
        } else if(words[0].equals("save")) {
            if(words.length != 1) {
                renderer.outputLine("Error: wrong number of arguments to command '" +
                      words[0] + "'");
                throw new DoNotStep();
            }
            try
            {
                manager.saveGame();
                renderer.outputLine("Game Saved!");
                throw new DoNotStep();
            }
            catch(JsonProcessingException err)
            {
                renderer.outputLine("Unable to save file");
                throw new DoNotStep();
            }
        } else if(words[0].equals("load")) {
                if(words.length > 2) {
                    renderer.outputLine("Error: wrong number of arguments to command '" +
                          words[0] + "'");
                    throw new DoNotStep();
                }
                else if(words.length == 2)
                {
                try
                {
                    if(Integer.parseInt(words[1])==0){
                        renderer.outputLine("Error: Game '" +
                                        words[1] +
                                        "' does not exist.");
                        throw new DoNotStep();
                    }
                } 
                catch (NumberFormatException e)
                {
                    renderer.outputLine("Error: '" +
                                        words[1] +
                                        "' is not a valid number.");
                        throw new DoNotStep();
                }
                manager.loadGame(Integer.parseInt(words[1]));
                throw new DoNotStep();
                }
                else if(words.length==1)
                {
                    renderer.outputLine("Please chose a game to load and enter the following command:");
                    renderer.outputLine("load <game id>");
                    int i = 1;
                    for(String game : manager.listGames())
                    {
                        String[] bits = game.split("\\.");
                        Timestamp t = new Timestamp(Long.parseLong(bits[3]));
                        Date d = new Date(t.getTime());
                        
                        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                        renderer.outputLine(String.format("[%d] Saved: %s", i++, date.format(d)));
                        //renderer.outputLine(game);
                    }
                    throw new DoNotStep();
                }
        } else if(words[0].equals("help")) {
            renderer.outputLine("Possible Commands: " + "\n" + "movecontrolrods <Percentage>" + "\n" +
                                              "openvalve <ValveNumber>" + "\n" + 
                                              "closevalve <ValveNumber>" + "\n" + 
                                              "pumpon <PumpNumber>" + "\n" + 
                                              "pumpoff <PumpNumber>" + "\n" + 
                                              "repair pump <PumpNumber>" + "\n" + 
                                              "repair turbine" + "\n" + 
                                              "repair condenser" + "\n" + 
                                              "save" + 
                                              "load <GameNumber>" +"\n" + "\n");
            throw new DoNotStep();
        }
        else if(command.isEmpty() && command.trim().isEmpty()) 
        {
            //Just skip
        }else {       
            renderer.outputLine("Error: Unknown command '" + words[0] + "'");
            throw new DoNotStep();
        }
    }
   
  
    /**
     *
     * @param username
     */
    public void setUsername(String username)
    {
        manager.setUsername(username);
    }
    
}
