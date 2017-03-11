package telecom.towerdefense.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	
	public final static int TXT_SIZE = 32;
	//public final static int SCREEN_WIDTH = 960;
	//public final static int SCREEN_HEIGHT = 640;
	//public final static float TXT_SCALE = 1f / TXT_SIZE;
	
	public static Texture spriteDesk, enemyDesk;;
	
	public static TextureRegion txtRoad, txtGround, txtBuilding;
	public static TextureRegion txtArcherTower;
	
	private static int soldierWidth = 64, soldierHeight = 64;
	private static int soldierKeys = 9;
    private static float animationTime = 2f;
	public static TextureRegion soldierIdle, soldierKeyTab[][];
	
	public static Animation soldierRight, soldierLeft, soldierUp, soldierDown;
	
	public static void load() {
		spriteDesk = new Texture(Gdx.files.internal("Sprite.png")); //Chargement des textures
		enemyDesk = new Texture(Gdx.files.internal("soldier.png"));
		spriteDesk.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		enemyDesk.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		txtRoad = new TextureRegion(spriteDesk, 480, 160, TXT_SIZE, TXT_SIZE);
		
		txtGround = new TextureRegion(spriteDesk, 673, 160, TXT_SIZE, TXT_SIZE);
		
		txtBuilding = new TextureRegion(spriteDesk, 577, 160, TXT_SIZE, TXT_SIZE);
		
		txtArcherTower = new TextureRegion(spriteDesk, 480, 480, TXT_SIZE, TXT_SIZE);
		
		soldierIdle = new TextureRegion(enemyDesk, 16, 206, 64, 64);
		
		TextureRegion[][] tmp = TextureRegion.split(enemyDesk, soldierWidth, soldierHeight);
		soldierKeyTab = new TextureRegion[4][soldierKeys];
		
		for(int i = 0; i < soldierKeys; i++) {
			soldierKeyTab[0][i] = tmp[3][i];
			soldierKeyTab[1][i] = tmp[1][i];
			soldierKeyTab[2][i] = tmp[0][i];
			soldierKeyTab[3][i] = tmp[2][i];
		}
		
		soldierRight = new Animation(animationTime / soldierKeys, soldierKeyTab[0]);
		soldierLeft = new Animation(animationTime / soldierKeys, soldierKeyTab[1]);
		soldierUp = new Animation(animationTime / soldierKeys, soldierKeyTab[2]);
		soldierDown = new Animation(animationTime / soldierKeys, soldierKeyTab[3]);
		
		soldierIdle = new TextureRegion(soldierKeyTab[0][0]);
		
	}
	
	public static void dispose() {
		spriteDesk.dispose();
	}
}
