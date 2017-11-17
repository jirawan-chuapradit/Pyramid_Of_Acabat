package com.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PyramidOfAcabat;

public class B2WorldCreator {
	public B2WorldCreator(World world, TiledMap map) {
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		
		// create ground bodies/fixtures
		// playerPink
		for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() /2)/PyramidOfAcabat.PPM, (rect.getY() + rect.getHeight()/ 2)/PyramidOfAcabat.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth() /2/PyramidOfAcabat.PPM, rect.getHeight() /2/PyramidOfAcabat.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			
			
		}
		
		// playerBlue
		for(MapObject object:
			map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight()/ 2);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth() /2, rect.getHeight() /2);
			fdef.shape = shape;
			body.createFixture(fdef);
			
			
		}
		
		// create flag bodies/fixtures
		for(MapObject object:
			map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set(rect.getX() + rect.getWidth() /2, rect.getY() + rect.getHeight()/ 2);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth() /2, rect.getHeight() /2);
			fdef.shape = shape;
			body.createFixture(fdef);
			
			
		}
	}

}
