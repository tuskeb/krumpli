package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ActorMenuSpaceShip extends ActorSpaceship {
    public ActorMenuSpaceShip() {
        super(new World(new Vector2(0, 0), true));
        setRocketState(RocketType.landing, true);
        setRocketState(RocketType.left,true);
        setRocketState(RocketType.right,true);
    }

    private float i;

    @Override
    public void act(float delta) {
        if (getY() < 200)
        {
            setBumm();
        }

        if (getY() > 0) {
            i += 0.02;
            setPosition(Gdx.graphics.getWidth() * .88f + (float) Math.sin(i) * 16f, getY() - 1);
        } else if (getY() == 0) {
            setY(-1);
            setSmoke();
        }
    }

}


