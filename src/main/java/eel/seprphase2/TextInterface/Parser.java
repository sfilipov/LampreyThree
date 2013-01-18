/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
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
            if(words[0].equals("openvalve")) {
                if(words.length != 2) {
                    renderer.outputLine("Error: wrong number of arguments to command '" +
                                    command + "'");
                    return;
                }
                if(Integer.parseInt(words[1])>0 & Integer.parseInt(words[1])<10) {
                    renderer.outputLine("Error: '" + 
                                        words[1] +
                                        "' is not a valid valve number.");
                    return;
                }
                controller.changeValveState(Integer.parseInt(words[1]), true);
            } else {
                if(words[0].equals("closevalve")) {
                    if(words.length != 2) {
                        renderer.outputLine("Error: worng number of arguments to command '" +
                                            command + "'");
                        return;
                    }
                    if(Integer.parseInt(words[1])>0 & Integer.parseInt(words[1])<10){
                        renderer.outputLine("Error: '" +
                                            words[1] +
                                            "' is not a valid valve number.");
                        return;
                    }
                    controller.changeValveState(Integer.parseInt(words[1]), false);
                } else {
                    if(words[0].equals("pumpon")) {
                        if(words.length !=2) {
                            renderer.outputLine("Error: worng number of arguments to command '" +
                                            command + "'");
                        return;
                        }
                        if(Integer.parseInt(words[1])>0 & Integer.parseInt(words[1])<10){
                        renderer.outputLine("Error: '" +
                                            words[1] +
                                            "' is not a valid valve number.");
                        return;
                    }
                    controller.changePumpState(Integer.parseInt(words[1]), true);
                } else {
                      if(words[0].equals("pumpoff")) {
                        if(words.length !=2) {
                            renderer.outputLine("Error: worng number of arguments to command '" +
                                            command + "'");
                        return;
                        }
                        if(Integer.parseInt(words[1])>0 & Integer.parseInt(words[1])<10){
                            renderer.outputLine("Error: '" +
                                            words[1] +
                                            "' is not a valid valve number.");
                        return;
                    }
                    controller.changePumpState(Integer.parseInt(words[1]), false);  
                    } else {
                          if(words[0].equals("repair")) {
                              if(words[1].equals("pump")) {
                                  if(words.length != 3) {
                                      renderer.outputLine("Error: worng number of arguments to command '" +
                                            command + "'");
                                      return;
                                  }
                                  if(Integer.parseInt(words[2])>0 & Integer.parseInt(words[2])<10){
                                    renderer.outputLine("Error: '" +
                                            words[2] +
                                            "' is not a valid valve number.");
                        return;
                              }
                                  //controller.repairPump(Integer.parseInt(words[2]));
                              } else {
                                  if(words.length != 2) {
                                      renderer.outputLine("Error: worng number of arguments to command '" +
                                            command + "'");
                                      return;
                                  }
                                  if(words[1].equals("reactor")) {
                                      //controller.repairReactor();
                                  } else {
                                      if(words[1].equals("condenser")) {
                                          //controller.repairCondenser();
                                      } else {
                                          if(words[1].equals("turbine")) {
                                              //controller.repairTurbine();
                                          }
                                      }
                                  }
                              }
                          } else {
                              try
                              {
                              if(words[0].equals("save")) {
                                  controller.saveGame();
                              } else {
                                  if(words[0].equals("load")) {
                                      controller.loadGame();
                                  }
                              }
                              }
                              catch(JsonProcessingException err)
                              {
                                  renderer.outputLine("Unable to save file");
                              }
                          }
                      }
            }
            renderer.outputLine("Error: Unknown command '" + words[0] + "'");
        }
    }
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
