/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import lamprey.seprphase3.GUI.MenuButtonListener;

/**
 *
 * @author Simeon
 */
public class MenuButton extends Image {
    private MenuButtonListener listener;
    
    public MenuButton(Texture texture) {
        super(texture);
        listener = new MenuButtonListener();
        this.addListener(listener);
    }
    
    @Override
    public void draw (SpriteBatch batch, float parentAlpha) {
        if(listener.isOver()) {
            this.setColor(1f, 1f, 1f, 1f);
        }
        else {
            this.setColor(0.8f, 0.8f, 0.8f, 0.8f);
        }
        super.draw(batch, parentAlpha);
    }
}
