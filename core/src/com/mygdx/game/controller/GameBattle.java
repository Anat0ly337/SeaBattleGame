package com.mygdx.game.controller;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.dto.Coordinates;
import com.mygdx.game.model.Model;
import com.mygdx.game.model.Sail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GameBattle implements Serializable {
    static final long SerialVersionUID = -4862926644813433707L;

    private Map<Integer, Integer> shipMap;
    private List<Sail> sails;
    private Set<Coordinates> neighbords;

    public GameBattle() {
        sails = new ArrayList<>();
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

    private List<Sail> generateShips() {
        for (int i = 1; i <= shipMap.size(); i++) {
            sails.addAll(generateSails(i));
        }
        return sails;
    }

    private List<Sail> generateSails(int count) {
        List<Sail> sails = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int size = shipMap.get(count);
            Sail firstSail = randomStartSail(isVertical(), size);
            sails.add(firstSail);
            neighbords.addAll(firstSail.getNeighbords());

            if (firstSail.isVertical()) {
                for (int j = 1; j < size; j++) {
                    int v = firstSail.getCoordinates().getX();
                    int k = firstSail.getCoordinates().getY() + j;
                    boolean u = firstSail.isVertical();
                    Sail sail = new Sail(v, k, u);
                    sails.add(sail);
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
        }
        return sails;
    }

    private Sail randomStartSail(boolean isVertical, int size) {
        int randomX = MathUtils.random(1, 10);
        int randomY = MathUtils.random(1, 10);
        if (checkIfMeetAnotherShip(randomX, randomY, isVertical, size)) {
            return new Sail(randomX, randomY, isVertical);
        }
        return randomStartSail(isVertical(), size);
    }

    private boolean checkIfMeetAnotherShip(int x, int y, boolean isVertical, int size) {
        List<Coordinates> coordinatesList = new ArrayList<>();
        if (isVertical) {
            for (int i = 1; i <= size; i++) {
                coordinatesList.add(new Coordinates(x, y * i));
            }
        } else {
            for (int i = 1; i <= size; i++) {
                coordinatesList.add(new Coordinates(x * i, y));
            }
        }
        for (Coordinates coordinates : neighbords) {
            for (Coordinates coordinates1 : coordinatesList) {
                if (coordinates.equals(coordinates1)) {
                    return false;
                }
            }
        }


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
