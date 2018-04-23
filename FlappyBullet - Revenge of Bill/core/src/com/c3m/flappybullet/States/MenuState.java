package com.c3m.flappybullet.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.c3m.flappybullet.FlappyBullet;

/**
 * Created by Matt and Colin on 2018-04-19.
 */

public class MenuState extends State{
    private Texture background;
    private Texture playBtn;
    private Texture title;
    private Texture logo;
    private Sound pressedSFX;
    private BitmapFont font;
    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBullet.WIDTH/2,FlappyBullet.HEIGHT/2);
        background = new Texture("background.png");
        playBtn = new Texture("playbtn.png");
        title = new Texture("titleImage.png");
        logo = new Texture("logo.png");
        pressedSFX = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(3,3);

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            pressedSFX.play(0.5f);
            gsm.set(new InstructionsState(gsm));
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
        sb.draw(playBtn,250,cam.viewportHeight/2, playBtn.getWidth() * 2, playBtn.getHeight() * 2);
        sb.draw(title, 140, cam.viewportHeight/2 + 200 , title.getWidth() * 1, title.getHeight() * 2);
        sb.draw(logo,200,cam.viewportHeight/20,logo.getWidth()/4,logo.getHeight()/4);
        font.draw(sb,"Games",275,cam.viewportHeight/20 + 50);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
