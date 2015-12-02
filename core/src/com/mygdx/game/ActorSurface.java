package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Ez jeleníti meg a felszínt
 */
public class ActorSurface extends Actor {

	private final World world;
	private final Body body;

	Sprite floor;

	PolygonSprite poly;
	PolygonSpriteBatch polyBatch = new PolygonSpriteBatch(); // To assign at the beginning
	Texture textureSolid;

	//ShapeRenderer shapeRenderer = new ShapeRenderer();


	ActorSurface(World world) {

		this.world = world;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.x = 0;
		bodyDef.position.y = 0;

		this.body = this.world.createBody(bodyDef);

		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.setColor(0x222222FF); // DE is red, AD is green and BE is blue.
		pix.fill();
		textureSolid = new Texture(pix);

		final ArrayList<Float> groundSegments = new ArrayList<Float>();

		groundSegments.add(0f);
		groundSegments.add(0f);

		final float MIN_HEIGHT = 100, HEIGHT_DIFF = 50, MIN_WIDTH = 30, MAX_WIDTH = 100;

		float x = 0, y = (float)Math.random() * 100f;

		groundSegments.add(x);
		groundSegments.add(MIN_HEIGHT + y);

		do {
			y += (Math.random() * 2 * HEIGHT_DIFF) - HEIGHT_DIFF;
			if(y < 0) y = 0;
			x += MIN_WIDTH + Math.random() * (MAX_WIDTH - MIN_WIDTH);
			groundSegments.add(x);
			groundSegments.add(MIN_HEIGHT + y);
		} while(x < Gdx.graphics.getWidth());

		groundSegments.add((float)Gdx.graphics.getWidth());
		groundSegments.add(0f);

		final float [] ground = new float[groundSegments.size() + 2];
		for (int i = groundSegments.size();i > 0;) {
			--i;
			ground[i] = groundSegments.get(i);

		--i;
			ground[i] = groundSegments.get(i);
		}

		/*ArrayList<Short> triangles = new ArrayList<Short>();
		for(int i = 0;i < groundSegments.size();) {
			float X = groundSegments.get(i++);
			float Y = groundSegments.get(i++);

			triangles.add()

		}*/
		EarClippingTriangulator earClippingTriangulator = new EarClippingTriangulator();

		PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid), ground, earClippingTriangulator.computeTriangles(ground).toArray());
		poly = new PolygonSprite(polyReg);
		poly.setOrigin(0, 0);
		polyBatch = new PolygonSpriteBatch();


		PolygonShape polygonShape = new PolygonShape();
		polygonShape.set(ground);

		Fixture fix = body.createFixture(polygonShape, 50);
		fix.setDensity(2);
//HB
		polygonShape.dispose();
	}
		int velSzam (int a, int b){return (int)(Math.random()*(b-a+1)+a);}

	@Override
	public void act(float delta) {
		//DaniProm:Szerintem nem kell mozognia a földnek
	}
//36303559202
	@Override
	public void draw(Batch batch, float parentAlpha) {
/*
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.polygon(ground);
		shapeRenderer.end();
		*/
		polyBatch.begin();
		poly.draw(polyBatch);
		polyBatch.end();
		//poly.rotate(1.1f);
	}
}
