/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Simeon
 */
public class MenuButtonListener extends ClickListener {
    public MenuButtonListener() {
        super();
    }
    
    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        //Gdx.input.getTextInput(nameListener, "Please enter your name", "Player");
        return true;
    }
}
