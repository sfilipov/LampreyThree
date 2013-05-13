package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import java.io.IOException;
import lamprey.seprphase3.GUI.BackyardReactor;
import lamprey.seprphase3.GUI.Images.HoverButton;
import lamprey.seprphase3.GUI.Images.HoverButtonType;

/**
 *
 * @author Simeon
 */
public class MenuScreen extends AbstractScreen {
    private Texture menubgTexture;
    private Texture gamelogoTexture;
    private Texture newgameTexture;
    private Texture loadgameTexture;
    private Texture creditsTexture;
    private Texture lampreylogoTexture;
    private Image menubgImage;
    private Image gamelogoImage;
    private HoverButton newgameImage;
    private HoverButton loadgameImage;
    private HoverButton creditsImage;
    private Image lampreylogoImage;
    
    private boolean isInputShown;
    
    private OperatorNameInput nameListener;
    
    public MenuScreen(BackyardReactor game, PlantController controller, PlantStatus status, GameManager manager) {
        super(game, controller, status, manager);
        menubgTexture      = new Texture(Gdx.files.internal("assets\\menu\\menubg.png"));
        gamelogoTexture    = new Texture(Gdx.files.internal("assets\\menu\\gamelogo.png"));
        newgameTexture     = new Texture(Gdx.files.internal("assets\\menu\\newgame.png"));
        loadgameTexture    = new Texture(Gdx.files.internal("assets\\menu\\loadgame.png"));
        creditsTexture    = new Texture(Gdx.files.internal("assets\\menu\\credits.png"));
        lampreylogoTexture = new Texture(Gdx.files.internal("assets\\menu\\lampreylogo.png"));
        
        nameListener = new OperatorNameInput();
        isInputShown = false;
    }
     
    @Override
    public void show() {
        super.show();
        
        stage.clear();
        
        menubgImage      = new Image(menubgTexture);
        gamelogoImage    = new Image(gamelogoTexture);
        newgameImage     = new HoverButton(newgameTexture, HoverButtonType.Transparent);
        loadgameImage    = new HoverButton(loadgameTexture, HoverButtonType.Transparent);
        creditsImage     = new HoverButton(creditsTexture, HoverButtonType.Transparent);
        lampreylogoImage = new Image(lampreylogoTexture);
        
        menubgImage.setPosition(0, 0);
        gamelogoImage.setPosition(268, 316);
        newgameImage.setPosition(361, 261);
        loadgameImage.setPosition(349, 214);
        creditsImage.setPosition(397, 173);
        lampreylogoImage.setPosition(436, 0);
        
        newgameImage.addListener(getNewgameListener());
        loadgameImage.addListener(getLoadListener());
        creditsImage.addListener(getCreditsListener());
        
        stage.addActor(menubgImage);
        stage.addActor(gamelogoImage);
        stage.addActor(newgameImage);
        stage.addActor(loadgameImage);
        stage.addActor(creditsImage);
        stage.addActor(lampreylogoImage);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);        
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
    }
    
    @Override
    public void hide() {
        stage.clear();
    }
    
    private ClickListener getNewgameListener() {
        return new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                if (!isInputShown) {
                    Gdx.input.getTextInput(nameListener, "Please enter your name", "");
                }
            }
        };
    }
        
    public ClickListener getLoadListener() {
        return new ClickListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    manager.loadGame();
                    game.setScreen(game.getGameplayScreen());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        };
    }
    
    public ClickListener getCreditsListener() {
        return new ClickListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getCreditsScreen());
                return true;
            }
        };
    }
    
    private class OperatorNameInput implements TextInputListener {
        @Override
        public void input (String text) {
            manager.setUsername(text);
            game.setScreen(game.getIntroScreen());
        }

        @Override
        public void canceled () {
            game.setScreen(game.getMenuScreen());
        }
    }
}
