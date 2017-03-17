package telecom.towerdefense.maps;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.gameobjects.building.ArcherTower;
import telecom.towerdefense.gameobjects.building.Nexus;
import telecom.towerdefense.gameobjects.building.Stonehenge;
import telecom.towerdefense.gameobjects.tiles.BuildingTile;
import telecom.towerdefense.gameobjects.tiles.GroundTile;
import telecom.towerdefense.gameobjects.tiles.RoadTile;
import telecom.towerdefense.gameobjects.tiles.Tile;
import telecom.towerdefense.gameobjects.units.Soldier;
import telecom.towerdefense.utilities.AssetLoader;

public class Map implements InputProcessor {

	protected final int Tile_WIDTH = 30;
	protected final int Tile_HEIGHT = 20;
	private OrthographicCamera camera;

	protected Tile[][] mapArray;
	private List<MobileEntity> listPlayerUnits;
	private List<MobileEntity> listEnemyUnits;
	private List<Entity> listPlayerBuilding;
	private AI aI;
	private Entity nexus;

	public Map() {
		this.listPlayerUnits = new ArrayList<MobileEntity>();
		this.listEnemyUnits = new ArrayList<MobileEntity>();
		this.listPlayerBuilding = new ArrayList<Entity>();
		this.mapArray = new Tile[Tile_WIDTH][Tile_HEIGHT];
		this.aI = new AI(this);

	}

	public Map(OrthographicCamera camera) {
		this.listPlayerUnits = new ArrayList<MobileEntity>();
		this.listEnemyUnits = new ArrayList<MobileEntity>();
		this.listPlayerBuilding = new ArrayList<Entity>();
		this.mapArray = new Tile[Tile_WIDTH][Tile_HEIGHT];
		this.aI = new AI(this);
		this.camera = camera;
	}

	public void loadLevel(String mapDatas) {
		mapDatas = mapDatas.replace(" ", ""); // On supprimer les espaces
		mapDatas = mapDatas.replace("\n", "");
		mapDatas = mapDatas.replace("\r", "");
		byte[] datas = mapDatas.getBytes();

		int i = datas.length - 1;
		for (int y = 0; y < Tile_HEIGHT; y++) {
			for (int x = (Tile_WIDTH - 1); x >= 0; x--) {
				switch (datas[i]) {
				case 0x47: // G
					mapArray[x][y] = new GroundTile();
					break;
				case 0x52: // R
					mapArray[x][y] = new RoadTile();
					break;
				case 0x42: // B
					mapArray[x][y] = new BuildingTile();
					break;
				case 0x4E: // N
					mapArray[x][y] = new RoadTile();
					Entity nexus = new Nexus(); // Création du nexus
					nexus.setPosition(new Vector2(x * AssetLoader.TXT_SIZE, y * AssetLoader.TXT_SIZE));
					this.nexus = nexus;
					break;
				default:
					mapArray[x][y] = new GroundTile();
					break;
				}
				mapArray[x][y].setPosition(new Vector2(x * 32, y * 32));
				i--;
			}
		}

		Soldier soldier = new Soldier();
		Soldier soldier2 = new Soldier();
		soldier.setPosition(new Vector2(mapArray[0][10].getPosition()));
		soldier.setDirection(new Vector2(1, 0));
		this.listEnemyUnits.add(soldier);
		soldier2.setPosition(new Vector2(mapArray[0][11].getPosition()));
		soldier2.setDirection(new Vector2(1, 0));
		this.listEnemyUnits.add(soldier2);
	}

	public void update() {
		this.aI.updateBuilding();
		this.aI.updateEnemyUnit();
	}

	public Entity getNexus() {
		return nexus;
	}

	public Tile[][] getMapArray() {
		return mapArray;
	}

	public void setMapArray(Tile[][] mapArray) {
		this.mapArray = mapArray;
	}

	public List<MobileEntity> getListEnemyUnits() {
		return listEnemyUnits;
	}

	public void setListEnemyUnits(List<MobileEntity> listEnemyUnits) {
		this.listEnemyUnits = listEnemyUnits;
	}

	public List<Entity> getListPlayerBuilding() {
		return listPlayerBuilding;
	}

	public void setListPlayerBuilding(List<Entity> listPlayerBuilding) {
		this.listPlayerBuilding = listPlayerBuilding;
	}

	public int getTile_WIDTH() {
		return Tile_WIDTH;
	}

	public int getTile_HEIGHT() {
		return Tile_HEIGHT;
	}

	Vector3 tp = new Vector3();
	boolean dragging;

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (getClassForPosition(screenX, screenY) == BuildingTile.class) { // Ajout
																						// d'un
																						// batiment
			Entity stoneHenge = new Stonehenge();
			stoneHenge.setPosition(getTileAtPosition(screenX, screenY).getPosition());
			this.addBuilding(stoneHenge);
		}

		return true;
	}
	
	public Class<?> getClassForPosition(float x, float y) {
		camera.unproject(tp.set(x, y, 0));
		return this.mapArray[(int) tp.x][(int) tp.y].getClass();		
	}
	
	public Tile getTileAtPosition(float x, float y) {
		camera.unproject(tp.set(x, y, 0));
		return this.mapArray[(int) tp.x][(int) tp.y];
	}
	
	public List<Tile> getNeighborsTiles(float x, float y) {
		camera.unproject(tp.set(x, y, 0)); //Pour gestion du resize de la fenetre
		List<Tile> neighborsTiles = new ArrayList<Tile>();
		//Récupération des index de la Map
		int xKey = (int) tp.x;
		int yKey = (int) tp.y;
		
		//Récupération de la Tile au centre
		//Tile centerTile = mapArray[xKey][yKey];
		
		if(yKey == 0) { 
			if(xKey == 0) { //En bas à gauche
				
			} else if (xKey == Tile_WIDTH - 1) { //En bas à droite
				
			}	
		} else if (xKey == 0) {
			
		}
			
		return neighborsTiles;
	}

	private void addBuilding(Entity building) {
		this.listPlayerBuilding.add(building);
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
