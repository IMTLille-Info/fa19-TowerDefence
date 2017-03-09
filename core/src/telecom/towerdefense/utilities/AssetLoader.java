package telecom.towerdefense.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public final static int TXT_SIZE = 32;
	public final static int SCREEN_WIDTH = 960;
	public final static int SCREEN_HEIGHT = 640;
	
	public static Texture spriteDesk, enemyDesk;;
	
	public static TextureRegion txtRoad, txtGround, txtBuilding;
	public static TextureRegion txtArcherTower;
	public static TextureRegion enemyIdle;
	
	public static void load() {
		spriteDesk = new Texture(Gdx.files.internal("Sprite.png")); //Chargement des textures
		enemyDesk = new Texture(Gdx.files.internal("daxbotsheet.png"));
		spriteDesk.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		enemyDesk.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		txtRoad = new TextureRegion(spriteDesk, 480, 160, TXT_SIZE, TXT_SIZE);
		
		txtGround = new TextureRegion(spriteDesk, 673, 160, TXT_SIZE, TXT_SIZE);
		
		txtBuilding = new TextureRegion(spriteDesk, 577, 160, TXT_SIZE, TXT_SIZE);
		
		txtArcherTower = new TextureRegion(spriteDesk, 480, 480, TXT_SIZE, TXT_SIZE);
		
		enemyIdle = new TextureRegion(enemyDesk, 15, 4, 32, 64);
		
	}
	
	public static void dispose() {
		spriteDesk.dispose();
	}
}
