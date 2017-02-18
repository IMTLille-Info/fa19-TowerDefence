package telecom.towerdefense.maps;

import com.badlogic.gdx.math.Vector2;

import telecom.towerdefense.gameobjects.cases.BuildingCase;
import telecom.towerdefense.gameobjects.cases.GroundCase;
import telecom.towerdefense.gameobjects.cases.RoadCase;

public class Level1Map extends Map {

	public Level1Map() {
		super();
	}

	@Override
	protected void loadLevel() {
		for(int x = 0; x < CASE_WIDTH; x++)
			for(int y = 0; y < CASE_HEIGHT; y++) {
				if((x >= 0 && x < 8) && (y >= 4 && y < 6)) {
					mapArray[x][y] = new RoadCase();
				} else if ((y >= 4 && y < 9) && (x >= 8 && x < 10)) {
					mapArray[x][y] = new RoadCase();
				} else if ((y >= 7 && y < 9) && (x >= 10 && x < 30)) {
					mapArray[x][y] = new RoadCase();
				} else if (y == 4 && x == 10) {
					mapArray[x][y] = new BuildingCase();
				} else {
					mapArray[x][y] = new GroundCase();
				}
				
				mapArray[x][y].setPosition(new Vector2(x * 32, y * 32));
			}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
