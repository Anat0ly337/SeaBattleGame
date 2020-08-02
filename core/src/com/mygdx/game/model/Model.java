package com.mygdx.game.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.dto.Coordinates;
import com.mygdx.game.view.GameStateManager;
import com.mygdx.game.view.ScreenParams;

import java.util.List;

public abstract class Model {
    protected Texture texture;
    protected Vector3 position;
    protected Rectangle rectangle;

    public abstract Texture getTexture();
    public abstract Vector3 getPosition();
    public abstract Rectangle getRectangle();
    public abstract List<Coordinates> getNeighbords();
}
