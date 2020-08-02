/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mygdx.game.controller;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.dto.Coordinates;
import com.mygdx.game.model.Model;
import com.mygdx.game.model.Sail;
import com.mygdx.game.view.ScreenParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GameBattle implements Serializable {
    static final long serialVersionUID = -4862926644813433707L;

    private Map<Integer, Integer> shipMap; //ключ-значение количества кораблей и размер
    private List<Sail> sails; // массив парусов
    private List<List<Sail>> ships; // массив кораблей
    private Set<Coordinates> neighbords; //клетки, недоступные для расположения

    public GameBattle() {
        sails = new ArrayList<>();
        ships = new ArrayList<>();
        neighbords = new HashSet<>();
        shipMap = new HashMap<>();
        shipMap.put(1, 4);
        shipMap.put(2, 3);
        shipMap.put(3, 2);
        shipMap.put(4, 1);
        generateShips();
    }

    public List<Sail> getSails() {
        return sails;
    }

    /*
    * геренация кораблей
    */
    private List<Sail> generateShips() {
        for (int i = 1; i <= shipMap.size(); i++) {
            sails.addAll(generateSails(i));
        }
        return sails;
    }

    /* sail generation
     *@param count - количество кораблей
     */
    private List<Sail> generateSails(int count) {
        List<Sail> sails = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int size = shipMap.get(count);// размер по Мап
            Sail firstSail = randomStartSail(isVertical(), size);//Получаем рандомный первый парус
            sails.add(firstSail);
            neighbords.addAll(firstSail.getNeighbords());
            List<Sail> list = new ArrayList<>();
            if (firstSail.isVertical()) {
                for (int j = 1; j < size; j++) {
                    int v = firstSail.getCoordinates().getX();
                    int k = firstSail.getCoordinates().getY() + j;
                    boolean u = firstSail.isVertical();
                    Sail sail = new Sail(v, k, u);
                    sails.add(sail);
                    list.add(sail);
                    neighbords.addAll(sail.getNeighbords());
                }
            } else {
                for (int j = 1; j < size; j++) {
                    int v = firstSail.getCoordinates().getX() + j;
                    int k = firstSail.getCoordinates().getY();
                    boolean u = firstSail.isVertical();
                    Sail sail = new Sail(v, k, u);
                    sails.add(sail);
                    neighbords.addAll(sail.getNeighbords());
                }
            }
            ships.add(list);
        }
        return sails;
    }

    /*
     * random sail
     * @param int isVertical - вертикальный
     * @param int size - размер
     */
    private Sail randomStartSail(boolean isVertical, int size) {
        int randomX = MathUtils.random(1, 10);
        int randomY = MathUtils.random(1, 10);
        if (checkIfMeetAnotherShip(randomX, randomY, isVertical, size)) {
            return new Sail(randomX, randomY, isVertical);
        }
        return randomStartSail(isVertical(), size);
    }

    /*
     * Метод проверяет встречается ли корабль с другими и рядом ли будет его расположение
     */
    private boolean checkIfMeetAnotherShip(int x, int y, boolean isVertical, int size) {
        List<Coordinates> coordinatesList = new ArrayList<>();//инициализируем координаты
        List<Rectangle> rectangleList = new ArrayList<>();//инициализируем прямоугольник
        if (isVertical) {
            for (int i = 1; i <= size; i++) {
                coordinatesList.add(new Coordinates(x, y * i));
                rectangleList.add(new Rectangle(x, y * i, ScreenParams.cellW, ScreenParams.cellH));
            }
            coordinatesList.add(new Coordinates(x, y * size + 1));
            rectangleList.add(new Rectangle(x, y * size + 1, ScreenParams.cellW, ScreenParams.cellH));
        } else {
            for (int i = 1; i <= size; i++) {
                coordinatesList.add(new Coordinates(x * i, y));
                rectangleList.add(new Rectangle(x * i, y, ScreenParams.cellW, ScreenParams.cellH));
            }
        }
        //Проверяем соседей(запрещенные клетки)
        for (Coordinates coordinates : neighbords) {
            for (Coordinates coordinates1 : coordinatesList) {
                if (coordinates.equals(coordinates1)) {
                    return false;
                }
            }
            //Проверяем сталкиваются ли текстуры
            for (List<Sail> list : ships) {
                for (Sail sail : list) {
                    for (Rectangle rectangle : rectangleList) {
                        if (sail.getRectangle().overlaps(rectangle)) {
                            return false;
                        }
                    }
                }
            }
        }
        //если координаты больше 10
        for (Coordinates coordinates1 : coordinatesList) {
            if (coordinates1.getX() > 10 || coordinates1.getY() > 10) {
                return false;
            }
        }
        return true;
    }

    private boolean isVertical() {
        return new Random().nextBoolean();
    }
}
