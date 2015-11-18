package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;


public class DaniMyGdxGame extends Game implements ApplicationListener {

    public ScreenGame screenGame;
    public DaniScreenMenu screenMainMenu;
    public ScreenHelp screenHelp;



    @Override
    public void create () {
        //screenHelp = new ScreenHelp(this);

        //screenGame =new ScreenGame(this);
        screenMainMenu=new DaniScreenMenu(this);
        setScreen(screenMainMenu);


    }


    @Override
    public void dispose() {
        super.dispose();
    }

}
