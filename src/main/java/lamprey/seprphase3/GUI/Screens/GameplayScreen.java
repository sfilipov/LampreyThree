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
import lamprey.seprphase3.GUI.BackyardReactor;
import lamprey.seprphase3.GUI.Images.Mechanic;

/**
 *
 * @author Simeon
 */
public class GameplayScreen extends AbstractScreen {
    private static final int MECHANIC_COLS = 5;
    private static final int MECHANIC_ROWS = 4;
    TextureRegion[] mechanicFrames;
    
    Texture gamebgTexture;
    Texture borderTexture;
    Texture condenserTexture;
    Texture coolerTexture;
    Texture pipesTexture;
    Texture poweroutTexture;
    Texture reactorTexture;
    Texture turbineTexture;
    Texture mechanicSheet;
    Texture mechanicNotMovingTexture;
    Texture pauseTexture;
    Texture consolebackTexture;
    Image gamebgImage;
    Image borderImage;
    Image condenserImage;
    Image coolerImage;
    Image pipesImage;
    Image poweroutImage;
    Image reactorImage;
    Image turbineImage;
    Mechanic mechanicImage;
    Image pauseImage;
    Image consolebackImage;
     
    float deltaSum;
    float mechanicX;
    float moveMechanicTo;
    
    Direction mechanicDirection;
    
    public GameplayScreen(BackyardReactor game) {
        super(game);
        
        gamebgTexture      = new Texture(Gdx.files.internal("assets\\game\\bg.png"));
        borderTexture      = new Texture(Gdx.files.internal("assets\\game\\border.png"));
        condenserTexture   = new Texture(Gdx.files.internal("assets\\game\\condenser.png"));
        coolerTexture      = new Texture(Gdx.files.internal("assets\\game\\cooler.png"));
        pipesTexture       = new Texture(Gdx.files.internal("assets\\game\\pipes.png"));
        poweroutTexture    = new Texture(Gdx.files.internal("assets\\game\\powerout.png"));
        reactorTexture     = new Texture(Gdx.files.internal("assets\\game\\reactor.png"));
        turbineTexture     = new Texture(Gdx.files.internal("assets\\game\\turbine.png"));
        pauseTexture       = new Texture(Gdx.files.internal("assets\\game\\pausebutton.png"));
        consolebackTexture = new Texture(Gdx.files.internal("assets\\game\\consoleback.png"));
        mechanicSheet      = new Texture(Gdx.files.internal("assets\\game\\mechanic_spritesheet.png"));
        mechanicNotMovingTexture = new Texture(Gdx.files.internal("assets\\game\\mechanic_notmoving.png"));
        
        TextureRegion[][] split = TextureRegion.split(mechanicSheet, 
                                                      mechanicSheet.getWidth()  / MECHANIC_COLS, 
                                                      mechanicSheet.getHeight() / MECHANIC_ROWS);
        mechanicFrames = new TextureRegion[MECHANIC_COLS * MECHANIC_ROWS];
        int index = 0;
        for (int i=0; i < MECHANIC_ROWS; i++) {
            for (int j=0; j < MECHANIC_COLS; j++) {
                mechanicFrames[index] = split[i][j];
                index++;
            }
        }
        
        
        gamebgImage      = new Image(gamebgTexture);
        borderImage      = new Image(borderTexture);
        condenserImage   = new Image(condenserTexture);
        coolerImage      = new Image(coolerTexture);
        pipesImage       = new Image(pipesTexture);
        poweroutImage    = new Image(poweroutTexture);
        reactorImage     = new Image(reactorTexture);
        turbineImage     = new Image(turbineTexture);
        mechanicImage    = new Mechanic(mechanicFrames, mechanicNotMovingTexture, Direction.Right);
        pauseImage       = new Image(pauseTexture);
        consolebackImage = new Image(consolebackTexture);
        
        gamebgImage.setPosition(0, 0);
        borderImage.setPosition(0, 0);
        condenserImage.setPosition(521, 110);
        coolerImage.setPosition(737, 102);
        pipesImage.setPosition(131, 175);
        poweroutImage.setPosition(709, 397);
        reactorImage.setPosition(32, 113);
        turbineImage.setPosition(448, 410);
        mechanicImage.setPosition(650, 75);
        mechanicImage.moveMechanicTo(650f);
        pauseImage.setPosition(20, 458);
        consolebackImage.setPosition(260, 0);
        
        
        //Makes image semi-transparent
        pauseImage.setColor(1f, 1f, 1f, 0.75f);
        //Makes image three times smaller
        pauseImage.setScale(0.33f);
        
        condenserImage.addListener(getCondenserListener());
        reactorImage.addListener(getReactorListener());
        pauseImage.addListener(getPauseListener());
        
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
            mechanicImage.moveMechanic();
        }
        super.render(delta);
    }
    
    @Override
    public void hide() {
        stage.clear();
    }
    
    private InputListener getCondenserListener() {
        return new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mechanicImage.moveMechanicTo(650f);
                return true;
            }
        };
    }
    
    private InputListener getReactorListener() {
        return new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mechanicImage.moveMechanicTo(60f);
                return true;
            }
        };
    }
    
    //DOESN'T Detect key presses
    private InputListener getPauseListener() {
        return new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getPauseScreen());
                return true;
            }
            
//            @Override
//            public boolean keyDown(InputEvent event, int keycode) {
//                if(keycode == Keys.A) {
//                    game.setScreen(game.getPauseScreen());
//                    return true;
//                }
//                return false;
//            }
        };
    }
}
