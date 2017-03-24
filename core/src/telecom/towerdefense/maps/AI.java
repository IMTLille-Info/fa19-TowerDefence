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
			Vector2 startTile = enemy.getPosition();
			Vector2 goalTile = nexus.getPosition();
			ArrayMap<Vector2, Vector2> cameFrom = new ArrayMap<Vector2, Vector2>();
			Vector2 currentTile = startTile;

			frontier.put(startTile, 0f);
			cameFrom.put(startTile, null);

			while (frontier.size > 0 && currentTile != goalTile) {
				currentTile = frontier.firstKey();
				frontier.removeKey(frontier.firstKey());

				List<Vector2> neighborsTiles = currentMap.getNeighborsTiles(currentTile);
				for (Vector2 tile : neighborsTiles) {
					if (!cameFrom.containsKey(tile)) {
						float priority = distance(goalTile, tile);
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
		}
	}

	private float distance(Vector2 a, Vector2 b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

	public void updateEnemyUnit() {
		for (MobileEntity enemy : currentMap.getListEnemyUnits()) {
			// Mise à jour de la direction en fonction de la path
			/*List<Tile> path = enemy.getPath();
			if ((path.size() > 1)) {
				if ((int) (path.get(path.size() - 2).getPosition().x / AssetLoader.TXT_SIZE)
						- (int) (enemy.getPosition().x / AssetLoader.TXT_SIZE) >= 1)
					enemy.setDirection(new Vector2(1, 0));
				else if ((int) (path.get(path.size() - 2).getPosition().y / AssetLoader.TXT_SIZE)
						- (int) (enemy.getPosition().y / AssetLoader.TXT_SIZE) >= 1)
					enemy.setDirection(new Vector2(0, 1));
				else if ((int) (path.get(path.size() - 2).getPosition().y / AssetLoader.TXT_SIZE)
						- (int) ((enemy.getPosition().y) / AssetLoader.TXT_SIZE) <= -1)
					enemy.setDirection(new Vector2(0, -1));

			} else
				enemy.setDirection(new Vector2(0, 0));

			// Mise à jour de la position en fonction de la direction*/
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
