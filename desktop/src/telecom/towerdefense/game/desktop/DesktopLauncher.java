package telecom.towerdefense.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import telecom.towerdefense.game.TowerDefense;
import telecom.towerdefense.utilities.AssetLoader;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 960;
		config.height = 640;
		//config.resizable = false;
		new LwjglApplication(new TowerDefense(), config);
		Gdx.app.debug("DesktopLauncher", "Game launched !");
	}
}
