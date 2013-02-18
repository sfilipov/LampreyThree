/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import eel.seprphase2.Simulator.CannotRepairException;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import lamprey.seprphase3.GUI.BackyardReactor;
import lamprey.seprphase3.GUI.Images.HoverButton;
import lamprey.seprphase3.GUI.Images.Mechanic;
import lamprey.seprphase3.GUI.GameplayListeners;

/**
 *
 * @author Simeon
 */
public class GameplayScreen extends AbstractScreen {
    GameplayListeners listeners;
    
    Texture gamebgTexture;
    Texture borderTexture;
    Texture condenserTexture;
    Texture coolerTexture;
    Texture pipesTexture;
    Texture poweroutTexture;
    Texture reactorbackTexture;
    Texture turbineTexture;
    Texture pauseTexture;
    Texture consolebackTexture;
    Texture crUpTexture;
    Texture crDownTexture;
    Texture pump1Texture;
    Texture pump2Texture;
    Texture valve1Texture;
    Texture valve2Texture;
    Texture sErrorTexture;
    Image gamebgImage;
    Image borderImage;
    HoverButton condenserImage;
    Image coolerImage;
    Image pipesImage;
    Image poweroutImage;
    HoverButton reactorImage;
    Image turbineImage;
    Mechanic mechanicImage;
    HoverButton pauseImage;
    Image consolebackImage;
    HoverButton crUpImage;
    HoverButton crDownImage;
    HoverButton pump1Image;
    HoverButton pump2Image;
    HoverButton valve1Image;
    HoverButton valve2Image;
    Image sErrorImage;
    
    float deltaSum;
    float mechanicX;
    float moveMechanicTo;
    
    Direction mechanicDirection;
    
    public GameplayScreen(BackyardReactor game, PlantController controller, PlantStatus status, GameManager manager) {
        super(game, controller, status, manager);
        listeners = new GameplayListeners(this, controller, status, manager);
        
        gamebgTexture      = new Texture(Gdx.files.internal("assets\\game\\bg.png"));
        borderTexture      = new Texture(Gdx.files.internal("assets\\game\\border.png"));
        condenserTexture   = new Texture(Gdx.files.internal("assets\\game\\condenser.png"));
        coolerTexture      = new Texture(Gdx.files.internal("assets\\game\\cooler.png"));
        pipesTexture       = new Texture(Gdx.files.internal("assets\\game\\pipes.png"));
        poweroutTexture    = new Texture(Gdx.files.internal("assets\\game\\powerout.png"));
        reactorbackTexture = new Texture(Gdx.files.internal("assets\\game\\reactor_back.png"));
        turbineTexture     = new Texture(Gdx.files.internal("assets\\game\\turbine.png"));
        pauseTexture       = new Texture(Gdx.files.internal("assets\\game\\pausebutton.png"));
        consolebackTexture = new Texture(Gdx.files.internal("assets\\game\\consoleback.png"));
        crUpTexture        = new Texture(Gdx.files.internal("assets\\game\\controlrodup.png"));
        crDownTexture      = new Texture(Gdx.files.internal("assets\\game\\controlroddown.png"));
        pump1Texture       = new Texture(Gdx.files.internal("assets\\game\\pump1.png"));
        pump2Texture       = new Texture(Gdx.files.internal("assets\\game\\pump2.png"));
        valve1Texture      = new Texture(Gdx.files.internal("assets\\game\\valve1.png"));
        valve2Texture      = new Texture(Gdx.files.internal("assets\\game\\valve2.png"));
        sErrorTexture      = new Texture(Gdx.files.internal("assets\\game\\softwareError.png"));
        

        
        gamebgImage      = new Image(gamebgTexture);
        borderImage      = new Image(borderTexture);
        condenserImage   = new HoverButton(condenserTexture, false);
        coolerImage      = new HoverButton(coolerTexture, false);
        pipesImage       = new Image(pipesTexture);
        poweroutImage    = new Image(poweroutTexture);
        reactorImage     = new HoverButton(reactorbackTexture, false);
        turbineImage     = new Image(turbineTexture);
        mechanicImage    = new Mechanic();
        pauseImage       = new HoverButton(pauseTexture,  true);
        consolebackImage = new Image(consolebackTexture);
        crUpImage        = new HoverButton(crUpTexture,   false);
        crDownImage      = new HoverButton(crDownTexture, false);
        pump1Image       = new HoverButton(pump1Texture,  false);
        pump2Image       = new HoverButton(pump2Texture,  false);
        valve1Image      = new HoverButton(valve1Texture, false);
        valve2Image      = new HoverButton(valve2Texture, false);
        sErrorImage      = new Image(sErrorTexture);
        
        gamebgImage.setPosition(0, 0);
        borderImage.setPosition(0, 0);
        condenserImage.setPosition(522, 110);
        coolerImage.setPosition(804, 122);
        pipesImage.setPosition(131, 149);
        poweroutImage.setPosition(705, 405);
        reactorImage.setPosition(32, 113);
        turbineImage.setPosition(448, 410);
        mechanicImage.setPosition(630, 75);
        mechanicImage.moveMechanicTo(630f); //ensures the mechanic is initially not moving
        pauseImage.setPosition(20, 458);
        consolebackImage.setPosition(260, 0);
        crUpImage.setPosition(545, 75);
        crDownImage.setPosition(560, 21);
        pump1Image.setPosition(323, 71);
        pump2Image.setPosition(373, 76);
        valve1Image.setPosition(300, 22);
        valve2Image.setPosition(353, 22);
        sErrorImage.setPosition(433, 18);
        
        
        //Makes image semi-transparent
        pauseImage.setColor(1f, 1f, 1f, 0.75f);
        //Makes image three times smaller
        pauseImage.setScale(0.33f);
        
        condenserImage.addListener(listeners.getCondenserListener());
//        coolerImage.addListener(listeners.getCoolerListener());
        reactorImage.addListener(listeners.getReactorListener());
        pauseImage.addListener(listeners.getPauseListener());
        crUpImage.addListener(listeners.getConrolRodsUpListener());
        crDownImage.addListener(listeners.getConrolRodsDownListener());
        valve1Image.addListener(listeners.getValve1Listener());
        valve2Image.addListener(listeners.getValve2Listener());
        pump1Image.addListener(listeners.getPump1Listener());
        pump2Image.addListener(listeners.getPump2Listener());
        
    }
    
    @Override
    public void show() {
        super.show();
        
        stage.addActor(gamebgImage);
        stage.addActor(borderImage);
        stage.addActor(pipesImage);
        stage.addActor(coolerImage);
        stage.addActor(poweroutImage);
        stage.addActor(reactorImage);
        stage.addActor(turbineImage);
        stage.addActor(condenserImage);
        stage.addActor(mechanicImage);
        stage.addActor(pauseImage);
        stage.addActor(consolebackImage);
        stage.addActor(crUpImage);
        stage.addActor(crDownImage);
        stage.addActor(pump1Image);
        stage.addActor(pump2Image);
        stage.addActor(valve1Image);
        stage.addActor(valve2Image);
        stage.addActor(sErrorImage);
        
        deltaSum = 0f;
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
    
    @Override
    public void render(float delta) {
        deltaSum += delta;
        if (deltaSum > 0.0167) {
            deltaSum -= 0.0167;
        }
        super.render(delta);
    }
    
    @Override
    public void hide() {
        stage.clear();
    }
    
    public void moveMechanicTo(float destination) {
        mechanicImage.moveMechanicTo(destination);
    }
    
    public BackyardReactor getGame() {
        return this.game;
    }
}
