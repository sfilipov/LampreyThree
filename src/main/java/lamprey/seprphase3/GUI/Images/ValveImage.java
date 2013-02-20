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
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PlantStatus;

/**
 *
 * @author Simeon
 */
public class ValveImage extends Image {
    private PlantStatus status;
    private int valveNumber;
    private Texture texture;
    private TextureRegion valveOpen;
    private TextureRegion valveClosed;
    private TextureRegionDrawable drawable;
    private boolean isOpen;
    
    public ValveImage(PlantStatus status, int valveNumber) {
        super();
        this.status = status;
        this.valveNumber = valveNumber;
        texture = new Texture(Gdx.files.internal("assets\\game\\valvesprite.png"));
        TextureRegion[][] split = TextureRegion.split(texture, texture.getWidth() / 2, texture.getHeight() );
        valveOpen   = split[0][0];
        valveClosed = split[0][1];
        drawable = new TextureRegionDrawable();
        this.setSize(50, 60);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        try {
            isOpen = status.getValveState(valveNumber);
        }
        catch(KeyNotFoundException e) {
            //If valve doesn't exist, then assume it is open at the place it is shown
            isOpen = true;
        }
        
        if (isOpen) {
            drawable.setRegion(valveOpen);
        }
        else {
            drawable.setRegion(valveClosed);
        }
        
        this.setDrawable(drawable);
        super.draw(batch, parentAlpha);
    }
    
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }
}
