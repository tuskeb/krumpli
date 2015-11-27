package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ActorMenuSpaceShip extends ActorSpaceship {
    public ActorMenuSpaceShip() {
        super(new World(new Vector2(0, 0), true));
    }

    @Override
    public void act(float delta) {

	    if(getY() > 0) {
		    a += 0.02;
		    setPosition(Gdx.graphics.getWidth() * .88f + (float) Math.sin(a) * 16f, getY() - 1);
	    } else if(getY() < 0){


    }

    float a = 0;

}
/*
allapot+=0.1;
        y--;
        x= Gdx.graphics.getWidth()/2 + (float)Math.sin(allapot)*100;
        if (y<-h)
        {

            y = Gdx.graphics.getHeight()+h;
        }

*/
/*
float a = 0;
        a += 0.01;
        if(getY() > 0) setPosition(getX()+a,getY()-(float)Math.sin(a));
        if(getX() < 0) setPosition(getX()-a, getY()-a);
 */

