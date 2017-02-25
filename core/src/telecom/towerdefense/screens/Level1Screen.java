package telecom.towerdefense.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import telecom.towerdefense.maps.AI;
import telecom.towerdefense.maps.Map;
import telecom.towerdefense.maps.MapRenderer;
import userinputs.Hud;

public class Level1Screen implements Screen {
	private Hud hud;
	private Map level1;
	private MapRenderer mapRenderer;
	private AI aI;
	
	public Level1Screen() {
		String mapDatas = Gdx.files.internal("level1.map").readString();
		this.level1 = new Map(mapDatas);
		this.aI = new AI(level1);
		this.hud = new Hud(aI);
		this.mapRenderer = new MapRenderer(hud);
		Gdx.input.setInputProcessor(hud);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.app.debug("Level1Screen", "RENDER !");
		hud.update();
		mapRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
