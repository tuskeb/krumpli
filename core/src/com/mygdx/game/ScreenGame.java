package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.awt.Color;

/**
 * A játék képernyője
 */
public class ScreenGame extends MyScreen {


    // https://github.com/libgdx/libgdx/wiki/A-simple-game
    // http://pimentoso.blogspot.hu/2013/01/meter-and-pixel-units-in-box2d-game.html
    Box2DDebugRenderer	debugger = new Box2DDebugRenderer();

    private void setWinPoint()
    {
        if (WIN_POINT==-1)
        {
            WIN_POINT=(int)(mElapsedTime-flyingStartTime);
        }
        else
        {
            if (mElapsedTime-flyingStartTime<WIN_POINT)
            {
                WIN_POINT=(int)(mElapsedTime-flyingStartTime);
            }
        }
    }

    private World world = new World(new Vector2(0, -10), true);

    {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Body bodyA = contact.getFixtureA().getBody(), bodyB = contact.getFixtureB().getBody();

                ActorNewSurface surface;
                ActorSpaceship spaceship;

                if (bodyA.getUserData() instanceof ActorNewSurface && bodyB.getUserData() instanceof ActorSpaceship) {
                    surface = (ActorNewSurface) bodyA.getUserData();
                    spaceship = (ActorSpaceship) bodyB.getUserData();
                    if (surface.mLandingArea && spaceShip.body.getLinearVelocity().len() < 30f) {
                        setWinPoint();
                        SpaceGame.sGame.showScreen(SpaceGame.Screens.STAT);
                    } else {
                        spaceship.setBumm();
                    }

                    pause();
                    Gdx.app.log("játék", "vége a játéknak");
                } else if (bodyB.getUserData() instanceof ActorNewSurface && bodyA.getUserData() instanceof ActorSpaceship) {
                    surface = (ActorNewSurface) bodyB.getUserData();
                    spaceship = (ActorSpaceship) bodyA.getUserData();

                    if (surface.mLandingArea && spaceShip.body.getLinearVelocity().len() < 30f) {
                        setWinPoint();
                        SpaceGame.sGame.showScreen(SpaceGame.Screens.STAT);
                    } else {
                        spaceship.setBumm();
                    }

                    pause();
                    Gdx.app.log("játék", "vége a játéknak");

                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();

    private Stage
            controlsStage = new Stage(),
            gameStage = new Stage();


    private ActorSpaceship spaceShip = new ActorSpaceship(world);
    private ActorBackground space = new ActorBackground();
    private TextButton button;
    private  Label END_GAME;
    final Music s = Gdx.audio.newMusic(Gdx.files.internal("game_theme_min.mp3"));
    public float flyingStartTime=0;
    public static int WIN_POINT=-1;
/*



*/

    final Display display = new Display();
    final Sound click = Gdx.audio.newSound(Gdx.files.internal("Sound/click_sound.mp3"));
    final Sound click_back = Gdx.audio.newSound(Gdx.files.internal("Sound/click_sound_back.mp3"));

    public ScreenGame() {
        super();
        flyingStartTime +=mElapsedTime;
        spaceShip.setSize(100, 150);
        spaceShip.setPosition(Gdx.graphics.getWidth() - spaceShip.getWidth() / 2,
                Gdx.graphics.getHeight());


        button = new TextButton("Indítás", MyScreen.TEXT_BUTTON_STYLE);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                resume();
                click.play();
            }
        });
        ActorBackground actorBackground = new ActorBackground();
        gameStage.addActor(space);
        gameStage.addActor(actorBackground);
        gameStage.addActor(spaceShip);

        boolean landingArea=false;
        int x = 0;
        float egyseg=Gdx.graphics.getWidth()/6;
        do {
            float width, height;
            if (Math.random() < .3) {
                width = 120;
            } else {
                width = 20 + (float) Math.random() * 60;
            }
            landingArea = (x>egyseg*2 && x<egyseg*3) || (x>egyseg*4 && x<egyseg*5);
            height = 30 + (float) Math.random() * 150;
            if (landingArea)
            {
                width=130;
            }
            gameStage.addActor(new ActorNewSurface(world, width, height, x, landingArea));
            x += width;

        } while (x < Gdx.graphics.getWidth());


