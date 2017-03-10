package telecom.towerdefense.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.gameobjects.tiles.Tile;
import telecom.towerdefense.utilities.AssetLoader;

public class MapRenderer {
	private SpriteBatch batch;
	private Map currentMap;
	private BitmapFont bmpFont;

	public MapRenderer(Map map) {
		batch = new SpriteBatch();
		currentMap = map;
		bmpFont = new BitmapFont();
		bmpFont.setColor(Color.WHITE);
		BitmapFontData datasFont = bmpFont.getData();
		datasFont.setScale(1.5f);
	}

	public void render() {
		Gdx.app.debug("mapRenderer", "RENDER !");
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.enableBlending();
		batch.begin();
		Tile[][] map = currentMap.getMapArray();
		for (int x = 0; x < currentMap.getTile_WIDTH(); x++)
			for (int y = 0; y < currentMap.getTile_HEIGHT(); y++) {
				batch.draw(map[x][y].getTexture(), map[x][y].getPosition().x, map[x][y].getPosition().y,
						AssetLoader.TXT_SIZE, AssetLoader.TXT_SIZE);
			}

		for (Entity building : currentMap.getListPlayerBuilding()) {
			batch.draw(building.getTexture(), building.getPosition().x, building.getPosition().y, AssetLoader.TXT_SIZE,
					AssetLoader.TXT_SIZE);
		}

		for (MobileEntity enemyUnit : currentMap.getListEnemyUnits()) {
			batch.draw(enemyUnit.getTexture(), enemyUnit.getPosition().x, enemyUnit.getPosition().y,
					enemyUnit.getTexture().getRegionWidth(), enemyUnit.getTexture().getRegionHeight());
		}

		batch.end();
	}
}
