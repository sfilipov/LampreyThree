/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
public class GameplayScreen extends AbstractScreen {
    Texture gameUITexture;
    Image   gameUIImage;
    
    
    public GameplayScreen(BackyardReactor game, Stage stage) {
        super(game, stage);
    }
    
    @Override
    public void show() {
        super.show();
        
        gameUITexture = new Texture(Gdx.files.internal("assets\\backyardreactor2.png"));
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        
        gameUIImage = new Image(gameUITexture);
        gameUIImage.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
        }
        
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getPauseScreen());
        }

        });
        stage.addActor(gameUIImage);
    }
}
