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

	//Sprite T = new Sprite();
	private ArrayList<Sprite> stars = new ArrayList<Sprite>();
	private Sprite spriteSpace;

	public ActorBackground() {
		spriteSpace = new Sprite(new Texture("Space.jpg"));

		final Texture starTexture = new Texture("StarWhite.png");
		for (int i = 0; i < 100; i++) {
			stars.add(new Sprite(starTexture));
		}

	}

	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		setOrigin(getX() / 2, getY() / 2);
		setBounds(getX(), getY(), width, height);

		// Úgy érzékelem, hogy fölösleges a `bounds`.
		// bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());

		for (Sprite star : stars) {
			star.setPosition((float) (Math.random() * width), (float) (Math.random() * height));
			int size = 1 + (int) (Math.random() * 20);
			star.setSize(size, size);
		}

		spriteSpace.setSize(width, height);
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		setOrigin(getX() / 2, getY() / 2);
		//bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
	}

	@Override
	public void draw(Batch batch, float alpha) {
		spriteSpace.draw(batch);
		for (Sprite star : stars) star.draw(batch);
	}


	@Override
	public void act(float delta) {
		for (Sprite star : stars) {
			if (star.getX() > getWidth() * .9) { // kicsúszik a képernyőből
				// Miért `-star.getWidth()`? Miért nem 0?
				star.setPosition(-star.getWidth(), (float) (Math.random() * getHeight()));
			} else {
				star.setX(star.getX() + star.getWidth() / 10);
			}
		}

	}
}
//LOL
