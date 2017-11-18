package Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Pyramid;

public class BlueBlock extends InteractiveTileObject {
	
	private Fixture fixture;
	
	
	public BlueBlock(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		

		
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Pyramid.PPM, (bounds.getY() + bounds.getHeight() / 2) / Pyramid.PPM);
		
		
		
		body = world.createBody(bdef);
		
		shape.setAsBox(bounds.getWidth() / 2 / Pyramid.PPM, bounds.getHeight() / 2 / Pyramid.PPM);
		fdef.shape = shape;
		fdef.filter.groupIndex = -2;
		fixture = body.createFixture(fdef);
		
		fixture.setUserData("BlueBlock");
		

		
	
	}


	@Override
	public void underFootHit() {
		Gdx.app.log("BlueBlock", "Collision");		
	}
	
//	public void switchState() {
//		if(fixture.isSensor()) {
//			fixture.setSensor(false);
//		}
//		else { 
//			fixture.setSensor(true);
//		}
//	}
}
