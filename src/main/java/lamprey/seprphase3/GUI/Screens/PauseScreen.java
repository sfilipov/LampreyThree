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
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
public class PauseScreen extends AbstractScreen {
    private Texture pauseBlurTexture;
    private Texture pausePausedTexture;
    private Texture pauseReturnTexture;
    private Texture pauseSaveTexture;
    private Texture pauseLoadTexture;
    private Texture pauseMenuTexture;
    
    private Image pauseBlurImage;
    private Image pausePausedImage;
    private Image pauseReturnImage;
    private Image pauseSaveImage;
    private Image pauseLoadImage;
    private Image pauseMenuImage;

    
    public PauseScreen(BackyardReactor game, PlantController controller, PlantStatus status, GameManager manager) {
        super(game, controller, status, manager);
        pauseBlurTexture = new Texture(Gdx.files.internal("assets\\pause\\pauseblur.png"));
        pausePausedTexture = new Texture(Gdx.files.internal("assets\\pause\\pauseblur.png"));
        pauseReturnTexture = new Texture(Gdx.files.internal("assets\\pause\\pauseblur.png"));
        pauseSaveTexture = new Texture(Gdx.files.internal("assets\\pause\\pauseblur.png"));
        pauseLoadTexture = new Texture(Gdx.files.internal("assets\\pause\\pauseblur.png"));
        pauseMenuTexture = new Texture(Gdx.files.internal("assets\\pause\\pauseblur.png"));
    }
    
    @Override
    public void show() {
        super.show();
        pauseBlurImage   = new Image(pauseBlurTexture);
        pausePausedImage = new Image(pausePausedTexture);
        pauseReturnImage = new Image(pauseReturnTexture);
        pauseSaveImage   = new Image(pauseSaveTexture);
        pauseLoadImage   = new Image(pauseLoadTexture);
        pauseMenuImage   = new Image(pauseMenuTexture);

        stage.addActor(pauseBlurImage);
        stage.addActor(pausePausedImage);
        stage.addActor(pauseReturnImage);
        stage.addActor(pauseSaveImage);
        stage.addActor(pauseLoadImage);
        stage.addActor(pauseMenuImage);
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
    
    @Override
    public void hide() {
        stage.clear();
    }
    
    public InputListener getPopupListener() {
        return new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getGameplayScreen());
            }
        };
    }
}
