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
public class ReactorWaterLevelImage extends Image {
    private final static Texture bottom = new Texture(Gdx.files.internal("assets\\game\\water\\reactorwaterbottom.png"));
    private final static Texture middle = new Texture(Gdx.files.internal("assets\\game\\water\\reactorwatermiddle.png"));
    private final static float BASE_Y = 147;
    
    private PlantStatus status;
    private Image bottomImage;
    private Image middleImage;
    
    private float reactorWaterLevel;
    private float waterSize;
    
    
    public ReactorWaterLevelImage(PlantStatus status) {
        super();
        this.status = status;
        bottomImage = new Image(bottom);
        middleImage = new Image(middle);
        
        bottomImage.setPosition(41, 127);
        middleImage.setPosition(50, BASE_Y);
        
        bottomImage.setSize(224, 29);
        middleImage.setSize(206, 160);
        
        bottomImage.setColor(1f, 1f, 1f, 0.6f);
        middleImage.setColor(1f, 1f, 1f, 0.6f);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        reactorWaterLevel = (float) status.reactorWaterLevel().points(); //Don't worry about the cast - Percentage 
                                                                         //can be only between 0 and 100.
        waterSize = 80f;//reactorWaterLevel * 1.6f;
        
        middleImage.setSize(206, waterSize);
//        topImage.setPosition(??, BASE_Y + waterSize); 
        bottomImage.draw(batch, parentAlpha);
        middleImage.draw(batch, parentAlpha);
    }
    
}
