package com.c3m.flappybullet.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.c3m.flappybullet.FlappyBullet;

import sun.audio.AudioPlayer;

/**
 * Created by Chris and Colin on 4/20/2018.
 */

public class GameOverState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture gameOverImage;
    private BitmapFont font;
    private Sound pressedSFX;

    public GameOverState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBullet.WIDTH/2,FlappyBullet.HEIGHT/2);
        background = new Texture("background.png");
        playBtn = new Texture("playbtn.png");
        gameOverImage = new Texture("gameover.png");
        pressedSFX = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3,3);

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            pressedSFX.play(0.5f);
            gsm.set(new MenuState(gsm));
            dispose();

        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth/2),0, FlappyBullet.WIDTH, FlappyBullet.HEIGHT/2);
        sb.draw(playBtn,250,cam.viewportHeight/2, playBtn.getWidth() * 2,playBtn.getHeight()* 2);
        sb.draw(gameOverImage, 175, cam.viewportHeight /2 + 300, gameOverImage.getWidth()* 2,gameOverImage.getHeight() * 2 );
        if(PlayState.score < 25)
            font.draw(sb,"Score: " + PlayState.score, 270, cam.viewportHeight/2 + 200 );
        else if(PlayState.score >=25 && PlayState.score <50)
            font.draw(sb,"Well Done! \n Score: " + PlayState.score, 270, cam.viewportHeight/2 + 250 );
        else
            font.draw(sb,"K... \n Score: " + PlayState.score, 270, cam.viewportHeight/2 + 250 );
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        gameOverImage.dispose();
    }
}
