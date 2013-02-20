package lamprey.seprphase3.GUI;

import com.badlogic.gdx.Game;
import eel.seprphase2.Simulator.Simulator;
import lamprey.seprphase3.GUI.Screens.CreditsScreen;
import lamprey.seprphase3.GUI.Screens.GameplayScreen;
import lamprey.seprphase3.GUI.Screens.MenuScreen;
import lamprey.seprphase3.GUI.Screens.PauseScreen;

/**
 *
 * @author Simeon
 */
public class BackyardReactor extends Game {
    Simulator simulator;
    
    MenuScreen menuScreen;
    GameplayScreen gameplayScreen;
    CreditsScreen creditsScreen;
    PauseScreen pauseScreen;
    
    public BackyardReactor() {
        simulator = new Simulator();
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
    
    public CreditsScreen getCreditsScreen() {
        return creditsScreen;
    }

    //Game Methods
    
    @Override
    public void create() {
        simulator = new Simulator();
        
        menuScreen     = new MenuScreen(this, simulator, simulator, simulator);
        gameplayScreen = new GameplayScreen(this, simulator, simulator, simulator);
        creditsScreen  = new CreditsScreen(this);
        pauseScreen    = new PauseScreen(this, simulator, simulator, simulator);
        
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
