package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Ez az űrhajó figurája
 */
public class ActorSpaceship extends Actor {

	private boolean landingRocketState = false, leftRocketState = false, rightRocketState = false;

	private final float LANDING_ROCKET_POWER = 40, SIDE_ROCKET_POWER = 10;

	private long mMainRocketOverheatedTime = 0;
	private long mMainRocketUsingTime = 0;

	final Body body;
	final World world;

	ActorSpaceship(World world) {
		this.world = world;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.x = 100;
		bodyDef.position.y = 400;

		//bodyDef.linearDamping = .1f;
		//bodyDef.angularDamping = .5f;

		this.body = this.world.createBody(bodyDef);

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(50, 80);

		Fixture fix = body.createFixture(polygonShape, 50);
        fix.setDensity(1);
		//fix.setDensity(1);
		//fix.setFriction(1f);
		//fix.setRestitution(0.8f);

		polygonShape.dispose();

		//Több képet tartalmazó PNG feldarabolása és betöltése
		textureFire=new TextureAtlas(Gdx.files.internal("fireanimation.atlas"));
		textureSpeceShip=new TextureRegion(new Texture(Gdx.files.internal("rocket.png")));
		final TextureAtlas.AtlasRegion region = textureFire.findRegion("0");
		/*
		spriteLeftFire = new Sprite(region);
		spriteRightFire = new Sprite(region);
		spriteLandingFire = new Sprite(region);
		*/
		spriteLeftFire = new Sprite(new Texture("ball.png"));
		spriteRightFire = new Sprite(new Texture("ball.png"));
		spriteLandingFire = new Sprite(new Texture("ball.png"));
		spriteSpaceShip=new Sprite(textureSpeceShip);

		//spriteFromAtlas.setPosition(0, 256);


	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		spriteSpaceShip.setSize(width,height);
		setFire();
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		spriteSpaceShip.setPosition(x, y);
		setFire();
	}

	private void setFire()
	{
		spriteLeftFire.setSize(getWidth() / 6,getHeight()/4);
		spriteRightFire.setSize(getWidth() / 6, getHeight() / 4);
		spriteLandingFire.setSize(getWidth() / 3,getHeight()/2);
		spriteLeftFire.setPosition(getX(), getY() - spriteLeftFire.getHeight());
		spriteRightFire.setPosition(getX()+getWidth()-spriteLeftFire.getWidth(),getY()-spriteLeftFire.getHeight());
		spriteLandingFire.setPosition(getX()+getWidth()/2-spriteLandingFire.getWidth()/2,getY()-spriteLandingFire.getHeight());
	}

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

    TextureRegion textureSpeceShip;
	TextureAtlas textureFire;
	Sprite spriteSpaceShip;
	Sprite spriteLandingFire;
	Sprite spriteLeftFire;
	Sprite spriteRightFire;

	@Override
	public void draw(Batch batch, float parentAlpha) {
		//batch.draw(textureSpeceShip, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
		spriteSpaceShip.draw(batch);

		spriteLandingFire.draw(batch);
		spriteLeftFire.draw(batch);
		spriteRightFire.draw(batch);

		if (landingRocketState)
		{

		}
		if (leftRocketState)
		{

		}
		if (rightRocketState)
		{

		}
		// kirajzoljuk a rakétát
		// TODO landoló egység rajzolása

		// kirajzoljuk a lángokat
		// TODO lángok rajzolás

	}

	@Override
	public void act(float delta) {

		//setOrigin(0, 0);
		//setRotation((float) Math.toDegrees(body.getAngle()));

		float elapsedTime = Gdx.graphics.getDeltaTime();

		if (landingRocketState && mMainRocketOverheatedTime == 0) { // be van kapcsolva a rakét, és nincs túlmelegedve
			mMainRocketUsingTime += elapsedTime;
			body.applyForce(0, (LANDING_ROCKET_POWER * elapsedTime) * 1e7f, 0, 0, true);

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
            body.applyForceToCenter(1e7f, 10f, false);
		} else if (rightRocketState) {
            body.applyForceToCenter(-1e7f, 10f, false);
		}

		final Vector2 pos = body.getPosition();
		setPosition(pos.x, pos.y);

	}
}
