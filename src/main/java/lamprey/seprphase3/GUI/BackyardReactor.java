/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Simeon
 */
public class BackyardReactor implements ApplicationListener {
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
    
    @Override
    public void create() {
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
    public void resize(int i, int i1) {
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        
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
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
