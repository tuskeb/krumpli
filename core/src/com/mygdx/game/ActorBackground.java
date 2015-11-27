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
    private static ArrayList<Star> stars = new ArrayList<Star>();

    static final int STAR_MIN_SIZE = 5, STAR_MAX_SIZE = 20;
    static final float STAR_FACTOR = 2, STAR_SHIFT = STAR_FACTOR * (STAR_MAX_SIZE - STAR_MIN_SIZE);

    private class Star {



        Star() {

        }

        private float baseX, baseY;

        final Sprite sprite = new Sprite(starTexture);

        void randomize() {
            baseX = (float) (Math.random() * (ActorBackground.this.getWidth() + 2 * STAR_SHIFT)) - STAR_SHIFT;
            baseY = (float) (Math.random() * (ActorBackground.this.getHeight() + 2 * STAR_SHIFT)) - STAR_SHIFT;

            int size = STAR_MIN_SIZE + (int) (Math.random() * (STAR_MAX_SIZE - STAR_MIN_SIZE));
            sprite.setSize(size, size);
        }

        void act() {
            /*if (sprite.getX() > getWidth()) { // kicsúszik a képernyőből
                sprite.setPosition(-sprite.getWidth(), (float) (Math.random() * getHeight()));
            } else {
                sprite.setX(sprite.getX() + sprite.getWidth() / 20);
            }*/

            sprite.setPosition(baseX + Gdx.input.getAccelerometerY() * STAR_FACTOR, baseY + Gdx.input.getAccelerometerX() * STAR_FACTOR);

        }

    }

	//Sprite T = new Sprite();
	private Sprite spriteSpace;

	public ActorBackground() {
		spriteSpace = new Sprite(new Texture("Space.jpg"));

		for (int i = 0; i < 100; i++) new Star();

	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		setOrigin(getX() / 2, getY() / 2);
		setBounds(getX(), getY(), width, height);

		for (Star star : stars) star.randomize();

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

		for (Star star : stars) {
            star.act();
		}

	}
}
//LOL
