package com.mygdx.game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

public class SpaceGame extends Game implements ApplicationListener {
	public SpaceGame spaceGame;
	public ScreenMenu screenMenu;
	public ScreenHelp screenHelp;




	@Override
	public void create () {
		//spaceGame = new SpaceGame(this);
		screenHelp =new ScreenHelp(this);
		screenMenu=new ScreenMenu(this);
		setScreen(screenMenu);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
