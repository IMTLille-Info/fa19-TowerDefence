package telecom.towerdefense.userinputs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;

import telecom.towerdefense.gameobjects.building.ArcherTower;
import telecom.towerdefense.gameobjects.building.Building;
import telecom.towerdefense.gameobjects.cases.BuildingCase;
import telecom.towerdefense.maps.AI;
import telecom.towerdefense.utilities.AssetLoader;

public class Hud implements InputProcessor {
	
	private int mana = 50;
	private AI aI = null;
	private BuildMenu buildMenu = null;
	
	public Hud(AI ai) {
		this.aI = ai;
		this.buildMenu = new BuildMenu();
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
		this.aI.getCurrentMap().getListPlayerBuilding().clear();
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
		
		if(this.buildMenu.isEnabled()) {
			this.buildMenu.setEnabled(false);
		}
		
		if(this.buildMenu.isEnabled() == false && this.aI.getCurrentMap().getMapArray()[xCase][yCase].getClass() == BuildingCase.class) { //Ajout d'un batiment
			//TODO Affichage du BuildMenu
			this.buildMenu.setPosition(new Vector2(screenX, screenY));
			this.buildMenu.setEnabled(true);
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
		this.aI.getCurrentMap().update();
	}

	public BuildMenu getBuildMenu() {
		return buildMenu;
	}

	public void setBuildMenu(BuildMenu buildMenu) {
		this.buildMenu = buildMenu;
	}

	public AI getaI() {
		return aI;
	}

	public void setaI(AI aI) {
		this.aI = aI;
	}
	
	
	
}
