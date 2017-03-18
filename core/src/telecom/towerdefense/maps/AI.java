package telecom.towerdefense.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.gameobjects.tiles.Tile;

public class AI {

	private Map currentMap = null;

	public AI(Map currentMap) {
		this.currentMap = currentMap;
	}

	public void updateMobileEntityPath() {	

		Entity nexus = currentMap.getNexus();
		for (MobileEntity enemy : currentMap.getListEnemyUnits()) {
			List<Tile> path = new ArrayList<Tile>();
			Stack<Tile> frontier= new Stack<Tile>();
			Tile startTile = currentMap.getTileAtPosition(enemy.getPosition().x, enemy.getPosition().y);
			ArrayMap<Tile, Tile> cameFrom = new ArrayMap<Tile, Tile>();
			
			frontier.push(startTile);
			cameFrom.put(startTile, null);
			while(!frontier.isEmpty()) {
				Tile currentTile = frontier.pop();
				List<Tile> neighborsTiles = currentMap.getNeighborsTiles(currentTile);
				for (Tile tile : neighborsTiles) {
					if(!cameFrom.containsKey(tile)) {
						frontier.push(tile);
						cameFrom.put(tile, currentTile);
					}
				}
			}
			
			Tile currentTile = currentMap.getTileAtPosition(nexus.getPosition().x, nexus.getPosition().y);
			path.add(currentTile);
			while(currentTile != startTile) {
				currentTile = cameFrom.get(currentTile);
				path.add(currentTile);
			}
			enemy.setPath(path);
		}
	}
	


	public void updateEnemyUnit() {
		for (MobileEntity enemy : currentMap.getListEnemyUnits()) {
			// Mise à jour de la position en fonction de la direction
			enemy.move();
		}
	}

	public void updateUnit() {

	}

	public void updateBuilding() {
		for (Entity entity : currentMap.getListPlayerBuilding()) {
			// Gestion des ennemis à proximité
			makeBuildingAttack(entity);
		}
	}

	private void makeBuildingAttack(Entity building) {
		Iterator<MobileEntity> mobItr = currentMap.getListEnemyUnits().iterator();
		while (mobItr.hasNext()) {
			Entity enemy = mobItr.next();

			if (building.isInRange(enemy)) {
				try {
					building.attack(enemy);
				} catch (Exception e) {
					mobItr.remove();
				}
			}
		}
	}

	public Map getMap() {
		return currentMap;
	}

	public void setMap(Map currentMap) {
		this.currentMap = currentMap;
	}

}
