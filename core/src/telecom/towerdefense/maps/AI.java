package telecom.towerdefense.maps;

public class AI {
	
	private Map currentMap = null;
	
	public AI(Map currentMap) {
		this.currentMap = currentMap;
	}
	
	private void updateEnemyUnit() {
		
	}
	
	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
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
}
