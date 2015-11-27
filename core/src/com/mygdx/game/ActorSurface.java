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
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Ez jeleníti meg a felszínt
 */
public class ActorSurface extends Actor {

	private final World world;
	private final Body body;

	Sprite floor;

	ActorSurface(World world) {

		this.world = world;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.position.x = 0;
		bodyDef.position.y = 0;

		this.body = this.world.createBody(bodyDef);





		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.setColor(0xDEADBEFF); // DE is red, AD is green and BE is blue.
		pix.fill();
		textureSolid = new Texture(pix);
		ArrayList<Float> v = new ArrayList<Float>();
		float yy = (float)(Math.random()*128);
		float xx = -100f;
		while (xx<640f)
		{
			xx += (float)(Math.random()*128);
			yy = (float)(Math.random()*128);
			v.add(xx);
			v.add(yy);
		}

		float[] talaj = new float[v.size()];
		for (int i=0; i<v.size(); i++) {
			talaj[i]=v.get(i);
		}


		/*
		float[] talaj = new float[] {      // Four vertices
				50, 0,            // Vertex 0        3--2
				100, 0,          // Vertex 1         | /|
				100, 100,        // Vertex 2         |/ |
				0, 100           // Vertex 3         0--1
		};
		*/
		PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid), talaj
				, new short[] {
				0, 1, 2,         // Two triangles using vertex indices.
				0, 2, 3          // Take care of the counter-clockwise direction.
		});
		poly = new PolygonSprite(polyReg);

		poly.setOrigin(0, 0);
		polyBatch = new PolygonSpriteBatch();





		PolygonShape polygonShape = new PolygonShape();
		//polygonShape.setAsBox(Gdx.graphics.getWidth(), 100);
		polygonShape.set(talaj);


		Fixture fix = body.createFixture(polygonShape, 50);
		fix.setDensity(2);


		/*
		Pixmap pixmap = new Pixmap(640,128, Pixmap.Format.RGBA8888);
		int i=-100;
		float magassag = (float)(Math.random()*128);
		pixmap.setColor(Color.CYAN);

		while (i<640)
		{

			float hossz = (float)(Math.random()*100);
			pixmap.fillTriangle(i,128,i + (int)(hossz / 2) ,0,i+100,128);
			i+=hossz;
		}
		Texture texture = new Texture(pixmap);
		floor = new Sprite(texture);
		*/

		polygonShape.dispose();






	}

	PolygonSprite poly;
	PolygonSpriteBatch polyBatch;
	Texture textureSolid;

	@Override
	public void act(float delta) {

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		polyBatch.begin();

		poly.draw(polyBatch);
		polyBatch.end();
		//poly.rotate(1.1f);
	}
}
