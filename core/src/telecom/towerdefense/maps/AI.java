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
			List<Vector2> path = new ArrayList<Vector2>();
			ArrayMap<Vector2, Float> frontier = new ArrayMap<Vector2, Float>();
			Vector2 startPos = enemy.getPosition();
			Vector2 goalPos = nexus.getPosition();
			ArrayMap<Vector2, Vector2> cameFrom = new ArrayMap<Vector2, Vector2>();
			Vector2 currentPos = startPos;

			frontier.put(startPos, 0f);
			cameFrom.put(startPos, null);

			while (frontier.size > 0 && currentPos != goalPos) {
				currentPos = frontier.firstKey();
				frontier.removeKey(frontier.firstKey());

				List<Vector2> neighborsTiles = currentMap.getNeighborsTiles(currentPos, 32);
				for (Vector2 tile : neighborsTiles) {
					if (!cameFrom.containsKey(tile)) {
						float priority = distance(goalPos, tile);
						frontier.put(tile, priority);
						cameFrom.put(tile, currentPos);
					}
				}
			}

			currentPos = goalPos;
			path.add(currentPos);
			while (currentPos != startPos) {
				currentPos = cameFrom.get(currentPos);
				path.add(currentPos);
			}
			enemy.setPath(path);
		}
	}

	private float distance(Vector2 a, Vector2 b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

	public void updateEnemyUnit() {
		for (MobileEntity enemy : currentMap.getListEnemyUnits()) {
			// Mise � jour de la direction en fonction de la path


			// Mise � jour de la position en fonction de la direction
			enemy.move();
		}
	}

	public void updateUnit() {

	}

	public void updateBuilding() {
		for (Entity entity : currentMap.getListPlayerBuilding()) {
			// Gestion des ennemis � proximit�
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
