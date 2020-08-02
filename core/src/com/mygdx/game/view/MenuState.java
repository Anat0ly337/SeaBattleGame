package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;

public class MenuState extends State {
    private Texture background;
    private Texture playBtn;
    private Music battleMusic;
    private int buttonX;
    private int buttonY;

    public MenuState(GameStateManager gsm, ShaderProgram shader) {
        super(gsm, shader);
        background = new Texture("seabattle.jpg");
        playBtn = new Texture("butn.png");
        battleMusic = Gdx.audio.newMusic(Gdx.files.internal("battle.mp3"));
        buttonX = ScreenParams.screenWigth / 2 - 100;
        buttonY = ScreenParams.screenHeight / 2 + 20;
        camera = new OrthographicCamera();
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            battleMusic.stop();
            gsm.set(new GameState(gsm, shader));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        battleMusic.setLooping(true);
        battleMusic.play();
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.draw(playBtn, buttonX, buttonY, 300, 300);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        battleMusic.dispose();
    }
}