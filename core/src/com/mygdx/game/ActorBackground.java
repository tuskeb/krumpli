package com.mygdx.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Ez a csillagos égbolt teljes képernyőn
 */
public class ActorBackground extends Actor {

    Texture T = new Texture(new FileHandle("ball.png"));
    private class Star {
        private float x, y;
        private float radius;

        Star() {

        }

        public float getX() {
            return this.x;
        }

        public float getY() {
            return this.y;
        }
    }


    private ArrayList<Star> stars = new ArrayList<Star>();

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(T,0,0);

    }

    private final World world;
    private final Body body = null;

    ActorBackground(World world) {

        this.world = world;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.x = 10;
        bodyDef.position.y = 10;
        bodyDef.linearDamping = .1f;
        bodyDef.angularDamping = .5f;
/*
        this.body = this.world.createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(50, 80);

        Fixture fix = body.createFixture(polygonShape, 50);
        fix.setDensity(1);
        fix.setFriction(1f);
        fix.setRestitution(0.8f);

        polygonShape.dispose();
        */
    }


    @Override
    public void act(float delta) {

    }
}
