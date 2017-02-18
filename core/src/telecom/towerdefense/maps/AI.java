package telecom.towerdefense.maps;

public class AI {
	
	private Map currentMap = null;
	
	public AI(Map currentMap) {
		this.currentMap = currentMap;
	}
	
	private void updateEnemyUnit() {
		
	}
	
	private void updateUnit() {
		
	}
	
	private void updateBuilding() {
		
	}
	
	public void updateLogic() {
		this.updateBuilding();
		this.updateEnemyUnit();
		this.updateUnit();
	}

	public Map getMap() {
		return currentMap;
	}

	public void setMap(Map currentMap) {
		this.currentMap = currentMap;
	}
	
	
	
}
