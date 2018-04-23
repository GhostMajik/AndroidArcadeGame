package com.c3m.flappybullet;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.c3m.flappybullet.States.GameStateManager;
import com.c3m.flappybullet.States.MenuState;
//Created by Colin Pugh, Cory Ronald, Matthew Gordan and Chris Lee
//Project uses Library LibGDX to assist with drawing objects on screen in a more familiar fashion, and easier import of sounds

public class FlappyBullet extends ApplicationAdapter {
	public static final int WIDTH = 1440;
	public static final int HEIGHT = 2880;

	public static final String TITLE = "Flappy Bullet";
	private GameStateManager gsm;
	private SpriteBatch batch;

	private Music music;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("fantasymusic.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));

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
		music.dispose();
	}
}
