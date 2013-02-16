/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.awt.Transparency;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
public class GameplayScreen extends AbstractScreen {
    Texture gamebgTexture;
    Texture borderTexture;
    Texture condenserTexture;
    Texture coolerTexture;
    Texture pipesTexture;
    Texture poweroutTexture;
    Texture reactorTexture;
    Texture turbineTexture;
    Texture mechanicTexture;
    Texture pauseTexture;
    Image gamebgImage;
    Image borderImage;
    Image condenserImage;
    Image coolerImage;
    Image pipesImage;
    Image poweroutImage;
    Image reactorImage;
    Image turbineImage;
    Image mechanicImage;
    Image pauseImage;
    
    float deltaSum;
    float mechanicX;
    
    public GameplayScreen(BackyardReactor game) {
        super(game);
        
        gamebgTexture    = new Texture(Gdx.files.internal("assets\\game\\bg.png"));
        borderTexture    = new Texture(Gdx.files.internal("assets\\game\\border.png"));
        condenserTexture = new Texture(Gdx.files.internal("assets\\game\\condenser.png"));
        coolerTexture    = new Texture(Gdx.files.internal("assets\\game\\cooler.png"));
        pipesTexture     = new Texture(Gdx.files.internal("assets\\game\\pipes.png"));
        poweroutTexture  = new Texture(Gdx.files.internal("assets\\game\\powerout.png"));
        reactorTexture   = new Texture(Gdx.files.internal("assets\\game\\reactor.png"));
        turbineTexture   = new Texture(Gdx.files.internal("assets\\game\\turbine.png"));
        mechanicTexture  = new Texture(Gdx.files.internal("assets\\game\\mechanic.png"));
        pauseTexture     = new Texture(Gdx.files.internal("assets\\game\\pausebutton.png"));
        
        gamebgImage    = new Image(gamebgTexture);
        borderImage    = new Image(borderTexture);
        condenserImage = new Image(condenserTexture);
        coolerImage    = new Image(coolerTexture);
        pipesImage     = new Image(pipesTexture);
        poweroutImage  = new Image(poweroutTexture);
        reactorImage   = new Image(reactorTexture);
        turbineImage   = new Image(turbineTexture);
        mechanicImage  = new Image(mechanicTexture);
        pauseImage     = new Image(pauseTexture);
        
        gamebgImage.setPosition(0, 0);
        borderImage.setPosition(0, 0);
        condenserImage.setPosition(521, 110);
        coolerImage.setPosition(737, 102);
        pipesImage.setPosition(131, 175);
        poweroutImage.setPosition(709, 397);
        reactorImage.setPosition(32, 113);
        turbineImage.setPosition(448, 410);
        mechanicImage.setPosition(650, 75);
        pauseImage.setPosition(20, 458);
        
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
        
        deltaSum = 0f;
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
    
    @Override
    public void render(float delta) {
        super.render(delta);
        
        deltaSum += delta;
        if (deltaSum > 0.016) {
            deltaSum -= 0.016;
            mechanicX = mechanicImage.getX();
            if (mechanicX > 50f) {
                mechanicX -= 4f;
                System.out.println(mechanicX);
                mechanicImage.setX(mechanicX);
            }
        }
    }
    
    @Override
    public void hide() {
        stage.clear();
    }
    
    private InputListener getCondenserListener() {
        return new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Do something to the Condenser on click
                System.out.println("Click on Condenser");
            }
        };
    }
    
    private InputListener getReactorListener() {
        return new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //Do something to the Condenser on click
                System.out.println("Click on Reactor");
            }
        };
    }
    
    //DOESN'T Detect key presses
    private InputListener getPauseListener() {
        return new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getPauseScreen());
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
