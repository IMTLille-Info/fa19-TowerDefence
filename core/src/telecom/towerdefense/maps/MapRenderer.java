package telecom.towerdefense.maps;

import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.omg.CORBA.FREE_MEM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.gameobjects.tiles.Tile;
import telecom.towerdefense.utilities.AssetLoader;

public class MapRenderer {
	private SpriteBatch batch;
	private Map currentMap;
	private float time;
	private ShapeRenderer shapeRenderer;
	private BitmapFont endFont, uiFont;

	public MapRenderer(Map currentMap) {
		this.currentMap = currentMap;
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		time = 0.0f;
		
		//Polices
		endFont = new BitmapFont();
		FreeTypeFontGenerator endFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("3Dventure.ttf"));
		FreeTypeFontParameter endFontParameter = new FreeTypeFontParameter();
		endFontParameter.size = 120;
		endFontParameter.color = Color.WHITE;
		endFont = endFontGenerator.generateFont(endFontParameter);
		endFontGenerator.dispose();
		
		uiFont = new BitmapFont();
		FreeTypeFontGenerator uiFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Savior1.ttf"));
		FreeTypeFontParameter uiFontParameters = new FreeTypeFontParameter();
		uiFontParameters.size = 32;
		uiFontParameters.color = Color.WHITE;
		uiFont = uiFontGenerator.generateFont(uiFontParameters);
		uiFontGenerator.dispose();
	}

	public void render() {
		Gdx.app.debug("mapRenderer", "RENDER !");
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.begin();
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
		if (nexus.getLifePoint() > 0)
			drawLifeBar(shapeRenderer, nexus);

		for (Entity building : currentMap.getListPlayerBuilding()) {
			batch.draw(building.getTexture(), building.getPosition().x, building.getPosition().y, AssetLoader.TXT_SIZE,
					building.getTexture().getRegionHeight() - AssetLoader.TXT_SIZE);
			if (building.getLifePoint() > 0)
				drawLifeBar(shapeRenderer, building);
		}

		time += Gdx.graphics.getDeltaTime(); // Time pour les animations
		List<MobileEntity> enemies = currentMap.getListEnemyUnits();
		for (int i = 0; i < enemies.size(); i++) {
			MobileEntity enemyUnit = enemies.get(i);
			TextureRegion texture;
			if (enemyUnit.getDirection().isZero()) {
				texture = enemyUnit.getTexture();
			} else {
				if (enemyUnit.getCurrentAnimation() != null)
					texture = (TextureRegion) enemyUnit.getCurrentAnimation().getKeyFrame(time, true);
				else
					texture = enemyUnit.getTexture();
			}
			batch.draw(texture, enemyUnit.getPosition().x, enemyUnit.getPosition().y + (AssetLoader.TXT_SIZE / 2),
					AssetLoader.TXT_SIZE, enemyUnit.getTexture().getRegionHeight() - AssetLoader.TXT_SIZE);
			if (enemyUnit.getLifePoint() > 0)
				drawLifeBar(shapeRenderer, enemyUnit);
		}
		
		if(currentMap.getMana() > 0)
			uiFont.setColor(Color.WHITE);
		else
			uiFont.setColor(Color.RED);
		uiFont.draw(batch, "Mana : " + currentMap.getMana() + " / " + currentMap.MANA_MAX, Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 20);
		
		batch.end();

		/*
		 * shapeRenderer.set(ShapeType.Filled); int i = 0; for (MobileEntity
		 * enemy : currentMap.getListEnemyUnits()) { shapeRenderer.setColor(i *
		 * 10, i * 10, i * 10, 0); List<Vector2> path = enemy.getPath(); for
		 * (Vector2 t : path) { shapeRenderer.circle(t.x+i*10, t.y, 3.0f); }
		 * i++; }
		 */
		if (currentMap.isWinLevel()) {
			shapeRenderer.setColor(Color.BLUE);
			shapeRenderer.rect(0, 250, Gdx.graphics.getWidth(), 200);
			shapeRenderer.end();
			batch.begin();
			endFont.draw(batch, "Victoire !", 170, 370);
			batch.end();
		} else if (currentMap.isLoseLevel()) {
			shapeRenderer.setColor(Color.RED);
			shapeRenderer.rect(0, 250, Gdx.graphics.getWidth(), 200);
			shapeRenderer.end();
			batch.begin();
			endFont.draw(batch, "Defaite !", 170, 370);
			batch.end();
		}

		shapeRenderer.end();

	}

	private void drawLifeBar(ShapeRenderer shape, Entity entity) {
		Vector2 entityPos = entity.getPosition();
		float xBarSize = (AssetLoader.LIFEBAR_WIDTH * entity.getxUnit() * entity.getLifePoint())
				/ entity.getMaxLifePoint();
		Vector2 lifeBarPos = new Vector2();
		// Positionnement de la barre de vie
		if ((Gdx.graphics.getHeight() - entityPos.y
				- (AssetLoader.LIFEBAR_HEIGHT * entity.getyUnit())) <= AssetLoader.LIFEBAR_HEIGHT) {
			lifeBarPos.set(entityPos.x, entityPos.y - (AssetLoader.LIFEBAR_HEIGHT * entity.getyUnit()));
		} else {
			lifeBarPos.set(entityPos.x, entityPos.y + (AssetLoader.LIFEBAR_HEIGHT * entity.getyUnit()));
		}

		shape.setColor(Color.RED);
		shape.set(ShapeType.Filled);
		shape.rect(lifeBarPos.x, lifeBarPos.y, xBarSize, AssetLoader.LIFEBAR_HEIGHT);
	}

	public void resize(int width, int height) {
	}
}
