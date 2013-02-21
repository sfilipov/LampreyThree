/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import lamprey.seprphase3.GUI.BackyardReactor;
import lamprey.seprphase3.GUI.Images.GameOverText;
import lamprey.seprphase3.GUI.Images.HoverButton;
import lamprey.seprphase3.GUI.Images.HoverButtonType;

/**
 *
 * @author Simeon
 */
public class GameOverScreen extends AbstractScreen {
    private Texture gameoverBg;
    private Texture gameoverSign;
    private Texture gameoverRestart;
    private Texture gameoverMenu;
    
    private Image bgImage;
    private Image signImage;
    private HoverButton restartImage;
    private HoverButton menuImage;
    
    private GameOverText score;
    
    public GameOverScreen(BackyardReactor game, PlantController controller, PlantStatus status, GameManager manager) {
        super(game, controller, status, manager);
        gameoverBg      = new Texture(Gdx.files.internal("assets\\gameover\\gameoverBg.png"));
        gameoverSign    = new Texture(Gdx.files.internal("assets\\gameover\\gameoverSign.png"));
        gameoverRestart = new Texture(Gdx.files.internal("assets\\gameover\\gameoverRestart.png"));
        gameoverMenu    = new Texture(Gdx.files.internal("assets\\gameover\\gameoverMenu.png"));
        score = new GameOverText(status);

    }
    
    @Override
    public void show() {
        super.show();
        bgImage   = new Image(gameoverBg);
        signImage = new Image(gameoverSign);
        restartImage = new HoverButton(gameoverRestart, HoverButtonType.Transparent);
        menuImage    = new HoverButton(gameoverMenu, HoverButtonType.Transparent);
        
        signImage.setPosition(338, 287);
        restartImage.setPosition(391, 188);
        menuImage.setPosition(367, 149);
        score.setPosition(260, 270);
        
        restartImage.addListener(getRestartListener());
        menuImage.addListener(getMenuListener());
        
        stage.addActor(bgImage);
        stage.addActor(signImage);
        stage.addActor(restartImage);
        stage.addActor(menuImage);
        stage.addActor(score);
    }
    
    public ClickListener getRestartListener() {
        return new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.initGame();
                game.setScreen(game.getGameplayScreen());
                return true;
            }
        };
    }
    
    public ClickListener getMenuListener() {
        return new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                manager.initGame();
                game.setScreen(game.getMenuScreen());
                return true;
            }
        };
    }
}
