package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by tuskeb on 2015.12.04..
 */
public class ActorNewSurface extends MyActor {
    boolean mLandingArea = false;

    final Body body;
    final World world;
    Texture textureSolid;

    ActorNewSurface(World world, float width, float height, float x, boolean LandingArea) {
        mLandingArea = LandingArea;
        this.world = world;

        Pixmap pix = new Pixmap(Gdx.files.internal("sajt1.png")); //(int)width, (int)height, Pixmap.Format.RGBA8888
        Pixmap pix2 = new Pixmap(Gdx.files.internal("sajt2.png"));
        pix.drawPixmap(pix2, (int)getX(), (int)getY());
       // pix.setColor(0x10df06ff);
       // pix.fill();


        textureSolid = new Texture(pix);
       // textureSolid = new Texture(pix2);
        //pix2.dispose();

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.x = x;
        bodyDef.position.y = 0;
        setX(x);

        this.body = this.world.createBody(bodyDef);
        this.body.setUserData(this);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width, height);

        Fixture fix = body.createFixture(polygonShape, 0);

        polygonShape.dispose();

    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        //textureSolid.draw(pix2, getX(), getY());
        batch.draw(textureSolid, getX(), getY());


    }
}
