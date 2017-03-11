package telecom.towerdefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import telecom.towerdefense.maps.AI;
import telecom.towerdefense.maps.Map;
import telecom.towerdefense.maps.MapRenderer;

public class Level1Screen implements Screen {
	private Map level1;
	private MapRenderer mapRenderer;

	public Level1Screen() {
		String mapDatas = Gdx.files.internal("level1.map").readString();
		this.level1 = new Map();
		this.level1.loadLevel(mapDatas);
		this.mapRenderer = new MapRenderer(level1);
		Gdx.input.setInputProcessor(level1);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.app.debug("Level1Screen", "RENDER !");
		level1.update();
		mapRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		mapRenderer.resize(width, height);
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
