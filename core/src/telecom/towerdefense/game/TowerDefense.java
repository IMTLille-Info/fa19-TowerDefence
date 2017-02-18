package telecom.towerdefense.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import telecom.towerdefense.screens.Level1Screen;
import telecom.towerdefense.utilities.AssetLoader;

public class TowerDefense extends Game {

	@Override
	public void create() {
		Gdx.app.debug("TowerDefense", "Game created !");
		AssetLoader.load();
		Screen level1 = new Level1Screen();
		this.setScreen(level1);
	}

	@Override
	public void dispose() {
		AssetLoader.dispose();
		super.dispose();
	}
}
