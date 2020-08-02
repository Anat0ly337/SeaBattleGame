package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.dto.Coordinates;
import com.mygdx.game.view.ScreenParams;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Position;

public class Sail extends Model {
    private int x;
    private int y;
    private boolean isVertical;
    private Coordinates coordinates;

    public Sail(int x, int y, boolean isVertical) {
        coordinates = new Coordinates(x, y);
        this.isVertical = isVertical;
        this.x = x;
        this.y = y;

        double startX = ScreenParams.cellsWidth - ScreenParams.cellsWidth * 0.935;
        double startY = ScreenParams.screenHeight - ScreenParams.cellsHeight * 0.99;
        int positionX = (int) (startX + (x - 1) * ScreenParams.cellW);
        int positionY = (int) (startY + (y - 1) * ScreenParams.cellH);


        texture = new Texture("sail.jpg");
        position = new Vector3(positionX, positionY, 0);
        rectangle = new Rectangle();
        rectangle.x = positionX;
        rectangle.y = positionY;
        rectangle.height = (float) (ScreenParams.cellH);
        rectangle.width = (float) (ScreenParams.cellW);

    }


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isVertical() {
        return isVertical;
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
        List<Coordinates> coordinatesList = new ArrayList<>();
        coordinatesList.add(new Coordinates(x + 1, y + 1));
        coordinatesList.add(new Coordinates(x + 1, y));
        coordinatesList.add(new Coordinates(x + 1, y - 1));
        coordinatesList.add(new Coordinates(x - 1, y + 1));
        coordinatesList.add(new Coordinates(x - 1, y));
        coordinatesList.add(new Coordinates(x - 1, y - 1));
        coordinatesList.add(new Coordinates(x, y + 1));
        coordinatesList.add(new Coordinates(x, y));
        coordinatesList.add(new Coordinates(x, y - 1));

        return coordinatesList;
    }
}
