package telecom.towerdefense.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import telecom.towerdefense.screens.LevelScreen;
import telecom.towerdefense.utilities.AssetLoader;

public class TowerDefense extends Game {
	public final static float SCALE = 32f;
	public final static float INVERSE_SCALE = 1.f / SCALE;
	public final static float WORLD_WIDTH = 960 * INVERSE_SCALE;
	public final static float WORLD_HEIGHT = 640 * INVERSE_SCALE;
	private OrthographicCamera camera;
	private StretchViewport viewport;
	private int nbLevel = 1;

	@Override
	public void create() {
		Gdx.app.debug("TowerDefense", "Game created !");
		AssetLoader.load();

		camera = new OrthographicCamera();
		viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
		String levelName = "level" + String.valueOf(nbLevel) + ".map";
		Screen CurrentScreen;
		try {
			CurrentScreen = new LevelScreen(this, Gdx.files.internal(levelName).readString(), camera);
			nbLevel++;
			this.setScreen(CurrentScreen);
		}catch (GdxRuntimeException ge) {
			//Pas de map
		}
		
		
	}

	@Override
	public void dispose() {
		AssetLoader.dispose();
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);

	}

	public int getNbLevel() {
		return nbLevel;
	}

	public void setNbLevel(int nbLevel) {
		this.nbLevel = nbLevel;
	}
	
}
