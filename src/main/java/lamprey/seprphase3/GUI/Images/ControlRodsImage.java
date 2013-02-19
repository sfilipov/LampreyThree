/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import eel.seprphase2.Simulator.PlantStatus;

/**
 *
 * @author Simeon
 */
public class ControlRodsImage extends Image {
    private final static Texture texture = new Texture(Gdx.files.internal("assets\\game\\controlrods.png"));
    private final static float BASE_Y = 127f;
    private PlantStatus status;
    
    private float controlRodPosition;
    
    public ControlRodsImage(PlantStatus status) {
        super(texture);
        this.status = status;
        this.setSize(226f, 154f);
        this.setX(40);
        
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        controlRodPosition = (float) status.controlRodPosition().points(); //Don't worry about the cast - Percentage 
                                                                           //can be only between 0 and 100.
        this.setY(BASE_Y + (controlRodPosition * 3f / 5f));
        super.draw(batch, parentAlpha);
    }
}
