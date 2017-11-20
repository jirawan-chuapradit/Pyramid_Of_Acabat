package Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import Screens.Hud;
import Sprites.InteractiveTileObject;
import Sprites.Thorn;

public class WorldContactListener implements ContactListener {

	// check conllision
	public static boolean checkPink = false;
	public static boolean checkBlue = false;
	public static boolean checkGameOver = false;
	public static boolean checkHitHealth = false;

	private Hud hud;

	@Override
	public void beginContact(Contact contact) {

		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();

		if (fixA.getUserData() == "footBlue" || fixA.getUserData() == "footPink") {

			Fixture foot = fixA.getUserData() == "foot" ? fixA : fixB;
			Fixture object = foot == fixA ? fixB : fixA;

			if (object.getUserData() != null
					&& InteractiveTileObject.class.isAssignableFrom(object.getUserData().getClass())) {
				((InteractiveTileObject) object.getUserData()).underFootHit();
			}
		}

		// check Blue on the Flage
		if (fixA.getUserData() == "footBlue" && fixB.getUserData() == "Flag") {
			setcheckBlue(true);
		} else if (fixA.getUserData() == "footBlue" && fixB.getUserData() != "Flag") {
			setcheckBlue(false);

		}

		// check Pink on the Flage
		if (fixA.getUserData() == "footPink" && fixB.getUserData() == "Flag") {
			setcheckPink(true);

		} else if (fixA.getUserData() == "footPink" && fixB.getUserData() != "Flag") {
			setcheckPink(false);

		}

		if ((fixA.getUserData() == "footPink" || fixA.getUserData() == "footBlue")
				&& fixB.getUserData() == "GameOver") {
			// add sound gameover
			setCheckGameOver(true);
		} else if ((fixA.getUserData() == "footPink" || fixA.getUserData() == "footBlue")
				&& fixB.getUserData() == "Thorn") {
			// add sound hit
			setCheckHitHealth(true);
			System.out.println(checkHitHealth);
		} else {
			setCheckHitHealth(false);
		}
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

	public static boolean ischeckPink() {
		return checkPink;
	}

	public static void setcheckPink(boolean checkPinks) {

		checkPink = checkPinks;
	}

	public static boolean ischeckBlue() {
		return checkBlue;
	}

	public static void setcheckBlue(boolean checkBlues) {
		checkBlue = checkBlues;
	}

	public static boolean isCheckGameOver() {
		return checkGameOver;
	}

	public static void setCheckGameOver(boolean checkGameOver) {
		WorldContactListener.checkGameOver = checkGameOver;
	}

	public boolean isCheckHitHealth() {
		return checkHitHealth;
	}

	public void setCheckHitHealth(boolean checkHitHealths) {
		this.checkHitHealth = checkHitHealths;
	}

}
