package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

/**
 * Ez az űrhajó figurája
 */
public class ActorSpaceship extends MyActor {

	private boolean landingRocketState = false, leftRocketState = false, rightRocketState = false, smoke = false, fireIsInProgress = false;;
	private int smokeFrame=0, fireFrame=0;


	TextureRegion textureSpaceShip;
	Sprite spriteSpaceShip;
	Sprite spriteLeftFire, spriteRightFire, spriteLandingFire, spriteSmoke;
	TextureAtlas textureAtlasLeftFire, textureAtlasRightFire, textureAtlasSmoke;
	Animation animationLeftFire, animationRightFire, animationSmoke;
	private final float LANDING_ROCKET_POWER = 40, SIDE_ROCKET_POWER = 10;

	private float mMainRocketOverheatedTime = 0;
	private float mMainRocketUsingTime = 0;

	final Body body;
	final World world;

	ActorSpaceship(World world) {
		this.world = world;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.x = Gdx.graphics.getWidth() / 2;
		bodyDef.position.y = 100;

		//bodyDef.linearDamping = .1f;
		//bodyDef.angularDamping = .5f;

		this.body = this.world.createBody(bodyDef);
        this.body.setFixedRotation(true);

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(50, 80);

		Fixture fix = body.createFixture(polygonShape, 50);
		fix.setDensity(10);
		fix.setFriction(2f);

		polygonShape.dispose();

		//Több képet tartalmazó PNG feldarabolása és betöltése
		//textureFire = new TextureAtlas(Gdx.files.internal("fireanimation.atlas"));
		textureSpaceShip = new TextureRegion(new Texture("rocket.png"));


//---- ANIMÁCIÓ ----------------------------------------------------------------------------
//---- ANIMÁCIÓ ----------------------------------------------------------------------------
//---- ANIMÁCIÓ ----------------------------------------------------------------------------
		//Töltsük be az ATLAS fájlt!
		textureAtlasLeftFire = new TextureAtlas("Left_fire.atlas");
		textureAtlasRightFire = new TextureAtlas("Right_fire.atlas");


		//Készítsünk hozzá animációt.
		animationLeftFire = new Animation(1 / 15f, textureAtlasLeftFire.getRegions());
		animationRightFire = new Animation(1 / 15f, textureAtlasLeftFire.getRegions());
		//Készítsünk belőle spriteot, az első kép a 0. indexű legyen. Később majd a spriteban kell cserélgetni a képeket.
		//Az animáció nem lenne fontos, ha nem számít, hogy melyik képet mikor játszuk le. (amúgy meg ki is lehetne számolni)
		spriteLeftFire = new Sprite(animationLeftFire.getKeyFrame(0, true));
		spriteRightFire = new Sprite(animationLeftFire.getKeyFrame(0, true));
//---- ANIMÁCIÓ ----------------------------------------------------------------------------
//---- ANIMÁCIÓ ----------------------------------------------------------------------------
//---- ANIMÁCIÓ ----------------------------------------------------------------------------

		//EZ VOLNA A TOVÁBBI MUNKA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//EZ VOLNA A TOVÁBBI MUNKA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!//EZ VOLNA A TOVÁBBI MUNKA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//spriteRightFire = new Sprite(new Texture("Ball.png"));

		spriteLandingFire = new Sprite(new Texture("ball.png"));

		spriteSpaceShip = new Sprite(textureSpaceShip);

		//spriteFromAtlas.setPosition(0, 256);

		textureAtlasSmoke = new TextureAtlas("smoke.atlas");
		animationSmoke = new Animation(1 / 30f, textureAtlasSmoke.getRegions());
		spriteSmoke = new Sprite(animationSmoke.getKeyFrame(2, true));

		setSize(spriteSpaceShip.getWidth(), spriteSpaceShip.getHeight());


	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		spriteSpaceShip.setSize(width, height);
		spriteSpaceShip.setOrigin(spriteSpaceShip.getWidth() / 2, spriteSpaceShip.getHeight() / 2);
		//setOrigin(0, 0);
		setFire();
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		spriteSpaceShip.setPosition(x - getOriginX(), y - getOriginX());
		//setOrigin(getX() + getWidth() / 2, getY() - getHeight() / 2);
		//setOrigin(0, 0);

		// A következő sor, fölösleges.
		//spriteSpaceShip.setOrigin(spriteSpaceShip.getWidth() / 2, spriteSpaceShip.getHeight() / 2);
		//spriteSpaceShip.setOrigin(0, 0); // ...vagy ez hibás

		setFire();

	}

	@Override
	public void setRotation(float degrees) {
		super.setRotation(degrees);
		spriteSpaceShip.setRotation(degrees);
		spriteLeftFire.setRotation(degrees);
		spriteRightFire.setRotation(degrees);
		spriteLandingFire.setRotation(degrees);
	}

