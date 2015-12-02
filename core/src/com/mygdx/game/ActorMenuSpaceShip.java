package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ActorMenuSpaceShip extends ActorSpaceship {
    private float baseX, baseY, newX, newY, currentX, currentY;
    public ActorMenuSpaceShip() {
        super(new World(new Vector2(0, 0), true));
        setRocketState(RocketType.landing, true);
        setRocketState(RocketType.left,true);
        setRocketState(RocketType.right, true);
    }

    private float i;

    @Override
    public void act(float delta) {
        if (Gdx.input.isTouched(2)) {setBumm();}

        if (getY() > 0) {
            i += 0.02;
            setPosition(Gdx.graphics.getWidth() * .88f + (float) Math.sin(i) * 16f, getY() - 1);
        } else if (getY() == 0) {
            setY(-1);
            setSmoke();
        }
        if(Gdx.input.getX() > getX() && Gdx.input.getX()<getX()+getWidth() && Gdx.input.getY() > getY() && Gdx.input.getY()<getY()+getHeight()) setPosition(Gdx.input.getX()/2, Gdx.input.getY()/2);

            if(Gdx.input.isTouched(4) ) {
                    newX = baseX + Gdx.input.getAccelerometerY() * getWidth();
                    newY = baseY + Gdx.input.getAccelerometerY() * getHeight();
                    currentX += (newX - currentX) / 80;
                    currentY = newY;
                    setPosition(currentX, currentY);
            }
    }


    @Override
    public void setSize(float width, float height) {
        super.setSize(120, 240);
    }
}


