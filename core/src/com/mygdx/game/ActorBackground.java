package com.mygdx.game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Ez a csillagos égbolt teljes képernyőn
 */
public class ActorBackground extends MyActor {


    Texture T = new Texture(new FileHandle("ball.png"));
    private class Star {
        private float mX, mY;
        private float mSize;

        Star() {

        }

        public float getmX() {
            return mX;
        }

        public float getmY() {
            return mY;
        }
    }


    private ArrayList<Star> mStars = new ArrayList<Star>();

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(T,0,0);
    }
}
