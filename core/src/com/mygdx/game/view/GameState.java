package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.controller.GameBattle;
import com.mygdx.game.model.Model;
import com.mygdx.game.model.Sail;

import java.util.ArrayList;
import java.util.List;

public class GameState extends State {
    private Texture background;
    private Texture field;
    private Texture button;
    private List<Model> models;
    private GameBattle gameBattle;


    public GameState(GameStateManager gsm, ShaderProgram shader) {
        super(gsm, shader);
        camera = new OrthographicCamera();
        background = new Texture("sea.jpg");
        field = new Texture("cells.jpg");
        models = new ArrayList<>();
        button = new Texture("auto.png");
        gameBattle = new GameBattle();
        models.addAll(gameBattle.getSails());

    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            models.clear();
            gameBattle = new GameBattle();
            models.addAll(gameBattle.getSails());
        }
    }


    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        shader.setUniformf("resolution", (float)ScreenParams.screenWigth,(float) ScreenParams.screenHeight);
        sb.setShader(shader);
        sb.begin();
        sb.draw(background, 0, 0, ScreenParams.screenWigth, ScreenParams.screenHeight);
        sb.draw(field, 0, ScreenParams.screenHeight - ScreenParams.cellsHeight, ScreenParams.cellsWidth, ScreenParams.cellsHeight);
        sb.draw(button, ScreenParams.screenWigth / 2 - 100, ScreenParams.screenHeight / 4 - 100, 200, 200);
        for (Model model : models) {
            if (model instanceof Sail) {
                sb.draw(model.getTexture(), model.getPosition().x, model.getPosition().y, model.getRectangle().width, model.getRectangle().height);
            }
        }
        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();
        field.dispose();
        button.dispose();
    }
}
