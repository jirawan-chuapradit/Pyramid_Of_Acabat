package Tools;

import java.util.ArrayList;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import Sprites.BlueBlock;
import Sprites.Flag;
import Sprites.GroundBlock;
import Sprites.PinkBlock;

public class B2WorldCreator {
	
	private int currentColor;
	private ArrayList<BlueBlock> blueBlocks;
	private ArrayList<PinkBlock> pinkBlocks;
	private ArrayList<GroundBlock> groundBlock;
	private ArrayList<Flag> flag;
	
	public B2WorldCreator(World world, TiledMap map) {	
		currentColor = 1;
		
		blueBlocks = new ArrayList<BlueBlock>();
		pinkBlocks = new ArrayList<PinkBlock>();
		groundBlock = new ArrayList<GroundBlock>();
		flag = new ArrayList<Flag>();
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;	
		
		// create ground bodies/fixtures
		// playerPink
		for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			groundBlock.add(new GroundBlock(world, map, rect));
		
			
		}
		
		// create BlueBock bodies / fixtures
		for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			blueBlocks.add(new BlueBlock(world, map, rect));
			
		}
		
		// create PinkBlock bodies / fixtures
		for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();	
			//เธขเธฑเธ�เน�เธกเน�เธ”เน�เน€เธ�เธตเธขเธ�เน�เธซเน�เน€เธซเธขเธตเธขเธ�เน�เธ”เน� เน�เธ�เธ”เธนเธ—เธตเน� sprite PinkBlock เธ�เธฑเธ� bLueBlock เธชเธด
			
			pinkBlocks.add(new PinkBlock(world, map, rect));
		}
		
		// crate flag bodies / fixtures
		for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			flag.add(new Flag(world, map, rect));
		}
		
		// disable pink block because blue player start first
//		for(PinkBlock pink: pinkBlocks) {
//			pink.switchState();			
//		}
		
	}
	
	public void switchColor() {
		if(currentColor == 1) {
			currentColor = 0;
		}
		else {
			currentColor = 1;
		}
		
//		switchBlock();
	}
	
//	public void switchBlock() {
//		for(BlueBlock blue: blueBlocks) {
//			blue.switchState();
//		}
//		for(PinkBlock pink: pinkBlocks) {
//			pink.switchState();			
//		}
//	}
	 
	

	public int getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(int currentColor) {
		this.currentColor = currentColor;
	}
}
