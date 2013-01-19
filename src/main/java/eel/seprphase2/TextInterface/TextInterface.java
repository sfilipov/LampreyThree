/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eel.seprphase2.TextInterface;

import eel.seprphase2.Simulator.PhysicalModel;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;

/**
 *
 * @author Yazidi
 */
public class TextInterface {

    private PlantController plantController;
    private PlantStatus plantStatus;
    private TextRenderer textRenderer;
    private LineReader lineReader;

    /**
     *
     * @param plantController
     * @param plantStatus
     * @param textRenderer
     * @param lineReader
     */
    public TextInterface(PlantController plantController,
                         PlantStatus plantStatus,
                         TextRenderer textRenderer,
                         LineReader lineReader) {
        this.plantController = plantController;
        this.plantStatus = plantStatus;
        this.textRenderer = textRenderer;
        this.lineReader = lineReader;
    }
    
    /**
     *
     */
    public void askForUsername(){
        Parser parser = new Parser(plantController, textRenderer);
        textRenderer.outputLine("Please Enter Username:");
        parser.setUsername(lineReader.readLine());
    }

    /**
     *
     */
    public void showStatus() {
        textRenderer.outputLine(String.format("%25s : %8d     %22s : %8d", 
                                                  "Control Rod Position", plantStatus.controlRodPosition().points(),
                                                  "Reactor Water Level",plantStatus.reactorWaterLevel().points()));
        textRenderer.outputLine(String.format("%25s : %-8f    %22s : %8f", 
                                                  "Reactor Temperature", plantStatus.reactorTemperature().inCelsius(),
                                                  "Reactor Pressure",plantStatus.reactorPressure().inAtmospheres()));
        textRenderer.outputLine(String.format("%25s : %8f    %22s : %8f", 
                                                  "Condenser Temperature", plantStatus.condenserTemperature().inCelsius(),
                                                  "Condenser Pressure", plantStatus.condenserPressure().inAtmospheres()));
        textRenderer.outputLine(String.format("%25s : %8d", 
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
    public void processCommand() {
        Parser parser = new Parser(plantController, textRenderer);
        
        parser.parseCommand(lineReader.readLine());
    }
}
