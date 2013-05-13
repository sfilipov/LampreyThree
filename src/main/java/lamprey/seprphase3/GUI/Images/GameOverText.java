/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI.Images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import eel.seprphase2.Simulator.GameManager;
import eel.seprphase2.Simulator.PlantStatus;


/**
 *
 * @author Simeon
 */
public class GameOverText extends Image {
    private PlantStatus status;
    private BitmapFont font;
    private FreeTypeFontGenerator generator;
    
    public GameOverText(PlantStatus status) {
        super();
        this.status = status;
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts\\arialbd.ttf"));
        font = generator.generateFont(36);
        font.setColor(0f, 0f, 0f, 0.8f);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
        Double energyGenerated = status.energyGenerated().inKJoules();
        font.draw(batch, status.getUsername() + ", your final score is: £" + energyGenerated.intValue(), this.getX(), this.getY());
        super.draw(batch, parentAlpha);
    }
}
