package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyActor extends Actor {

	private long lastDraw = System.currentTimeMillis();
	protected long elapsedTime;

	@Override
	public void draw(Batch batch, float parentAlpha) {
		final long currentTime = System.currentTimeMillis();
		elapsedTime = currentTime - lastDraw;
		lastDraw = currentTime;
	}
}
