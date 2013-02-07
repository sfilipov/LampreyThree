/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 *
 * @author Simeon
 */
public class GameStarter {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title   = "Backyard Reactor";
        cfg.useGL20 = true;
        cfg.width   = 960;
        cfg.height  = 540;
        
        new LwjglApplication(new BackyardReactor(), cfg);
    }
}
