package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Ez az űrhajó figurája
 */
public class ActorSpaceship extends MyActor {

	private boolean mMainRocketState = false, mLeftRocketState = false, mRightRocketState = false;

	private final float SIDE_ROCKET_POWER = 10;
	private final float MAIN_ROCKET_POWER = 40;

	private long mMainRocketOverheatedTime = 0;
	private long mMainRocketUsingTime = 0;


	public enum RocketType {
		main,
		left,
		right
	}

	;

	public void setRocketState(RocketType type, boolean state) {
		switch (type) {
			case main:
				mMainRocketState = state;
				break;
			case left:
				mLeftRocketState = state;
				if (mLeftRocketState) mRightRocketState = false;
				break;
			case right:
				mRightRocketState = state;
				if (mRightRocketState) mRightRocketState = false;
				break;
		}
	}

	private void useRockets() {


		if (mMainRocketState && mMainRocketOverheatedTime == 0) { // be van kapcsolva a rakét, és nincs túlmelegedve
			mMainRocketUsingTime += elapsedTime;

			setY(getY() + (MAIN_ROCKET_POWER * elapsedTime) / 1000f);

			if(mMainRocketUsingTime > 2000) { // többet használtuk, mint 2 másodperc
				mMainRocketOverheatedTime = 3000;
			}
		} else if(mMainRocketOverheatedTime > 0) { // túl van melegedve
			mMainRocketOverheatedTime -= elapsedTime;
			if(mMainRocketOverheatedTime < 0) {
				mMainRocketOverheatedTime = 0;
			}
		}

		if (mLeftRocketState) {
			setX(getX() + (SIDE_ROCKET_POWER * elapsedTime) / 1000f);
		} else if (mRightRocketState) {
			setX(getX() - (SIDE_ROCKET_POWER * elapsedTime) / 1000f);
		}

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		batch.begin();


		setY(getY() + (MAIN_ROCKET_POWER * elapsedTime) / 1000f);


		useRockets();

		// kirajzoljuk a rakétát


		// kirajzoljuk a lángokat




		batch.end();
	}
}
