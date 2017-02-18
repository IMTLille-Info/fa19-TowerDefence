package telecom.towerdefense.maps;

import java.util.ArrayList;
import java.util.List;

import telecom.towerdefense.gameobjects.building.Building;
import telecom.towerdefense.gameobjects.cases.Case;
import telecom.towerdefense.gameobjects.units.Unit;

public abstract class Map {

	protected final int CASE_WIDTH = 30;
	protected final int CASE_HEIGHT = 20;

	protected Case[][] mapArray;
	private List<Unit> listPlayerUnits;
	private List<Unit> listEnemyUnits;
	private List<Building> listPlayerBuilding;

	public Map() {
		this.listPlayerUnits = new ArrayList<Unit>();
		this.listEnemyUnits = new ArrayList<Unit>();
		this.listPlayerBuilding = new ArrayList<Building>();
		this.mapArray = new Case[CASE_WIDTH][CASE_HEIGHT];
		this.loadLevel();
	}
	
	protected abstract void loadLevel();
	public abstract void update();

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
