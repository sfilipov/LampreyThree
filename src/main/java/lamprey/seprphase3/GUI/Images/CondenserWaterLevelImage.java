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
public class CondenserWaterLevelImage extends Image {
    private final static Texture bottom = new Texture(Gdx.files.internal("assets\\game\\water\\reactorwaterbottom.png"));
    private final static Texture middle = new Texture(Gdx.files.internal("assets\\game\\water\\reactorwatermiddle.png"));
    
    private PlantStatus status;
    private Image bottomImage;
    private Image middleImage;
    
    private float condenserWaterLevel;
    private float waterSize;
    
    public CondenserWaterLevelImage(PlantStatus status) {
        super();
        this.status = status;
        bottomImage = new Image(bottom);
        middleImage = new Image(middle);
        
        bottomImage.setPosition(562, 131);
        middleImage.setPosition(565, 150);
        
        bottomImage.setSize(190, 27);
        middleImage.setSize(182, 160);
        
        bottomImage.setColor(1f, 1f, 1f, 0.6f);
        middleImage.setColor(1f, 1f, 1f, 0.6f);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        condenserWaterLevel = (float) status.condenserWaterLevel().points(); //Don't worry about the cast - Percentage 
                                                                         //can be only between 0 and 100.
        waterSize = condenserWaterLevel * 1.6f;
        
        middleImage.setSize(206, waterSize);
//        topImage.setPosition(??, BASE_Y + waterSize);
        
        bottomImage.draw(batch, parentAlpha);
        middleImage.draw(batch, parentAlpha);
    }
}
