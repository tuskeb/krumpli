package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.BalazsScale;

public class BalazsDesktopLauncher {
    public static void main (String[] arg) {
        System.setProperty("user.name","CorrectUserName");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new BalazsScale(), config);
    }
}