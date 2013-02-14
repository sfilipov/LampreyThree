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
        menuScreen     = new MenuScreen(this);
        gameplayScreen = new GameplayScreen(this);
        pauseScreen    = new PauseScreen(this);
        
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
