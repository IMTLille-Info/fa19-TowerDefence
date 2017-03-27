package telecom.towerdefense.maps;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
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
	protected Tile[][] mapArray;
	private List<MobileEntity> listPlayerUnits;
	private List<MobileEntity> listEnemyUnits;
	private List<Entity> listPlayerBuilding;
	private AI aI;
	private Entity nexus;
	private Vector2 startPosition;
	private int nbWaves = 2;
	private boolean winLevel = false;
	private boolean loseLevel = false;

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
				case 'S':
					mapArray[x][y] = new RoadTile();
					startPosition = new Vector2(x * AssetLoader.TXT_SIZE, y * AssetLoader.TXT_SIZE);
					break;
				default:
					mapArray[x][y] = new GroundTile();
					break;
				}
				mapArray[x][y].setPosition(new Vector2(x * 32, y * 32));
				i--;
			}
		}
	}

	public void update() {
		try {
			this.makeWave();
			this.aI.updateBuilding();
		} catch (Exception e) {
			if(nbWaves == 0) winLevel = true; // Fin du niveau
		}

		try {
			this.aI.updateEnemyUnit();
		} catch (Exception e) {
			loseLevel = true;
		}
	}

	public void makeWave() throws Exception {
		if (this.listEnemyUnits.isEmpty()) {
			List<MobileEntity> enemyToPop = new ArrayList<MobileEntity>();
			for (int i = 0; i < 4; i++) {
				Soldier s = new Soldier();
				s.setPosition(new Vector2(startPosition));
				enemyToPop.add(s);
			}
			this.aI.updateMobileEntityPath(enemyToPop);
			if (nbWaves > 0) {
				Wave wave = new Wave(this, aI, enemyToPop);
				Thread popWave = new Thread(wave);
				popWave.start();
				this.nbWaves--;
			} else {
				// Niveau terminé !
				throw new Exception("Niveau terminé !");
			}
		}
	}

	public Entity getNexus() {
		return nexus;
	}

	public boolean isWinLevel() {
		return winLevel;
	}

	public boolean isLoseLevel() {
		return loseLevel;
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

	public Vector2 getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Vector2 startPosition) {
		this.startPosition = startPosition;
	}

	Vector3 tp = new Vector3();
	boolean dragging;

	@Override
	public boolean keyDown(int keycode) {
		/*
		 * Tile current = mapArray[7][11]; List<Tile> neighbors =
		 * getNeighborsTiles(current); if (neighbors.size() == 0)
		 * System.out.println("Pas de tile voisins"); for (Tile tile :
		 * neighbors) { System.out.println("Case " + neighbors.indexOf(tile) +
		 * " : x = " + tile.getPosition().x / 32 + ", y = " +
		 * tile.getPosition().y / 32); }
		 */
		// this.aI.updateMobileEntityPath();
		// System.out.println("Calcul path OK !");
		return true;
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
		screenY = Gdx.graphics.getHeight() - screenY;
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
		// camera.unproject(tp.set(x, y, 0));
		return this.mapArray[(int) x / AssetLoader.TXT_SIZE][(int) y / AssetLoader.TXT_SIZE].getClass();
	}

	public Tile getTileAtPosition(float x, float y) {
		// camera.unproject(tp.set(x, y, 0));
		return this.mapArray[(int) x / AssetLoader.TXT_SIZE][(int) y / AssetLoader.TXT_SIZE];
	}

	public List<Vector2> getNeighborsTiles(Vector2 positionTile, int precision) {
		// camera.unproject(tp.set(current.getPosition().x,
		// current.getPosition().y, 0));

		float xPos = positionTile.x;
		float yPos = positionTile.y;
		Vector2 currentPos = new Vector2(xPos, yPos);
		List<Vector2> neighorsTiles = new ArrayList<Vector2>();

		// ensemble des index des coins de la map
		List<Vector2> coinKeys = new ArrayList<Vector2>(4);
		coinKeys.add(0, new Vector2(0, 0)); // Bas gauche
		coinKeys.add(1, new Vector2(0, Gdx.graphics.getHeight() - precision)); // Haut
																				// gauche
		coinKeys.add(2, new Vector2(Gdx.graphics.getWidth() - precision, 0)); // Bas
																				// droite
		coinKeys.add(3, new Vector2(Gdx.graphics.getHeight() - precision, Gdx.graphics.getWidth() - precision)); // Haut
		// droite

		// ensemble des index des côtés
		List<Vector2> sideKeys = new ArrayList<Vector2>(4);
		sideKeys.add(0, new Vector2(xPos, 0)); // Coté bas
		sideKeys.add(1, new Vector2(xPos, Gdx.graphics.getHeight() - precision)); // Coté
																					// haut
		sideKeys.add(2, new Vector2(Gdx.graphics.getWidth() - precision, yPos)); // coté
																					// droit
		sideKeys.add(3, new Vector2(0, yPos)); // coté gauche

		if (coinKeys.contains(currentPos)) {
			if (coinKeys.get(0).x == xPos && coinKeys.get(0).y == yPos) {
				if (getTileAtPosition(xPos + precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos + precision, yPos));
				if (getTileAtPosition(xPos, yPos + precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos + precision));
			} else if (coinKeys.get(1).x == xPos && coinKeys.get(1).y == yPos) {
				if (getTileAtPosition(xPos + precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos + precision, yPos));
				if (getTileAtPosition(xPos, yPos - precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos - precision));
			} else if (coinKeys.get(2).x == xPos && coinKeys.get(2).y == yPos) {
				if (getTileAtPosition(xPos - precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos - precision, yPos));
				if (getTileAtPosition(xPos, yPos + precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos + precision));
			} else if (coinKeys.get(3).x == xPos && coinKeys.get(3).y == yPos) {
				if (getTileAtPosition(xPos - precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos - precision, yPos));
				if (getTileAtPosition(xPos, yPos - precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos - precision));
			}
		} else if (sideKeys.contains(currentPos)) {
			if (sideKeys.get(0).x == xPos && sideKeys.get(0).y == yPos) {
				if (getTileAtPosition(xPos + precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos + precision, yPos));
				if (getTileAtPosition(xPos, yPos + precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos + precision));
				if (getTileAtPosition(xPos - precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos - precision, yPos));
			} else if (sideKeys.get(1).x == xPos && sideKeys.get(1).y == yPos) {
				if (getTileAtPosition(xPos + precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos + precision, yPos));
				if (getTileAtPosition(xPos, yPos - precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos - precision));
				if (getTileAtPosition(xPos - precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos - precision, yPos));
			} else if (sideKeys.get(2).x == xPos && sideKeys.get(2).y == yPos) {
				if (getTileAtPosition(xPos - precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos - precision, yPos));
				if (getTileAtPosition(xPos, yPos + precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos + precision));
				if (getTileAtPosition(xPos, yPos - precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos - precision));
			} else if (sideKeys.get(3).x == xPos && sideKeys.get(3).y == yPos) {
				if (getTileAtPosition(xPos + precision, yPos).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos + precision, yPos));
				if (getTileAtPosition(xPos, yPos + precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos + precision));
				if (getTileAtPosition(xPos, yPos - precision).getClass() == RoadTile.class)
					neighorsTiles.add(new Vector2(xPos, yPos - precision));
			}
		} else {
			if (getTileAtPosition(xPos + precision, yPos).getClass() == RoadTile.class)
				neighorsTiles.add(new Vector2(xPos + precision, yPos));
			if (getTileAtPosition(xPos, yPos + precision).getClass() == RoadTile.class)
				neighorsTiles.add(new Vector2(xPos, yPos + precision));
			if (getTileAtPosition(xPos, yPos - precision).getClass() == RoadTile.class)
				neighorsTiles.add(new Vector2(xPos, yPos - precision));
			if (getTileAtPosition(xPos - precision, yPos).getClass() == RoadTile.class)
				neighorsTiles.add(new Vector2(xPos - precision, yPos));
		}

		return neighorsTiles;

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
