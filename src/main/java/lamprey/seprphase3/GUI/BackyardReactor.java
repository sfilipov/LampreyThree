package lamprey.seprphase3.GUI;

import com.badlogic.gdx.Game;
import lamprey.seprphase3.GUI.Screens.MenuScreen;

/**
 *
 * @author Simeon
 */
public class BackyardReactor extends Game {
    
    public BackyardReactor() {
    }
    
    public MenuScreen getMenuScreen() {
        return new MenuScreen(this);
    }

    //Game Methods
    
    @Override
    public void create() {
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
