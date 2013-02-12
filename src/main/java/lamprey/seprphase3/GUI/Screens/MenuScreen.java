package lamprey.seprphase3.GUI.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import lamprey.seprphase3.GUI.BackyardReactor;

/**
 *
 * @author Simeon
 */
public class MenuScreen extends AbstractScreen {
    Texture menubgImage;
    Texture gamelogoImage;
    Texture newgameImage;
    Texture loadgameImage;
    Texture optionsImage;
    Texture highscoresImage;
    Texture lampreylogoImage;
    
    OrthographicCamera camera;
    SpriteBatch batch;
    Rectangle menu;
    
    public MenuScreen (BackyardReactor game) {
        super(game);
    }
    
    @Override
    public void show() {
        menubgImage      = new Texture(Gdx.files.internal("assets\\menubg.png"));
        gamelogoImage    = new Texture(Gdx.files.internal("assets\\gamelogo.png"));
        newgameImage     = new Texture(Gdx.files.internal("assets\\newgame.png"));
        loadgameImage    = new Texture(Gdx.files.internal("assets\\loadgame.png"));
        optionsImage     = new Texture(Gdx.files.internal("assets\\options.png"));
        highscoresImage  = new Texture(Gdx.files.internal("assets\\highscores.png"));
        lampreylogoImage = new Texture(Gdx.files.internal("assets\\lampreylogo.png"));
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 540);
        batch  = new SpriteBatch();
        
        
    }

    @Override
    public void resize( int width, int height ) {
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        
        camera.update();
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(menubgImage,      0,   0);
        batch.draw(gamelogoImage,    272, 333);
        batch.draw(newgameImage,     361, 283);
        batch.draw(loadgameImage,    349, 241);
        batch.draw(optionsImage,     392, 199);
        batch.draw(highscoresImage,  349, 155);
        batch.draw(lampreylogoImage, 436, 0);
        batch.end();
    }

    @Override
    public void dispose() {
    }
}
