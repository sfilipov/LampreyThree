package lamprey.seprphase3.GUI;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import lamprey.seprphase3.GUI.Screens.GameplayScreen;
import lamprey.seprphase3.GUI.Screens.MenuScreen;
import lamprey.seprphase3.GUI.Screens.PauseScreen;

/**
 *
 * @author Simeon
 */
public class BackyardReactor extends Game {
    Stage stage;
    
    MenuScreen menuScreen;
    GameplayScreen gameplayScreen;
    PauseScreen pauseScreen;
    
    public BackyardReactor() {
    }
    
    public MenuScreen getMenuScreen() {
        return menuScreen;
    }
    
    public GameplayScreen getGameplayScreen() {
        return gameplayScreen;
    }
    
    public PauseScreen getPauseScreen() {
        return pauseScreen;
    }

    //Game Methods
    
    @Override
    public void create() {
        int width  = 960;
        int height = 540;
        boolean keepAspectRatio = true;
        this.stage = new Stage(width, height, keepAspectRatio);
        Gdx.input.setInputProcessor(stage);
        
        menuScreen     = new MenuScreen(this, this.stage);
        gameplayScreen = new GameplayScreen(this, this.stage);
        pauseScreen    = new PauseScreen(gameplayScreen);
        
        setScreen(getMenuScreen());
    }
    
    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }
    
    @Override
    public void render() {
        super.render();
    }
    
        @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
