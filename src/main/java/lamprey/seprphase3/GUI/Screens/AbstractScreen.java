package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.PlantController;
import eel.seprphase2.Simulator.PlantStatus;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
abstract class AbstractScreen implements Screen {
    protected final BackyardReactor game;
    protected PlantController controller;
    protected PlantStatus status;
    protected GameManager manager;
    protected final Stage stage;
    
    public AbstractScreen(BackyardReactor game, PlantController controller, PlantStatus status, GameManager manager) {
        this.game  = game;
        this.controller = controller;
        this.status = status;
        this.manager = manager;
        
        int width  = 960;
        int height = 540;
        boolean keepAspectRatio = true;
        this.stage = new Stage(width, height, keepAspectRatio);
    }
    
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        //Resize the stage
        stage.setViewport(width, height, true);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        stage.act(delta);
        stage.draw();
    }
    
    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
    
    public PlantStatus getPlantStatus() {
        return this.status;
    }
    
    public PlantController getPlantController() {
        return this.controller;
    }
    
    public GameManager getGameManager() {
        return this.manager;
    }
}
