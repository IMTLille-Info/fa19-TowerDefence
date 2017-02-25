package telecom.towerdefense.userinputs;

import telecom.towerdefense.gameobjects.GameObject;
import telecom.towerdefense.utilities.AssetLoader;

public class BuildMenu extends GameObject {
	private boolean isEnabled;
	
	public BuildMenu() {
		isEnabled = false;
		this.texture = AssetLoader.txtBuildingMenuBackGround;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
