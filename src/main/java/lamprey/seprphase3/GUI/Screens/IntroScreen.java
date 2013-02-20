/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
public class IntroScreen extends AbstractScreen {
        private Texture introbgTexture;
        private Texture introOverlayTexture;
        
        private Image introbg;
        private Image introOverlay;
        
        public IntroScreen(BackyardReactor game) {
            super(game);
            introbgTexture      = new Texture(Gdx.files.internal("assets\\intro\\introbg.png"));
            introOverlayTexture = new Texture(Gdx.files.internal("assets\\intro\\introoverlay.png"));
            
            introbg      = new Image(introbgTexture);
            introOverlay = new Image(introOverlayTexture);
        }
        
        @Override
        public void show() {
            super.show();
            introbg.addListener(getGameplayListener());
            introOverlay.addListener(getGameplayListener());
            
            stage.addActor(introbg);
            stage.addActor(introOverlay);
        }
        
        public ClickListener getGameplayListener() {
            return new ClickListener() {
                @Override
                public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(game.getGameplayScreen());
                    return true;
                }
        };
    }
}
