/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lamprey.seprphase3.GUI;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 *
 * @author Simeon
 */
public class GameStarter {
    public static void main(String[] args) {
        new LwjglApplication(new BackyardReactor(),
                             "Backyard Reactor",
                             640, 480, false);
    }
}
