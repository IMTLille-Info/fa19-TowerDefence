package telecom.towerdefense.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

import telecom.towerdefense.gameobjects.building.ArcherTower;
import telecom.towerdefense.gameobjects.building.Building;
import telecom.towerdefense.gameobjects.cases.BuildingCase;
import telecom.towerdefense.utilities.AssetLoader;

public class Hud implements InputProcessor {
	
	private int mana = 50;
	private AI aI = null;;
	
	public Hud(AI ai) {
		this.aI = ai;
	}
	
	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	@Override
	public boolean keyDown(int keycode) {
		//TODO A SUPPRIMER, POUR TESTS UNIQUEMENT
		this.aI.getMap().getListPlayerBuilding().clear();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int xCase = screenX / AssetLoader.TXT_SIZE;
		int yCase = (AssetLoader.SCREEN_HEIGHT - screenY) / AssetLoader.TXT_SIZE;
		
		if(this.aI.getMap().getMapArray()[xCase][yCase].getClass() == BuildingCase.class) { //Ajout d'un batiment
			Building archerTower = new ArcherTower();
			archerTower.setPosition(this.aI.getMap().getMapArray()[xCase][yCase].getPosition());
			this.aI.getMap().addBuilding(archerTower);
		}
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public void update() {
		this.aI.updateLogic();
		this.aI.getMap().update();
	}

}
