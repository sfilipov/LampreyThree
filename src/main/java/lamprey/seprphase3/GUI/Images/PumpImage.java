/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PlantStatus;

/**
 *
 * @author Simeon
 */
public class PumpImage extends Image {
    private final static int COLS = 5;
    private final static int ROWS = 4;
    
    private PlantStatus status;
    private int pumpNumber;
    private Texture texture;
    private TextureRegion[] frames;
    private Animation animation;
    
    private TextureRegion frame;
    private TextureRegionDrawable drawable;
    private float stateTime;
    private boolean isPumping;
    
    public PumpImage(PlantStatus status, int pumpNumber) {
        super();
        this.status = status;
        this.pumpNumber = pumpNumber;
        texture = new Texture(Gdx.files.internal("assets\\game\\spritesheets\\pumpanim.png"));
        TextureRegion[][] split = TextureRegion.split(texture, texture.getWidth() / COLS, texture.getHeight() / ROWS);
        frames = new TextureRegion[COLS * ROWS];
        int index = 0;
        for (int i=0; i < ROWS; i++) {
            for (int j=0; j < COLS; j++) {
                frames[index] = split[i][j];
                index++;
            }
        }
        animation = new Animation(0.033f, frames);
        stateTime = 0f;
        drawable  = new TextureRegionDrawable();
        this.setSize(45, 45);
    }
    
    @Override
    public void draw (SpriteBatch batch, float parentAlpha) {
        try {
            isPumping = status.getPumpState(pumpNumber);
        }
        catch(KeyNotFoundException e) {
            //If a pump doesn't exist, then assume it is pumping and show animation
            isPumping = true;
        }
        
        if (isPumping) {
            stateTime += Gdx.graphics.getDeltaTime();
            frame = animation.getKeyFrame(stateTime, true);
            drawable.setRegion(frame);
            this.setDrawable(drawable);
        }
        super.draw(batch, parentAlpha);
    }
}
