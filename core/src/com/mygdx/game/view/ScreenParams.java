package com.mygdx.game.view;

public class ScreenParams {
    public static int screenHeight;
    public static int screenWigth;
    public static int cellsHeight;
    public static int cellsWidth;
    public static int cellH;
    public static int cellW;

    static {
        cellsWidth = screenWigth;
        cellsHeight = screenWigth;

        cellH = (int) ((cellsHeight / 10) * 0.925);
        cellW = (int) ((cellsWidth / 10) * 0.94);
    }

}
