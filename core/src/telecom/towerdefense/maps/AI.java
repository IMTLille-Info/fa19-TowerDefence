package telecom.towerdefense.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;

import telecom.towerdefense.gameobjects.Entity;
import telecom.towerdefense.gameobjects.MobileEntity;
import telecom.towerdefense.utilities.AssetLoader;

public class AI {

	private Map currentMap = null;

	public AI(Map currentMap) {
		this.currentMap = currentMap;
	}

	public void popWave(List<MobileEntity> listEnemyUnits, Vector2 startPosition) throws Exception {

	}

	public void updateMobileEntityPath(List<MobileEntity> listEnemies) {

		Entity nexus = currentMap.getNexus();
		for (MobileEntity enemy : listEnemies) {
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

	public void updateEnemyUnit() throws Exception {
		for (MobileEntity enemy : currentMap.getListEnemyUnits()) {
			// Mise à jour de la direction en fonction de la path
			List<Vector2> path = enemy.getPath();
			if ((path.size() > 1) && !enemy.isInRange(currentMap.getNexus())) {
				if ((int) (path.get(path.size() - 2).y / AssetLoader.TXT_SIZE)
						- (int) (enemy.getPosition().y / AssetLoader.TXT_SIZE) == 0) {
					if ((int) (path.get(path.size() - 2).x / AssetLoader.TXT_SIZE)
							- (int) (enemy.getPosition().x / AssetLoader.TXT_SIZE) == 1
							&& path.get(path.size() - 2).y + 1 > enemy.getPosition().y) {
						enemy.setDirection(new Vector2(1, 0)); // droite
						path.remove(path.size() - 1);
					}
					if ((int) (path.get(path.size() - 2).x / AssetLoader.TXT_SIZE)
							- (int) (enemy.getPosition().x / AssetLoader.TXT_SIZE) == -1) {
						enemy.setDirection(new Vector2(-1, 0)); // gauche
						path.remove(path.size() - 1);
					}
				} else if ((int) (path.get(path.size() - 2).x / AssetLoader.TXT_SIZE)
						- (int) (enemy.getPosition().x / AssetLoader.TXT_SIZE) == 0) {
					if ((int) (path.get(path.size() - 2).y / AssetLoader.TXT_SIZE)
							- (int) (enemy.getPosition().y / AssetLoader.TXT_SIZE) == 1) {
						enemy.setDirection(new Vector2(0, 1)); // haut
						path.remove(path.size() - 1);
					}
					if ((int) (path.get(path.size() - 2).y / AssetLoader.TXT_SIZE)
							- (int) (enemy.getPosition().y / AssetLoader.TXT_SIZE) == -1) {
						enemy.setDirection(new Vector2(0, -1)); // bas
						path.remove(path.size() - 1);
					}
				}
			} else
				enemy.setDirection(new Vector2(0, 0));

			// Mise à jour de la position en fonction de la direction
			enemy.move();

			// Les enemis attaquent le nexus
			if (enemy.isInRange(currentMap.getNexus())) {
				enemy.attack(currentMap.getNexus());
			}
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
