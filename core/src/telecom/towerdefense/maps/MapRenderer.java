package telecom.towerdefense.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.gameobjects.tiles.Tile;
import telecom.towerdefense.utilities.AssetLoader;

public class MapRenderer {
	private SpriteBatch batch;
	private Map currentMap;
	private float time;

	public MapRenderer(Map currentMap) {
		this.currentMap = currentMap;
		batch = new SpriteBatch();
		time = 0.0f;
	}

	public void render() {
		Gdx.app.debug("mapRenderer", "RENDER !");
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		Tile[][] map = currentMap.getMapArray();
		for (int x = 0; x < currentMap.getTile_WIDTH(); x++)
			for (int y = 0; y < currentMap.getTile_HEIGHT(); y++) {
				batch.draw(map[x][y].getTexture(), map[x][y].getPosition().x, map[x][y].getPosition().y,
						AssetLoader.TXT_SIZE, AssetLoader.TXT_SIZE);
			}

		// Nexus
		Entity nexus = currentMap.getNexus();
		batch.draw(nexus.getTexture(), nexus.getPosition().x, nexus.getPosition().y, AssetLoader.TXT_SIZE * 2,
				(nexus.getTexture().getRegionHeight() - AssetLoader.TXT_SIZE) * 2);

		for (Entity building : currentMap.getListPlayerBuilding()) {
			batch.draw(building.getTexture(), building.getPosition().x, building.getPosition().y, AssetLoader.TXT_SIZE,
					building.getTexture().getRegionHeight() - AssetLoader.TXT_SIZE);
		}

		time += Gdx.graphics.getDeltaTime();
		for (MobileEntity enemyUnit : currentMap.getListEnemyUnits()) {
			TextureRegion texture;
			if (enemyUnit.getDirection().isZero()) {
				texture = enemyUnit.getTexture();
			} else {
				if (enemyUnit.getCurrentAnimation() != null)
					texture = (TextureRegion) enemyUnit.getCurrentAnimation().getKeyFrame(time, true);
				else
					texture = enemyUnit.getTexture();
			}
			batch.draw(texture, enemyUnit.getPosition().x, enemyUnit.getPosition().y, AssetLoader.TXT_SIZE,
					enemyUnit.getTexture().getRegionHeight() - AssetLoader.TXT_SIZE);
		}

		batch.end();
	}

	public void resize(int width, int height) {
	}
}
