package com.mygdx.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public abstract class State {
    protected GameStateManager gsm;
    protected OrthographicCamera camera;
    protected ShaderProgram shader;

    public State(GameStateManager gsm,ShaderProgram shaderProgram) {
        this.gsm = gsm;
        this.shader = shaderProgram;
    }

    protected abstract void handleInput();

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
}
