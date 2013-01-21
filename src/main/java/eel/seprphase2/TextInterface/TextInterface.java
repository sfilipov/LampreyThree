/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import com.fasterxml.jackson.core.JsonProcessingException;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import eel.seprphase2.Utilities.Pressure;

/**
 *
 * @author Yazidi
 */
public class TextInterface {

    private PlantController plantController;
    private PlantStatus plantStatus;
    private GameManager gameManager;
    private TextRenderer textRenderer;
    private LineReader lineReader;
    private final Pressure condenserWarningPressure = new Pressure(25530000);
    /**
     *
     * @param plantController
     * @param plantStatus
     * @param textRenderer
     * @param lineReader
     */
    public TextInterface(PlantController plantController, PlantStatus plantStatus, GameManager gameManager,
                         TextRenderer textRenderer, LineReader lineReader) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.gameManager = gameManager;
        this.textRenderer = textRenderer;
        this.lineReader = lineReader;
    }
    
    /**
     *
     */
    public void askForUsername(){
        Parser parser = new Parser(plantController, gameManager, textRenderer);
        textRenderer.outputLine("Please Enter Username:");
        parser.setUsername(lineReader.readLine());
    }

    /**
     *
     */
    public void showStatus() {
        textRenderer.outputLine(String.format("%25s : %8f     %22s : %8f", 
                                                  "Control Rod Position", plantStatus.controlRodPosition().points(),
                                                  "Reactor Water Level",plantStatus.reactorWaterLevel().points()));
        textRenderer.outputLine(String.format("%25s : %-8f    %22s : %8f", 
                                                  "Reactor Temperature", plantStatus.reactorTemperature().inCelsius(),
                                                  "Reactor Pressure",plantStatus.reactorPressure().inAtmospheres()));
        textRenderer.outputLine(String.format("%25s : %8f    %22s : %8f", 
                                                  "Condenser Temperature", plantStatus.condenserTemperature().inCelsius(),
                                                  "Condenser Pressure", plantStatus.condenserPressure().inAtmospheres()));
        textRenderer.outputLine(String.format("%25s : %8f", 
                                                  "Condenser Water Level", plantStatus.condenserWaterLevel().points()));
        textRenderer.outputLine(String.format("%25s : %f", 
                                                  "Energy Generated", plantStatus.energyGenerated().inKJoules()));
        
        
        
        /*
         * 
         */
        if(plantStatus.reactorWaterLevel().points()<plantStatus.reactorMinimumWaterLevel().points())
        {
            textRenderer.outputLine("WARNING: REACTOR WATER LEVEL TOO LOW");
        }
        
        
        /*
         * 
         */
        if(plantStatus.condenserPressure().greaterThan(condenserWarningPressure))
        {
           textRenderer.outputLine("WARNING: CONDENSER PRESSURE TOO HIGH"); 
        }
        
        /*
         * 
         */
        if(plantStatus.listFailedComponents().length>0)
        {
            for(String failedComponent : plantStatus.listFailedComponents()) {
                textRenderer.outputLine("WARNING: "+ failedComponent + " HAS FAILED"); 
            }
           
        }
        
        
        /*
        textRenderer.outputLine("Control Rod Position: " +
                                plantStatus.controlRodPosition());
        textRenderer.outputLine("Reactor Temperature: " + plantStatus
                .reactorTemperature());
        textRenderer.outputLine("Reactor Pressure: " + plantStatus.reactorPressure());
        textRenderer.outputLine("Water Level: " + plantStatus.reactorWaterLevel());
        textRenderer.outputLine("Energy Generated: " + plantStatus.energyGenerated());
        */
    }

    /**
     *
     */
    public void processCommand() throws DoNotStep {
        Parser parser = new Parser(plantController, gameManager, textRenderer);
        
        parser.parseCommand(lineReader.readLine());
    }
}
