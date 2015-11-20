package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;


import java.util.ArrayList;
import java.util.Vector;

import javafx.stage.Stage;

/**
 * Ez a csillagos égbolt teljes képernyőn
 */
public class ActorBackground extends MyActor {

    Rectangle bounds;


    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        setOrigin(getX() / 2, getY() / 2);
        setBounds(getX(), getY(), width, height);
        bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
        for (Sprite s: csillagok) {
            s.setPosition(veletlenSzam(0, (int) width), veletlenSzam(0, (int) height));
            int size = veletlenSzam(1, 20);
            s.setSize(size,size);
        }
        spriteSpace.setSize(width,height);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        setOrigin(getX() / 2, getY() / 2);
        bounds=new Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }

    //Sprite T = new Sprite();
    private Vector<Sprite> csillagok = new Vector<Sprite>();
    private ArrayList<Texture> textureStars = new ArrayList<Texture>();
    private Texture textureSpace;
    private Sprite spriteSpace;
    //private ArrayList<Star> mStars = new ArrayList<Star>();


    public ActorBackground() {
        textureSpace = new Texture(new FileHandle("star.png"));
        spriteSpace = new Sprite(textureSpace);
        textureStars.add(new Texture(new FileHandle("star.png")));
        for(int i = 0; i<100; i++)
        {
            csillagok.add(new Sprite(textureStars.get(0)));
        }
    }
    public int veletlenSzam(int a, int b){return (int) (Math.random()*(b-a+1)+a);}
    @Override
    public void draw(Batch batch, float alpha){

        spriteSpace.draw(batch);
        for (Sprite s: csillagok) {
            s.draw(batch);
        }

    }





    @Override
    public void act(float delta) {
        for (Sprite s: csillagok) {
            s.setX((s.getX()+s.getWidth()/10));
            if (s.getX()> getWidth())
            {
                s.setPosition(0 - s.getWidth(), veletlenSzam(0, (int) getHeight()));
            }
        }
        super.act(delta);
    }
}
