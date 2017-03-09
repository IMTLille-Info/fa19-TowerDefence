package telecom.towerdefense.userinputs;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import telecom.towerdefense.gameobjects.cases.BuildingCase;
import telecom.towerdefense.gameobjects.cases.Case;
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
		//this.aI.getCurrentMap().getListPlayerBuilding().clear();
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
	
	/**
	 * 
	 * @param screenX
	 * @param screenY
	 * @return la classe de l'objet sur lequel on a cliqué
	 */
	private Class<? extends Case> getTypeCaseClicked(int screenX, int screenY) {
		int xCase = screenX / AssetLoader.TXT_SIZE;
		int yCase = (AssetLoader.SCREEN_HEIGHT - screenY) / AssetLoader.TXT_SIZE;
		
		return this.aI.getCurrentMap().getMapArray()[xCase][yCase].getClass();
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int xCase = screenX / AssetLoader.TXT_SIZE;
		int yCase = (AssetLoader.SCREEN_HEIGHT - screenY) / AssetLoader.TXT_SIZE;
		
		if(this.buildMenu.isEnabled()) {
			this.buildMenu.setEnabled(false);
		}
		
		//if(this.buildMenu.isEnabled() && )
		//TODO Gestion du click dans le building menu (Input multiplexer)
		
		if(this.buildMenu.isEnabled() == false && getTypeCaseClicked(screenX, screenY) == BuildingCase.class) { //Ouverture du building Menu
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
