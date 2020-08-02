/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.mygdx.game.view.GameState;
import com.mygdx.game.view.GameStateManager;
import com.mygdx.game.view.MenuState;
import com.mygdx.game.view.ScreenParams;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private GameStateManager gsm;
	private ShaderProgram shader;

	@Override
	public void create () {
		batch = new SpriteBatch();
		//параметры экрана
		ScreenParams.screenHeight = Gdx.graphics.getHeight();
		ScreenParams.screenWigth = Gdx.graphics.getWidth();
		ScreenParams.cellsWidth = ScreenParams.screenWigth;
		ScreenParams.cellsHeight = ScreenParams.screenWigth;
		ScreenParams.cellH = (int) ((ScreenParams.cellsHeight / 10) * 0.925);
		ScreenParams.cellW = (int) ((ScreenParams.cellsWidth / 10) * 0.94);
		//создание шедера
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(Gdx.files.internal("shaders/default.vert"),
				(Gdx.files.internal("shaders/invertColors.frag")));
		if (!shader.isCompiled()) {
			System.err.println(shader.getLog());
			System.exit(0);
		}
		gsm = new GameStateManager();
		gsm.push(new MenuState(gsm,shader));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}
