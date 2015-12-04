package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Ez a csillagos égbolt teljes képernyőn
 */
public class ActorBackground extends MyActor {

    static final Texture starTexture = new Texture("StarWhite.png");
    private final ArrayList<Star> stars = new ArrayList<Star>();

    static final int STAR_MIN_SIZE = 10, STAR_MAX_SIZE = 30, STAR_SPACE = (STAR_MAX_SIZE * STAR_MAX_SIZE * 4);
    static final float STAR_FACTOR = 6, STAR_SHIFT = STAR_FACTOR * STAR_MAX_SIZE * 10;

    private class Star {

        private final boolean unruly = Math.random() > .9;

        Star() {
            int size = STAR_MIN_SIZE + (int) (Math.random() * (STAR_MAX_SIZE - STAR_MIN_SIZE));
            sprite.setSize(size, size);
            sprite.setAlpha(.8f);

            stars.add(this);
        }

        private float baseX, baseY, newX, newY, currentX, currentY;

        final Sprite sprite = new Sprite(starTexture);

        void randomizePosition() {
            baseX = (float) (Math.random() * (ActorBackground.this.getWidth() + 2 * STAR_SHIFT)) - STAR_SHIFT;
            baseY = (float) (Math.random() * (ActorBackground.this.getHeight()));

            currentX = baseX;
            currentY = baseY;
        }

        void act() {
            /*if (sprite.getX() > getWidth()) { // kicsúszik a képernyőből
                sprite.setPosition(-sprite.getWidth(), (float) (Math.random() * getHeight()));
            } else {
                sprite.setX(sprite.getX() + sprite.getWidth() / 20);

            }*/

            //if(!unruly) {
            newX = baseX + Gdx.input.getAccelerometerY() * STAR_FACTOR * sprite.getWidth();
            newY = baseY;

            currentX += (newX - currentX) / 80;

            currentY = newY;
            //} else {

            //}
            sprite.setPosition(currentX, currentY);

        }

    }

	private Sprite spriteSpace = new Sprite(new Texture("Space.jpg"));

	public ActorBackground() {
		for (int i = Gdx.graphics.getWidth() * Gdx.graphics.getHeight() / STAR_SPACE; --i >= 0; ) new Star();

	}

	@Override
	public void setSize(float width, float height) {
        super.setSize(width, height);
        setOrigin(getX() / 2, getY() / 2);

        for (Star star : stars) star.randomizePosition();


		spriteSpace.setSize(width, height);
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		setOrigin(getX() / 2, getY() / 2);
	}

	@Override
	public void draw(Batch batch, float alpha) {
		spriteSpace.draw(batch);
		for (Star star : stars) star.sprite.draw(batch);
	}


	@Override
	public void act(float delta) {
		for (Star star : stars) star.act();

	}
}

