package Tools;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import Sprites.BlueBlock;
import Sprites.Flag;
import Sprites.GameOver;
import Sprites.GroundBlock;
import Sprites.PinkBlock;
import Sprites.Thorn;

public class B2WorldCreator {

	public static int currentColor;
	private ArrayList<BlueBlock> blueBlocks;
	private ArrayList<PinkBlock> pinkBlocks;
	private ArrayList<GroundBlock> groundBlock;
	private ArrayList<GameOver> gameOver;
	private ArrayList<Flag> flag;
	private ArrayList<Thorn> thorn;

	public B2WorldCreator(World world, TiledMap map) {
		currentColor = 1;

		blueBlocks = new ArrayList<BlueBlock>();
		pinkBlocks = new ArrayList<PinkBlock>();
		groundBlock = new ArrayList<GroundBlock>();
		gameOver = new ArrayList<GameOver>();
		flag = new ArrayList<Flag>();
		thorn = new ArrayList<Thorn>();

		// create ground bodies/fixtures
		for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {

			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			groundBlock.add(new GroundBlock(world, map, rect));

		}

		// create BlueBock bodies / fixtures
		for (MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {

			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			blueBlocks.add(new BlueBlock(world, map, rect));

		}

		// create PinkBlock bodies / fixtures
		for (MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			pinkBlocks.add(new PinkBlock(world, map, rect));
		}

		// crate flag bodies / fixtures
		for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			flag.add(new Flag(world, map, rect));
		}

		// crate gameOver bodies / fixtures
		for (MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			gameOver.add(new GameOver(world, map, rect));
		}

		// crate thorn bodies / fixtures
		for (MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();

			thorn.add(new Thorn(world, map, rect));
		}

	}

	public void switchColor() {
		if (currentColor == 1) {
			currentColor = 0;
		} else {
			currentColor = 1;
		}

	}



	public int getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(int currentColor) {
		B2WorldCreator.currentColor = currentColor;
	}
}
