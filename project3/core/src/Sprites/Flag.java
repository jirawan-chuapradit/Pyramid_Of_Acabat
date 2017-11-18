package Sprites;

<<<<<<< HEAD
=======
import com.badlogic.gdx.Gdx;
>>>>>>> Beer
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Pyramid;

public class Flag extends InteractiveTileObject{

	private Fixture fixture;

	public Flag(World world, TiledMap map, Rectangle bounds) {
		super(world, map, bounds);
<<<<<<< HEAD
=======
		
>>>>>>> Beer
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
<<<<<<< HEAD
=======
		
>>>>>>> Beer
		bdef.type = BodyDef.BodyType.StaticBody;
		bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Pyramid.PPM, (bounds.getY() + bounds.getHeight() / 2) / Pyramid.PPM);
		
		body = world.createBody(bdef);
		
		shape.setAsBox(bounds.getWidth() / 2 / Pyramid.PPM, bounds.getHeight() / 2 / Pyramid.PPM);
		fdef.shape = shape;
<<<<<<< HEAD
		fdef.filter.groupIndex = 3;
		
		
		fixture = body.createFixture(fdef);
=======
//		fdef.filter.groupIndex = 3;
		
		
		fixture = body.createFixture(fdef);
		fixture.setUserData("Flag");
	}
	

	@Override
	public void underFootHit() {
		Gdx.app.log("Flag", "Collision");
>>>>>>> Beer
	}
	
}
