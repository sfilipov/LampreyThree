package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
public class MenuScreen extends AbstractScreen {
    Texture menubgTexture;
    Texture gamelogoTexture;
    Texture newgameTexture;
    Texture loadgameTexture;
    Texture optionsTexture;
    Texture highscoresTexture;
    Texture lampreylogoTexture;
    Image menubgImage;
    Image gamelogoImage;
    Image newgameImage;
    Image loadgameImage;
    Image optionsImage;
    Image highscoresImage;
    Image lampreylogoImage;
    
    public MenuScreen (BackyardReactor game, Stage stage) {
        super(game, stage);
    }
    
    @Override
    public void show() {
        super.show();
        
        menubgTexture      = new Texture(Gdx.files.internal("assets\\menubg.png"));
        gamelogoTexture    = new Texture(Gdx.files.internal("assets\\gamelogo.png"));
        newgameTexture     = new Texture(Gdx.files.internal("assets\\newgame.png"));
        loadgameTexture    = new Texture(Gdx.files.internal("assets\\loadgame.png"));
        optionsTexture     = new Texture(Gdx.files.internal("assets\\options.png"));
        highscoresTexture  = new Texture(Gdx.files.internal("assets\\highscores.png"));
        lampreylogoTexture = new Texture(Gdx.files.internal("assets\\lampreylogo.png"));
              
        stage.clear();
        
        menubgImage      = new Image(menubgTexture);
        gamelogoImage    = new Image(gamelogoTexture);
        newgameImage     = new Image(newgameTexture);
        loadgameImage    = new Image(loadgameTexture);
        optionsImage     = new Image(optionsTexture);
        highscoresImage  = new Image(highscoresTexture);
        lampreylogoImage = new Image(lampreylogoTexture);
        
        menubgImage.setPosition(0, 0);
        gamelogoImage.setPosition(272, 333);
        newgameImage.setPosition(361, 283);
        loadgameImage.setPosition(349, 241);
        optionsImage.setPosition(392, 199);
        highscoresImage.setPosition(349, 155);
        lampreylogoImage.setPosition(436, 0);
        
        newgameImage.addListener(getNewgameListener());
        
        stage.addActor(menubgImage);
        stage.addActor(gamelogoImage);
        stage.addActor(newgameImage);
        stage.addActor(loadgameImage);
        stage.addActor(optionsImage);
        stage.addActor(highscoresImage);
        stage.addActor(lampreylogoImage);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);        
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        drawHovers();
    }

    @Override
    public void dispose() {
    }
    
    private ClickListener getNewgameListener() {
        return new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(game.getGameplayScreen());
            }
        };
    }
    
    private void drawHovers() {
        Array<Actor> actors = stage.getActors();
        for (Actor actor : actors) {
            Array<EventListener> listeners = actor.getListeners();
            if (listeners.size > 0) {
                EventListener listener = listeners.first();
                if (listener instanceof ClickListener) {
                    ClickListener clickListener = (ClickListener) listener;
                    if (clickListener.isOver()) {
                        //Draw the hover image
                    }
                    else {
                        //Draw the normal image
                    }
                }
            }
        }
    }
}
