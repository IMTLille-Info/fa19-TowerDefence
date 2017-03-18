package telecom.towerdefense.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.gameobjects.tiles.Tile;
import telecom.towerdefense.utilities.AssetLoader;

public class AI {

	private Map currentMap = null;

	public AI(Map currentMap) {
		this.currentMap = currentMap;
	}

	public void updateMobileEntityPath() {

		Entity nexus = currentMap.getNexus();
		for (MobileEntity enemy : currentMap.getListEnemyUnits()) {
			List<Tile> path = new ArrayList<Tile>();
			ArrayMap<Tile, Float> frontier = new ArrayMap<Tile, Float>();
			Tile startTile = currentMap.getTileAtPosition(enemy.getPosition().x, enemy.getPosition().y);
			Tile goalTile = currentMap.getTileAtPosition(nexus.getPosition().x, nexus.getPosition().y);
			ArrayMap<Tile, Tile> cameFrom = new ArrayMap<Tile, Tile>();
			Tile currentTile = startTile;

			frontier.put(startTile, 0f);
			cameFrom.put(startTile, null);

			while (frontier.size > 0 && currentTile != goalTile) {
				currentTile = frontier.firstKey();
				frontier.removeKey(frontier.firstKey());

				List<Tile> neighborsTiles = currentMap.getNeighborsTiles(currentTile);
				for (Tile tile : neighborsTiles) {
					if (!cameFrom.containsKey(tile)) {
						float priority = heuristique(goalTile.getPosition(), tile.getPosition());
						frontier.put(tile, priority);
						cameFrom.put(tile, currentTile);
					}
				}
			}

			currentTile = goalTile;
			path.add(currentTile);
			while (currentTile != startTile) {
				currentTile = cameFrom.get(currentTile);
				path.add(currentTile);
			}
			enemy.setPath(path);
			
			
			if ((path.size()>1)){
				if((int)(path.get(path.size()-2).getPosition().x/AssetLoader.TXT_SIZE)-(int)(enemy.getPosition().x/AssetLoader.TXT_SIZE) >= 1)
			enemy.setDirection(new Vector2(1,0));
			else if((int)(path.get(path.size()-2).getPosition().y/AssetLoader.TXT_SIZE)-(int)(enemy.getPosition().y/AssetLoader.TXT_SIZE) >= 1)
				enemy.setDirection(new Vector2(0,1));
			else if((int)(path.get(path.size()-2).getPosition().y/AssetLoader.TXT_SIZE)-(int)((enemy.getPosition().y)/AssetLoader.TXT_SIZE) <= -1)
				enemy.setDirection(new Vector2(0,-1));
			
				
		}
			else enemy.setDirection(new Vector2(0,0));	
		}
	}

	private float heuristique(Vector2 a, Vector2 b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
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