        gameStage.addActor(button);
        gameStage.addActor(display);
        {
            if (Gdx.input.isTouched()) spaceShipGoUP();
            else spaceShipGoDown();
            //BEST_TIME=+1f;
        }
    }


    private float spX, spY, time;

    private void spaceShipGoUP() {
        spX = spaceShip.getX();
        spY = spaceShip.getY();
        time = 30;
        do {
            if (time > 0 & Gdx.input.isTouched()) {
                spY += 0.1;
                time--;
            }
            if (Gdx.input.isTouched()) time++;
            if (Gdx.input.getAccelerometerX() > 0) spX += 0.1;
            /*
            if (spX == Gdx.graphics.getWidth() || spY == Gdx.graphics.getHeight()) {
                ActorSpaceship.bumm = true;
            }
            */
            if (spX-spaceShip.getWidth() >= Gdx.graphics.getWidth() || spY-spaceShip.getHeight() >= Gdx.graphics.getHeight() || spX<=0) {
                ActorSpaceship.bumm = true;
            }
        } while (time == 0);
    }

    private void spaceShipGoDown() {
        if (mIsRunning) {
            if (!Gdx.input.isTouched()) spY -= 0.01;
        }
        //if (spaceShip.getY() == surface.getY()) SpaceGame.sGame.showScreen(SpaceGame.Screens.STAT);

    }



    private boolean mIsRunning = true;

    public void pause() {
        if (!mIsRunning) return;
        mIsRunning = false;

    }

    public void resume() {
        flyingStartTime =mElapsedTime;
        if (mIsRunning) return;
        mIsRunning = true;

    }

    @Override
    public void show() {
        s.play();
        s.setLooping(true);
        s.setVolume(0.5f);
        spaceShip.reset();
        resume();
        Gdx.input.setInputProcessor(new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.BACK:
                    case Input.Keys.ESCAPE:
                        SpaceGame.sGame.showScreen(SpaceGame.Screens.MENU);
                        click_back.play();
                        break;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                spaceShip.setRocketState(ActorSpaceship.RocketType.landing, true);
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                spaceShip.setRocketState(ActorSpaceship.RocketType.landing, false);
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        });
    }


    @Override
    public void hide() {
        super.hide();
        s.pause();
        pause();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        batch.begin();

        if (mIsRunning) {
            button.setVisible(false);
            float accelY = Gdx.input.getAccelerometerY();
            if (accelY > 2) {
                spaceShip.setRocketState(ActorSpaceship.RocketType.left, true);
            } else if (accelY < -2) {
                spaceShip.setRocketState(ActorSpaceship.RocketType.right, true);
            } else {
                spaceShip.setRocketState(ActorSpaceship.RocketType.left, false);
                spaceShip.setRocketState(ActorSpaceship.RocketType.right, false);
            }

            world.step(delta, 1, 1);
            gameStage.act(delta);

            // kiértünk a képernyőn kívülre
            final Vector2 pos = spaceShip.body.getPosition();
            if(pos.x < 0 || pos.x > Gdx.graphics.getWidth()-spaceShip.getWidth() || Gdx.graphics.getHeight()<pos.y+spaceShip.getHeight()) {
                spaceShip.setBumm();
                pause();
            }
            display.setTime((int)(mElapsedTime-flyingStartTime));
            display.setMagassag((int) spaceShip.getY());
            display.setSebesseg((int) (spaceShip.body.getLinearVelocity().len()/3));
            display.setTimeSlider(spaceShip.mMainRocketUsingTime);
            debugRenderer.render(world, camera.combined);
        } else {
            button.setVisible(true);
            display.setMagassag(0);
            display.setSebesseg(0);
            display.setTimeSlider(spaceShip.mMainRocketUsingTime);
            if (spaceShip.isBumming()==false) {
                if (Gdx.input.isTouched()) {

                    resume();
                    spaceShip.reset();
                }
            }
        }
        gameStage.act(Gdx.graphics.getDeltaTime());
        gameStage.draw();
        camera.update();
        //debugger.render( world, camera.combined);

        batch.end();



    }

}
