/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
public class CreditsScreen extends AbstractScreen {
    private final static Texture creditsBg   = new Texture(Gdx.files.internal("assets\\credits\\creditsbg.png"));
    private final static Texture creditsText = new Texture(Gdx.files.internal("assets\\credits\\credits.png"));
    private Image creditsBgImage;
    private Image creditsTextImage;
    
    public CreditsScreen(BackyardReactor game) {
        super(game);
        creditsBgImage   = new Image(creditsBg);
        creditsTextImage = new Image(creditsText);
    }
    
    @Override
    public void show() {
        super.show();
        
       creditsTextImage.addListener(getMenuListener());
       
       stage.addActor(creditsBgImage);
       stage.addActor(creditsTextImage);
    }
    
    public ClickListener getMenuListener() {
        return new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getMenuScreen());
                return true;
            }
        };
    }
}
