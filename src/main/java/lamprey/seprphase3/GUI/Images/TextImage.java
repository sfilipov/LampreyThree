/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * TextImage provides functionality to write text on top of an Image using a BitmapFont  
 * 
 * @author Simeon
 */
public class TextImage extends Image {
    private BitmapFont font;
    private float fontX;
    private float fontY;
    private float textDrawX;
    private float textDrawY;
    private String text;
    
    public TextImage(Texture texture) {
        super(texture);
        font = new BitmapFont();
        fontX = 45f;
        fontY = 50f;
        text = "";
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        textDrawX = this.getX() + fontX;
        textDrawY = this.getY() + fontY;
        font.draw(batch, text, textDrawX, textDrawY);
    }
    
    public void setFontPosition(float fontX, float fontY) {
        this.fontX = fontX;
        this.fontY = fontY;
    }
    
    public void setStringToDisplay(String text) {
        this.text = text;
    }
}
