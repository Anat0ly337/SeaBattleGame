/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.dto.Coordinates;
import com.mygdx.game.view.ScreenParams;

import java.util.List;
/*
 *Класс для коричневых квадратов вокруг кораблей
 */
public class GraySquare extends Model {
    public GraySquare(int x, int y, int size) {
        texture = new Texture("gray.png");
        position = new Vector3(x, y, 0);
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        rectangle.height = (float) (size * 0.95);
        rectangle.width = (float) (size * 0.95);
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Vector3 getPosition() {
        return position;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public List<Coordinates> getNeighbords() {
        return null;
    }
}
