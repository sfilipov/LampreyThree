/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    Texture pauseTexture;
    Image gamebgImage;
    Image borderImage;
    Image condenserImage;
    Image coolerImage;
    Image pipesImage;
    Image poweroutImage;
    Image reactorImage;
    Image turbineImage;
//    Image pauseImage;
    
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
    }
    
    @Override
    public void show() {
        super.show();
        
        gamebgImage    = new Image(gamebgTexture);
        borderImage    = new Image(borderTexture);
        condenserImage = new Image(condenserTexture);
        coolerImage    = new Image(coolerTexture);
        pipesImage     = new Image(pipesTexture);
        poweroutImage  = new Image(poweroutTexture);
        reactorImage   = new Image(reactorTexture);
        turbineImage   = new Image(turbineTexture);
//        pauseImage     = new Image();
        
        gamebgImage.setPosition(0, 0);
        borderImage.setPosition(0, 0);
        condenserImage.setPosition(521, 110);
        coolerImage.setPosition(737, 102);
        pipesImage.setPosition(131, 175);
        poweroutImage.setPosition(709, 397);
        reactorImage.setPosition(32, 113);
        turbineImage.setPosition(448, 410);
//        pauseImage.setPosition(0, 0);
        
        condenserImage.addListener(getCondenserListener());
        reactorImage.addListener(getReactorListener());
//        pauseImage.addListener(getPauseListener());
        
        stage.addActor(gamebgImage);
        stage.addActor(borderImage);
        stage.addActor(pipesImage);
        stage.addActor(coolerImage);
        stage.addActor(poweroutImage);
        stage.addActor(reactorImage);
        stage.addActor(turbineImage);
        stage.addActor(condenserImage);
//        stage.addActor(pauseImage);
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
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
                game.setScreen(game.getPauseScreen());
            }
        };
    }
    
    //DOESN'T Detect key presses
//    private InputListener getPauseListener() {
//        return new InputListener() {
////            @Override
////            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
////                return true;
////            }
////            
////            @Override
////            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
////                //Do something to the Pause on click
////                System.out.println("Click on Pause");
////            }
//            @Override
//            public boolean keyDown(InputEvent event, int keycode) {
//                if(keycode == Keys.A) {
//                    game.setScreen(game.getPauseScreen());
//                    return true;
//                }
//                return false;
//            }
//        };
//    }
}
