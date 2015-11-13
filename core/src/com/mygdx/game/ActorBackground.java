package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Ez a csillagos égbolt teljes képernyőn
 */
public class ActorBackground extends MyActor {

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



}
