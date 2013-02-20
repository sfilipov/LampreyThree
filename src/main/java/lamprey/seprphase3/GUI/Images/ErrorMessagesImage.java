/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import eel.seprphase2.Simulator.PlantStatus;

/**
 *
 * @author Simeon
 */
public class ErrorMessagesImage extends Image {
    private final static Texture errorTexture = new Texture(Gdx.files.internal("assets\\game\\errormsgs.png"));
    private final static int COLS = 3;
    private final static int ROWS = 2;

    private PlantStatus status;
    private TextureRegion normal;
    private TextureRegion turbine;
    private TextureRegion pump2;
    private TextureRegion pump1;
    private TextureRegion condenser;
    private TextureRegion software;
    private TextureRegionDrawable drawable;
    private float delta;
    
    public ErrorMessagesImage(PlantStatus status) {
        super();
        this.status = status;
        drawable = new TextureRegionDrawable();
        
        TextureRegion[][] split = TextureRegion.split(errorTexture, errorTexture.getWidth() / COLS, errorTexture.getHeight() / ROWS);
        normal    = split[0][0];
        turbine   = split[0][1];
        pump2     = split[0][2];
        pump1     = split[1][0];
        condenser = split[1][1];
        software  = split[1][2];
        delta = 0f;
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
//        if (status.)
    }
}
