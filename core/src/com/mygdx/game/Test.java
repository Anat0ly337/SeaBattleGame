package com.mygdx.game;

import com.mygdx.game.dto.Coordinates;

public class Test {
    public static void main(String[] args) {
        Coordinates coordinates = new Coordinates(3,4);
        Coordinates coordinates1 = new Coordinates(2,4);
        System.out.println(coordinates.equals(coordinates1));
    }
}
