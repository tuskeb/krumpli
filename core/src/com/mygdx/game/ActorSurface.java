package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

		setX(0);
		setY(0);

		this.world = world;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.x = 0;
		bodyDef.position.y = 0;

		this.body = this.world.createBody(bodyDef);

		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.fill();
		textureSolid = new Texture(pix);
		pix.setColor(0x10df06ff);

		final ArrayList<Float> groundSegments = new ArrayList<Float>();

		groundSegments.add(0f);
		groundSegments.add(0f);

		final float MIN_HEIGHT = 100, HEIGHT_DIFF = 50, MIN_WIDTH = 30, MAX_WIDTH = 100;

		float x = 300, y = (float)Math.random() * 100f;

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

		float [] ground = new float[groundSegments.size()];
		//final float [] vertices = new float[] {10, 100, 60, 200, 120, 130, 150, 30, 90, 3 /* ezek szerint konkáv alakzatok nincsenek? állítsd 70-re/3-ra*/}; // Fák.
		for (int i = 0;i < groundSegments.size();) {
			ground[i] = groundSegments.get(i);
			//vertices[i] = new i >= 2 ? ground[i] - ground[i - 2] : ground[i];
			i++;

			ground[i] = groundSegments.get(i);
			//vertices[i] = i >= 2 ? ground[i] - ground[i - 2] : ground[i];
			i++;


		}
		ground = new float[] {0,0,300,100,500,300,600,0};

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
		poly.setX(0);
		poly.setY(0);
		polyBatch = new PolygonSpriteBatch();

		// convert ground segments to vertices

		PolygonShape polygonShape = new PolygonShape();
		polygonShape.set(ground);

		Fixture fix = body.createFixture(polygonShape, 0);

		polygonShape.dispose();
	}

	@Override
	public void act(float delta) {

	}

	// KÉRLEK NE IROGASSATOK BELE FONTOS INFORMÁCIÓKAT, MERT SZÁJBARÚGOK VALAKIT! EZEK MINDENKI SZÁMÁRA LÁTSZÓDNAK. KÖSZÖNÖM! (bocsánat, csak így is mérges vagyok már) //OK, VETTETM! DP

	@Override
	public void draw(Batch batch, float parentAlpha) {

		//poly.draw(batch);
/*
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.polygon(ground);
		shapeRenderer.end();
		*/
		//polyBatch.begin();
		//poly.draw((PolygonSpriteBatch)batch);
		//polyBatch.end();

	}
}
