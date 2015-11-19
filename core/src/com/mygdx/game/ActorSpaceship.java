package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

/**
 * Ez az űrhajó figurája
 */
public class ActorSpaceship extends MyActor {

	private boolean landingRocketState = false, leftRocketState = false, rightRocketState = false;

	private final float LANDING_ROCKET_POWER = 40, SIDE_ROCKET_POWER = 10;

	private long mMainRocketOverheatedTime = 0;
	private long mMainRocketUsingTime = 0;


	public enum RocketType {
		landing,
		left,
		right
	}

	public void setRocketState(RocketType type, boolean state) {
		switch (type) {
			case landing:
				landingRocketState = state;
				break;
			case left:
				leftRocketState = state;
				if (leftRocketState) rightRocketState = false;
				break;
			case right:
				rightRocketState = state;
				if (rightRocketState) rightRocketState = false;
				break;
		}
	}

	// http://pimentoso.blogspot.hu/2013/01/meter-and-pixel-units-in-box2d-game.html

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		batch.begin();

		// kirajzoljuk a rakétát
		// TODO landoló egység rajzolása

		// kirajzoljuk a lángokat
		// TODO lángok rajzolás

		batch.end();
	}

	@Override
	public void act(float delta) {

		//setOrigin(0, 0);
		//setRotation((float) Math.toDegrees(body.getAngle()));

		float elapsedTime = Gdx.graphics.getDeltaTime();

		if (landingRocketState && mMainRocketOverheatedTime == 0) { // be van kapcsolva a rakét, és nincs túlmelegedve
			mMainRocketUsingTime += elapsedTime;

			body.applyForce(0, (LANDING_ROCKET_POWER * elapsedTime) / -1000f, 0, 0, true);

			if (mMainRocketUsingTime > 2000) { // többet használtuk, mint 2 másodperc
				mMainRocketOverheatedTime = 3000;
			}
		} else if (mMainRocketOverheatedTime > 0) { // túl van melegedve
			mMainRocketOverheatedTime -= elapsedTime;
			if (mMainRocketOverheatedTime < 0) {
				mMainRocketOverheatedTime = 0;
			}
		}

		if (leftRocketState) {
			body.applyForce((LANDING_ROCKET_POWER * elapsedTime) / 1000f, 0, 0, 0, true);
		} else if (rightRocketState) {
			body.applyForce(-(LANDING_ROCKET_POWER * elapsedTime) / 1000f, 0, 0, 0, true);
		}


		final Vector2 pos = body.getPosition();
		setPosition(pos.x, pos.y);

	}
}
