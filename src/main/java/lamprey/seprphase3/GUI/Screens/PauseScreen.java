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
public class PauseScreen extends GameplayScreen {
    GameplayScreen screen;
    Texture pausePopupTexture;
    Image   pausePopupImage;
    
    public PauseScreen(BackyardReactor game) {
        super(game);
        pausePopupTexture = new Texture(Gdx.files.internal("assets\\pause\\pausepopup.png"));
    }
    
    @Override
    public void show() {
        super.show();
        pausePopupImage = new Image(pausePopupTexture);
        pausePopupImage.setPosition(240, 135);
        
        pausePopupImage.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getGameplayScreen());
            }
        });
        
        stage.addActor(pausePopupImage);
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
    
    @Override
    public void hide() {
        stage.clear();
    }
}
