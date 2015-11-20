package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Ez jeleníti meg a felszínt
 */
public class ActorSurface extends Actor {

	private final World world;
	private final Body body = null;

	ActorSurface(World world) {

		this.world = world;
/*
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.x = 0;
		bodyDef.position.y = 0;
		bodyDef.linearDamping = .1f;
		bodyDef.angularDamping = .5f;

		this.body = this.world.createBody(bodyDef);

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setAsBox(50, 80);

		Fixture fix = body.createFixture(polygonShape, 50);
		fix.setDensity(3);
		fix.setFriction(1f);
		fix.setRestitution(0.8f);

		polygonShape.dispose();
*/

	}

	@Override
	public void act(float delta) {

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

	}
}
