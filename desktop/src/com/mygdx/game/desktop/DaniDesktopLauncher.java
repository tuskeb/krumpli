package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyScreen;
import com.mygdx.game.DaniSpaceGame;

public class DaniDesktopLauncher {
    public static void main (String[] arg) {
        System.setProperty("user.name","pendroid");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = (int)MyScreen.VIRTUAL_WIDTH;
        config.height = (int)MyScreen.VIRTUAL_HEIGHT;
        config.title="SPACE GAME";
        new LwjglApplication(DaniSpaceGame.sGame, config);
    }
}
