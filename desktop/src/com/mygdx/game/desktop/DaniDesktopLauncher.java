package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DaniMyGdxGame;
import com.mygdx.game.MyScreen;
import com.mygdx.game.DaniSpaceGame;

public class DaniDesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new DaniMyGdxGame(), config);
    }
}
