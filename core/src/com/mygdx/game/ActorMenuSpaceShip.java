package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class ActorMenuSpaceShip extends ActorSpaceship {
    private float toX=-1, toY=-1;
    public ActorMenuSpaceShip() {
        super(new World(new Vector2(0, 0), true));
        setRocketState(true);
    }

    public void setRocketState(boolean state) {
        leftRocketState=state;
        rightRocketState=state;
        landingRocketState=state;
    }

    private float i;
    @Override
    public void act(float delta) {
        if (!getBumm()) {

            if (Gdx.input.isTouched(3)) {
                setBumm();

            }
/*            if (getBumm())
            {
                if (Gdx.input.isTouched(1)) {
                    setRepair();
                }
            }*/
            if (Gdx.input.isTouched()) {
                toX = Gdx.input.getX() - getWidth() / 2;
                toY = Gdx.graphics.getHeight() - Gdx.input.getY() - getHeight() / 2;
            }

            if (toX == -1) {
                if (getY() > 0) {
                    i += 0.02;
                    setPosition(Gdx.graphics.getWidth() * .88f + (float) Math.sin(i) * 16f - 90, getY() - 1);
                } else if (getY() == 0) {
                    setY(-1);
                    setSmoke();

                }
            } else {
                if (getY() < 0) {
                    setSmoke();
                    landing.play();
                    setY(0);
                    toY = 0;
                }
                setPosition((getX() * 5 + toX) / 6, (getY() * 5 + toY) / 6);
            }
        }
        //if(Gdx.input.getX() > getX() && Gdx.input.getX()<getX()+getWidth() && Gdx.input.getY() > getY() && Gdx.input.getY()<getY()+getHeight()) setPosition(Gdx.input.getX()/2, Gdx.input.getY()/2);
/*
            if(Gdx.input.isTouched(4) ) {
                    newX = baseX + Gdx.input.getAccelerometerY() * getWidth();
                    newY = baseY + Gdx.input.getAccelerometerY() * getHeight();
                    currentX += (newX - currentX) / 80;
                    currentY = newY;
                    setPosition(currentX, currentY);

            }*/
    }



}


