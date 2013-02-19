/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import eel.seprphase2.Simulator.KeyNotFoundException;
import eel.seprphase2.Simulator.PlantStatus;

/**
 *
 * @author Simeon
 */
public class InformationPanels extends Image {
    private final static Texture texture = new Texture(Gdx.files.internal("assets\\game\\infopanels.png"));
    PlantStatus status;
    private BitmapFont font;

    
    public InformationPanels(PlantStatus status) {
        super(texture);
        this.status = status;
        font = new BitmapFont();
        font.setScale(0.8f);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        
        font.draw(batch, status.reactorPressure().toString(), 287, 326);
        font.draw(batch, status.reactorTemperature().toString(), 287, 297);
        font.draw(batch, status.reactorWear().toString(), 293, 266);
        font.draw(batch, status.reactorWaterLevel().toString(), 287, 245);
        
        font.draw(batch, status.condenserPressure().toString(), 500, 337);
        font.draw(batch, status.condenserWear().toString(), 505, 285);
        
        try {
            font.draw(batch, status.getPumpWear(2).toString(), 775, 300);
            font.draw(batch, status.getPumpWear(1).toString(), 400, 230);
        }
        catch(KeyNotFoundException e) {
        }
    }
}
