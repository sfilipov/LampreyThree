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
    Texture menuImage;
    Texture logoImage;
    OrthographicCamera camera;
    SpriteBatch batch;
    Rectangle menu;
    
    @Override
    public void create() {
        menuImage = new Texture(Gdx.files.internal("assets\\menu.png"));
        logoImage = new Texture(Gdx.files.internal("assets\\logo.png"));
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 960, 540);
        batch  = new SpriteBatch();
        
        menu = new Rectangle();
        menu.x = 0;
        menu.y = 0;
        menu.width  = 960;
        menu.height = 540;
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
        batch.draw(menuImage, menu.x, menu.y);
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
