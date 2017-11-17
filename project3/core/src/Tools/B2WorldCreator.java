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
import com.mygdx.game.Pyramid;

import Sprites.BlueBlock;
import Sprites.Flag;
import Sprites.PinkBlock;

public class B2WorldCreator {
	
	private int currentColor;
	private ArrayList<BlueBlock> blueBlocks;
	private ArrayList<PinkBlock> pinkBlocks;
	private ArrayList<Flag> flag;
	
	public B2WorldCreator(World world, TiledMap map) {	
		currentColor = 1;
		
		blueBlocks = new ArrayList<BlueBlock>();
		pinkBlocks = new ArrayList<PinkBlock>();
		flag = new ArrayList<Flag>();
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		
		// create ground bodies/fixtures
		// playerPink
		for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() /2)/Pyramid.PPM, (rect.getY() + rect.getHeight()/ 2)/Pyramid.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth() /2/Pyramid.PPM, rect.getHeight() /2/Pyramid.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			
		}
		
		// create BlueBock bodies / fixtures
		for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			blueBlocks.add(new BlueBlock(world, map, rect));
			
		}
		
		// create PinkBlock bodies / fixtures
		for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();	
			//ยังไมได้เขียนให้เหยียบได้ ไปดูที่ sprite PinkBlock กับ bLueBlock สิ
			
			pinkBlocks.add(new PinkBlock(world, map, rect));
		}
		
		// crate flag bodies / fixtures
		for(MapObject object: map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			flag.add(new Flag(world, map, rect))
			;
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
	
	public void switchBlock() {
		for(BlueBlock blue: blueBlocks) {
			blue.switchState();
		}
		for(PinkBlock pink: pinkBlocks) {
			pink.switchState();			
		}
	}

	public int getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(int currentColor) {
		this.currentColor = currentColor;
	}
}