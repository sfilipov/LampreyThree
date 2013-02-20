/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
public class LogoScreen extends AbstractScreen {
    private final static Texture logoTexture = new Texture(Gdx.files.internal("assets\\logo\\lampreylogo.png"));
    private Image logo;
    
    public LogoScreen(BackyardReactor game) {
        super(game);
        logo = new Image(logoTexture);
        logo.setPosition(0, 0);
    }
    
    @Override
    public void show() {
        Action action = Actions.sequence(Actions.fadeOut(3f),
                                         new Action() {
                                             public boolean act(float delta) {
                                                 game.setScreen(game.getMenuScreen());
                                                 return true;
                                             }
                                         });
        logo.addAction(action);
        stage.addActor(logo);
    }
}
