package lamprey.seprphase3.reactor;

import com.badlogic.gdx.backends.android.AndroidApplication;

/**
 *
 * @author Will
 */
public class GameStarter extends AndroidApplication {
	public void onCreate (android.os.Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new BackyardReactor(), true);
	}
}
