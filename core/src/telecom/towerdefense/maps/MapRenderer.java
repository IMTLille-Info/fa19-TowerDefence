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
	private OrthographicCamera camera;
	
	private float time;
	
	public MapRenderer(Map currentMap) {
		this.currentMap = currentMap;
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
		camera.update();
		time = 0.0f;
	}
	
	public void render() {
		Gdx.app.debug("mapRenderer", "RENDER !");
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		
		batch.begin();
		Tile[][] map = currentMap.getMapArray();
		for(int x = 0; x < currentMap.getTile_WIDTH(); x++)
			for(int y = 0; y < currentMap.getTile_HEIGHT(); y++) {
				batch.draw(map[x][y].getTexture(), map[x][y].getPosition().x, map[x][y].getPosition().y, AssetLoader.TXT_SIZE, AssetLoader.TXT_SIZE);
			}
		
		for(Entity building : currentMap.getListPlayerBuilding()) {
			batch.draw(building.getTexture(), building.getPosition().x, building.getPosition().y, AssetLoader.TXT_SIZE, AssetLoader.TXT_SIZE);
		}
		
		
		
		for(MobileEntity enemyUnit : currentMap.getListEnemyUnits()) {
			TextureRegion texture;
			//batch.draw(AssetLoader.soldierRight.getKeyFrame(time, true), enemyUnit.getPosition().x, enemyUnit.getPosition().y, enemyUnit.getTexture().getRegionWidth(), enemyUnit.getTexture().getRegionHeight());
			if(!enemyUnit.getDirection().isZero()) {
				texture = enemyUnit.getTexture();
			} else {
				time += Gdx.graphics.getDeltaTime();
				texture = (TextureRegion) enemyUnit.getCurrentAnimation().getKeyFrame(time, true);
			}
			batch.draw(texture, enemyUnit.getPosition().x, enemyUnit.getPosition().y, enemyUnit.getTexture().getRegionWidth(), enemyUnit.getTexture().getRegionHeight());
		}
		
		batch.end();
	}
	
	public void resize(int width, int height) {
		camera.setToOrtho(false, width, height);
	}
}
