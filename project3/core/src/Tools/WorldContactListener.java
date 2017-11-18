package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import Sprites.InteractiveTileObject;

public class WorldContactListener implements ContactListener {

	// check conllision
	public static boolean CHECKPINK = false;
	public static boolean CHECKBLUE = false;
	
	private Fixture fixA;
	private Fixture fixB;
	
	@Override
	public void beginContact(Contact contact) {
		 fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		
		if(fixA.getUserData() == "footBlue" || fixA.getUserData() == "footPink") {
			Fixture foot = fixA.getUserData() == "foot" ? fixA : fixB;
			Fixture object = foot == fixA ? fixB : fixA;
			if(object.getUserData() != null && InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
				((InteractiveTileObject) object.getUserData()).underFootHit();
			}
		}
		
		// check Blue  on the Flage
		if(fixA.getUserData() == "footBlue" && fixB.getUserData() == "Flag") {
			System.out.println("==========================================================");
			CHECKBLUE = true;
		}
		else if(fixA.getUserData() == "footBlue" && fixB.getUserData() != "Flag") {
			System.out.println("###############################");
			CHECKBLUE = false;
			
		}
		
		// check Pink  on the Flage
		if(fixA.getUserData() == "footPink" && fixB.getUserData() == "Flag") {
			System.out.println("==========================================================");
			CHECKPINK = true;
			
		}
		else if(fixA.getUserData() == "footPink" && fixB.getUserData() != "Flag") {
			System.out.println("###############################");
			CHECKPINK = false;
					
		}
		System.out.println("Pink : "+CHECKPINK + "Blue: "+ CHECKBLUE);

		
		
	}

	@Override
	public void endContact(Contact contact) {
		Gdx.app.log("End Contact", "");		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

//	public boolean isCheckPink() {
//		System.out.println(checkPink);
//		return checkPink;
//	}


//	public boolean isCheckBlue() {
//		System.out.println(checkBlue);
//		return checkBlue;
//	}




}
