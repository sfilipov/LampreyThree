/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import lamprey.seprphase3.GUI.Screens.Direction;

/**
 *
 * @author Simeon
 */
public class Mechanic extends Image {
    private Direction mechanicDirection;
    private float mechanicX;
    private float mechanicWidth;
    
    public Mechanic(Drawable drawable, Direction mechanicDirection) {
        super(drawable);
        this.mechanicDirection = mechanicDirection;
    }
    
    public Mechanic(Texture texture, Direction mechanicDirection) {
        super(texture);
        this.mechanicDirection = mechanicDirection;
    }
    
    @Override
    public void draw (SpriteBatch batch, float parentAlpha) {
        if (mechanicDirection == Direction.Right) {
            super.draw(batch, parentAlpha);
        }
        else if (mechanicDirection == Direction.Left) {
            mechanicX = this.getX();
            mechanicWidth = this.getWidth();
            this.setX(mechanicX + mechanicWidth);
            this.setScaleX(-1f);
            
            super.draw(batch, parentAlpha);
            
            this.setX(mechanicX);
            this.setScaleX(1f);
        }
    }
    
    public void setMechanicDirection(Direction mechanicDirection) {
        this.mechanicDirection = mechanicDirection;
    }
    
    public Direction getMechanicDirection() {
        return this.mechanicDirection;
    }
}