	private void setFire() {
		spriteLeftFire.setSize(getWidth() / 6, getHeight() / 4);
		spriteLeftFire.setPosition(getX(), getY() - spriteLeftFire.getHeight());
		spriteLeftFire.setOrigin(spriteSpaceShip.getWidth() / 2, spriteSpaceShip.getOriginY() + spriteLeftFire.getHeight());

		spriteRightFire.setSize(getWidth() / 6, getHeight() / 4);
		spriteRightFire.setPosition(getX() + getWidth() - spriteRightFire.getWidth(), getY() - spriteRightFire.getHeight());
		spriteRightFire.setOrigin(-spriteSpaceShip.getWidth() / 2 + spriteRightFire.getWidth(), spriteSpaceShip.getOriginY() + spriteRightFire.getHeight());

		spriteLandingFire.setSize(getWidth() / 3, getHeight() / 2);
		spriteLandingFire.setPosition(getX() + getWidth() / 2 - spriteLandingFire.getWidth() / 2, getY() - spriteLandingFire.getHeight());
		spriteLandingFire.setOrigin(spriteLandingFire.getWidth() / 2, spriteSpaceShip.getOriginY() + spriteLandingFire.getHeight());


		spriteSmoke.setSize(getHeight(), getHeight());
		spriteSmoke.setPosition(getX() + getWidth() / 2 - spriteSmoke.getWidth() / 2, getY() - spriteSmoke.getHeight() / 2);


	}

	public enum RocketType {landing, left, right}

	public void setRocketState(RocketType type, boolean state) {
		switch (type) {
			case landing:
				landingRocketState = state;
				break;
			case left:
				leftRocketState = state;
				if (leftRocketState) rightRocketState = true;
				break;
			case right:
				rightRocketState = state;
				if (rightRocketState) leftRocketState = true;
				break;
		}
	}

	// http://pimentoso.blogspot.hu/2013/01/meter-and-pixel-units-in-box2d-game.html


	public void setSmoke()
	{
		if (smoke==false) smokeFrame=0;
		smoke=true;

		//animationSmoke.setPlayMode(Animation.PlayMode.NORMAL);


	}
	public void setRocket(){
		if(fireIsInProgress == false) fireFrame = 0;
		fireIsInProgress=true;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		spriteSpaceShip.draw(batch);

		if (smoke)
		{
			spriteSmoke.setRegion(textureAtlasSmoke.getRegions().get(smokeFrame));
			smokeFrame++;
			spriteSmoke.draw(batch);
			if (smokeFrame>=textureAtlasSmoke.getRegions().size)
			{
				smoke=false;
			}
		}
		if(fireIsInProgress){
			spriteLeftFire.setRegion(textureAtlasLeftFire.getRegions().get(fireFrame));
			spriteRightFire.setRegion(textureAtlasRightFire.getRegions().get(fireFrame));
			fireFrame++;
			spriteLeftFire.draw(batch);
			spriteRightFire.draw(batch);
			if(fireFrame >= textureAtlasRightFire.getRegions().size && fireFrame >= textureAtlasLeftFire.getRegions().size){
				fireIsInProgress = false;
			}
		}

		if (landingRocketState) {
			spriteLandingFire.draw(batch);
		}

		if (leftRocketState) {
//-------------- Renderelés közben cseréljük ki az ábrát. Az animation adja az indexét annak, amire cserélni kell, az eltelt idő függvényében.
//-------------- Renderelés közben cseréljük ki az ábrát. Az animation adja az indexét annak, amire cserélni kell, az eltelt idő függvényében.
			spriteLeftFire.setRegion(animationLeftFire.getKeyFrame(elapsedTime, true));
//-------------- Renderelés közben cseréljük ki az ábrát. Az animation adja az indexét annak, amire cserélni kell, az eltelt idő függvényében.
//-------------- Renderelés közben cseréljük ki az ábrát. Az animation adja az indexét annak, amire cserélni kell, az eltelt idő függvényében.
			spriteLeftFire.draw(batch);
		}

		if (rightRocketState) {
			spriteRightFire.draw(batch);
		}

	}

	@Override
	public void act(float delta) {

		final float elapsedTime = Gdx.graphics.getDeltaTime();
		setRotation((float)Math.toDegrees(body.getAngle()));

		if (landingRocketState && mMainRocketOverheatedTime == 0) { // be van kapcsolva a rakét, és nincs túlmelegedve
			mMainRocketUsingTime += elapsedTime;

			body.applyForce(0, (LANDING_ROCKET_POWER * elapsedTime) * 5e7f, 0, 0, true);

			if (mMainRocketUsingTime > 2) { // többet használtuk, mint 2 másodperc
				mMainRocketOverheatedTime = 3;
                Gdx.app.log("Rocket", "Felmelegedett");
			}

		} else if (mMainRocketOverheatedTime > 0) { // túl van melegedve
			mMainRocketOverheatedTime -= elapsedTime;
			if (mMainRocketOverheatedTime < 0) {
				mMainRocketOverheatedTime = 0;
                mMainRocketUsingTime = 0;
                Gdx.app.log("Rocket", "Lehűlt");
			}

		}


		if (leftRocketState) {
			body.applyForceToCenter(2e7f, 0, false);
		} else if (rightRocketState) {
			body.applyForceToCenter(-2e7f, 0, false);
		}

		final Vector2 pos = body.getPosition();
		setPosition(pos.x, pos.y);

	}

}
