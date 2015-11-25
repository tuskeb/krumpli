package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by 13-0123 on 2015.11.25..
 */
public class ActorMenuSpaceShip extends ActorSpaceship {
    public ActorMenuSpaceShip() {
        super(new World(new Vector2(0, 0), true));
    }

    @Override
    public void act(float delta) {
        if(getY() > 0) setPosition(getX(),getY()-1);
    }
}
