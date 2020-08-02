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

   /* private void test() {
        int randomOne = MathUtils.random(0, 9);
        int randomTwo = MathUtils.random(0, 9);
        double startX = ScreenParams.cellsWidth - ScreenParams.cellsWidth * 0.935;
        double startY = ScreenParams.cellsHeight - ScreenParams.cellsHeight * 0.99;
        int randomX = (int) (startX + randomOne * ScreenParams.cellW);
        int randomY = (int) (startY + randomTwo * ScreenParams.cellW);
        Sail sail = new Sail((int) randomX, (int) randomY, (int) (ScreenParams.cellW * 0.99));
        models.add(sail);
        Rectangle rectangle = new Rectangle();
        rectangle.x = sail.getPosition().x;
        rectangle.y = sail.getPosition().y;
        rectangle.height = sail.getRectangle().height;
        rectangle.width = sail.getRectangle().width;

        for (Model graySquare : models) {
            if (graySquare instanceof GraySquare) {
                if (graySquare.getRectangle().overlaps(rectangle) || rectangle.overlaps(graySquare.getRectangle())) {
                    System.out.println("--------------------------------------------");
                }
            }
        }
    }*/

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        camera.update();
        sb.setShader(shader);
        int x = ScreenParams.screenHeight - ScreenParams.cellsHeight;
        int y = ScreenParams.cellsWidth;
        sb.begin();
        sb.draw(background, 0, 0, ScreenParams.screenWigth, ScreenParams.screenHeight);
        sb.draw(field, 0, x, y, ScreenParams.cellsHeight);
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

    }
}
