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
    private float mouseOver = 1f;
    private float notOver = 0.8f;
    private float current;
    
    public MenuButton(Texture texture) {
        super(texture);
        listener = new MenuButtonListener();
        this.addListener(listener);
        current = 0.8f;
    }
    
    @Override
    public void draw (SpriteBatch batch, float parentAlpha) {
        //Increase and decrease current based on mouse hover
        if       (listener.isOver() && current < mouseOver) {
            current += 0.025; }
        else if (!listener.isOver() && current > notOver) {
            current -= 0.025; }
        
        //Put current to min or max if it underflows or overflows
        if      (current > mouseOver) {
            current = mouseOver;
        }
        else if (current < notOver) {
            current = notOver;
        }
        
        //Set the proper color and draw the button
        this.setColor(current, current, current, current);
        super.draw(batch, parentAlpha);
    }
}
