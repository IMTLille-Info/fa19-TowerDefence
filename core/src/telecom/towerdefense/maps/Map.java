package telecom.towerdefense.maps;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import telecom.towerdefense.gameobjects.building.Building;
import telecom.towerdefense.gameobjects.cases.BuildingCase;
import telecom.towerdefense.gameobjects.cases.Case;
import telecom.towerdefense.gameobjects.cases.GroundCase;
import telecom.towerdefense.gameobjects.cases.RoadCase;
import telecom.towerdefense.gameobjects.units.Unit;

public class Map {

	protected final int CASE_WIDTH = 30;
	protected final int CASE_HEIGHT = 20;

	private Case[][] mapArray;
	private List<Unit> listPlayerUnits;
	private List<Unit> listEnemyUnits;
	private List<Building> listPlayerBuilding;

	public Map(String mapDatas) {
		this.listPlayerUnits = new ArrayList<Unit>();
		this.listEnemyUnits = new ArrayList<Unit>();
		this.listPlayerBuilding = new ArrayList<Building>();
		this.mapArray = new Case[CASE_WIDTH][CASE_HEIGHT];
		this.loadLevel(mapDatas);
	}
	
	private void loadLevel(String mapDatas) {
		mapDatas = mapDatas.replace(" ", ""); //On supprimer les espaces
		mapDatas = mapDatas.replace("\n", "");
		mapDatas = mapDatas.replace("\r", "");
		byte[] datas = mapDatas.getBytes();
		
		int i = datas.length - 1;
		for(int y = 0; y < CASE_HEIGHT; y++) {
			for(int x = (CASE_WIDTH - 1); x >= 0 ; x--) {
				switch(datas[i]) {
				case 0x47: //G
					mapArray[x][y] = new GroundCase();
					break;
				case 0x52: //R
					mapArray[x][y] = new RoadCase();
					break;
				case 0x42: //B
					mapArray[x][y] = new BuildingCase();
					break;
				case 0x4E: //N
					mapArray[x][y] = new BuildingCase();
					break;
				default:
					mapArray[x][y] = new GroundCase();
					break;
				}
				mapArray[x][y].setPosition(new Vector2(x * 32, y * 32));
				i--;
			}
		}				
	}
	
	public void update() {
		
	}

	public Case[][] getMapArray() {
		return mapArray;
	}

	public void setMapArray(Case[][] mapArray) {
		this.mapArray = mapArray;
	}

	public List<Unit> getListPlayerUnits() {
		return listPlayerUnits;
	}

	public void setListPlayerUnits(List<Unit> listPlayerUnits) {
		this.listPlayerUnits = listPlayerUnits;
	}

	public List<Unit> getListEnemyUnits() {
		return listEnemyUnits;
	}

	public void setListEnemyUnits(List<Unit> listEnemyUnits) {
		this.listEnemyUnits = listEnemyUnits;
	}

	public List<Building> getListPlayerBuilding() {
		return listPlayerBuilding;
	}

	public void setListPlayerBuilding(List<Building> listPlayerBuilding) {
		this.listPlayerBuilding = listPlayerBuilding;
	}
	
	public void addBuilding(Building building) {
		this.listPlayerBuilding.add(building);
	}

	public int getCASE_WIDTH() {
		return CASE_WIDTH;
	}

	public int getCASE_HEIGHT() {
		return CASE_HEIGHT;
	}
	
	
}
