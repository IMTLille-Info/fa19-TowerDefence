package telecom.towerdefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.GdxRuntimeException;

import telecom.towerdefense.game.TowerDefense;
import telecom.towerdefense.maps.Map;
import telecom.towerdefense.maps.MapRenderer;

public class LevelScreen implements Screen {
	private Map level;
	private MapRenderer mapRenderer;
	private TowerDefense game;
	private OrthographicCamera camera;
	

	public LevelScreen(TowerDefense game, String mapFile, OrthographicCamera camera) {
		this.game = game;
		this.camera = camera;
		this.level = new Map(camera);
		this.level.loadLevel(mapFile);
		this.mapRenderer = new MapRenderer(level);
		Gdx.input.setInputProcessor(level);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.app.debug("Level1Screen", "RENDER !");
		if(level.isWinLevel()) {
			try {
				Thread.sleep(2000);
				int currentLevel = game.getNbLevel();
				String nextLevelDatas = Gdx.files.internal("level" + String.valueOf(currentLevel) + ".map").readString();
				LevelScreen nextLevel = new LevelScreen(game, nextLevelDatas, camera);
				game.setScreen(nextLevel);
			} catch (InterruptedException e) {
				
			} catch (GdxRuntimeException ge) {
				//Plus de map a charger
			}
		}
		level.update();
		mapRenderer.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
