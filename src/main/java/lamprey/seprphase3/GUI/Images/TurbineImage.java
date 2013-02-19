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
import eel.seprphase2.Simulator.PlantStatus;

/**
 *
 * @author Simeon
 */
public class TurbineImage extends HoverButton {
    private PlantStatus status;
    private Texture texture;
    private TextureRegion[] frames;
    private Animation animationSpeed1;
    private Animation animationSpeed2;
    private Animation animationSpeed3;
    private Animation animationSpeed4;
    private Animation currentAnimation;
    
    private TextureRegion frame;
    private TextureRegionDrawable drawable;
    private float stateTime;
    private final static int COLS = 2;
    private final static int ROWS = 7;
    
    public TurbineImage(PlantStatus status) {
        super();
        texture = new Texture(Gdx.files.internal("assets\\game\\spritesheets\\turbineanim.png"));
        TextureRegion[][] split = TextureRegion.split(texture, texture.getWidth() / COLS, texture.getHeight() / ROWS);
        frames = new TextureRegion[COLS * ROWS];
        int index = 0;
        for (int i=0; i < ROWS; i++) {
            for (int j=0; j < COLS; j++) {
                frames[index] = split[i][j];
                index++;
            }
        }
        animationSpeed1 = new Animation(0.08f, frames);
        animationSpeed2 = new Animation(0.04f, frames);
        animationSpeed3 = new Animation(0.02f, frames);
        animationSpeed4 = new Animation(0.013f, frames);
        stateTime = 0f;
        drawable = new TextureRegionDrawable();
        this.setSize(300, 130);
    }
    
    @Override
    public void draw (SpriteBatch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        frame = animationSpeed2.getKeyFrame(stateTime, true);
        drawable.setRegion(frame);
        this.setDrawable(drawable);
        super.draw(batch, parentAlpha);
    }
}
