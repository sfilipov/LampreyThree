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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    
    public void showWelcomeMessage() {
        textRenderer.outputLine(
"_________ .__                              ___.          .__   \n" +
"\\_   ___ \\|  |__   ___________  ____   ____\\_ |__ ___.__.|  |  \n" +
"/    \\  \\/|  |  \\_/ __ \\_  __ \\/    \\ /  _ \\| __ <   |  ||  |  \n" +
"\\     \\___|   Y  \\  ___/|  | \\/   |  (  <_> ) \\_\\ \\___  ||  |__\n" +
" \\______  /___|  /\\___  >__|  |___|  /\\____/|___  / ____||____/\n" +
"        \\/     \\/     \\/           \\/           \\/\\/           \n" +
"                         _________        .__       .__        \n" +
"        A                \\_   ___ \\_______|__| _____|__| ______\n" +
"     TEAM EEL            /    \\  \\/\\_  __ \\  |/  ___/  |/  ___/\n" +
"    PRODUCTION           \\     \\____|  | \\/  |\\___ \\|  |\\___ \\ \n" +
"                          \\______  /|__|  |__/____  >__/____  >\n" +
"                                 \\/               \\/        \\/ \n");
    }
    
    
    public int askForAction() {
        Parser parser = new Parser(plantController, gameManager, textRenderer);
        textRenderer.outputLine("\n\nPlease choose an option and press enter: ");
        textRenderer.outputLine("\t[1] New Game ");
        textRenderer.outputLine("\t[2] Load Game \n\n\n");
        
        int result = parser.chooseAction(lineReader.readLine());
        while(result < 0 || result > 3) {
            textRenderer.outputLine("Please choose a valid option!");
            result = parser.chooseAction(lineReader.readLine());
        }
        
        return result;
    }
    
    public void showIntroText() {
        textRenderer.outputLine("\f");
        textRenderer.outputLine("You are in command of the Chernobyl power plant. ");
        textRenderer.outputLine("<<show map of plant>>");
    }
    
    public void showSavedGames() {
        Parser parser = new Parser(plantController, gameManager, textRenderer);
        
        textRenderer.outputLine("\fPlease enter a game number and press enter:");
        try
        {
            int i = 0;
            for(String game : gameManager.listGames())
            {
                String[] bits = game.split("\\.");
                Timestamp t = new Timestamp(Long.parseLong(bits[3]));
                Date d = new Date(t.getTime());

                SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                textRenderer.outputLine(String.format("[%d] Saved: %s", i++, date.format(d)));
                //renderer.outputLine(game);
            }
        }
        catch(Exception e)
        {
            
        }
        
        int result = parser.chooseAction(lineReader.readLine());
        while(result < 0 || result > gameManager.listGames().length ) {
            textRenderer.outputLine("Please choose a valid option!");
            result = parser.chooseAction(lineReader.readLine());
            
        }
        textRenderer.outputLine("\f");
        
        gameManager.loadGame(result);
        
    }
}
