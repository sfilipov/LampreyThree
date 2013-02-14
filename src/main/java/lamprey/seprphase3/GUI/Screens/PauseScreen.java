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

/**
 *
 * @author Simeon
 */
public class PauseScreen extends GameplayScreen {
    GameplayScreen screen;
    Texture pauseTexture;
    Image   pauseImage;
    
    public PauseScreen(GameplayScreen screen) {
        super(screen.game, screen.stage);
    }
    
    @Override
    public void show() {
        super.show();
        
        pauseTexture = new Texture(Gdx.files.internal("assets\\pausepopup.png"));
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        
        pauseImage = new Image(pauseTexture);
        pauseImage.setPosition(240, 135);
        pauseImage.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
        }
        
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getGameplayScreen());
        }
        });
        
        
        stage.addActor(pauseImage);
    }
}
