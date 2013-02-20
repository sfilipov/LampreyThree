/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import eel.seprphase2.Simulator.PlantStatus;

/**
 * PiggyBank provides functionality to write text on top of an Image using BitmapFonts  
 * 
 * @author Simeon
 */
public class PiggyBank extends Image {
    private PlantStatus status;
    private BitmapFont font;
    private float fontX;
    private float fontY;
    private float textDrawX;
    private float textDrawY;
    
    public PiggyBank(Texture texture, PlantStatus status) {
        super(texture);
        this.status = status;
        font = new BitmapFont();
        fontX = 45f;
        fontY = 50f;
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        textDrawX = this.getX() + fontX;
        textDrawY = this.getY() + fontY;
        
        Double energyGenerated = status.energyGenerated().inKJoules();
        font.draw(batch, "£" + energyGenerated.toString(), textDrawX, textDrawY);
    }
    
    public void setFontPosition(float fontX, float fontY) {
        this.fontX = fontX;
        this.fontY = fontY;
    }
}
