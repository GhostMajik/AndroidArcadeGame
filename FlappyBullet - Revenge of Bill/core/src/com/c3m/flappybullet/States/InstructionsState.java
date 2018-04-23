package com.c3m.flappybullet.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.c3m.flappybullet.FlappyBullet;

/**
 * Created by Chris and Colin on 4/20/2018.
 */

public class InstructionsState extends State {
    private Texture background;
    private Texture playBtn;
    private Texture menuImage;
    private BitmapFont font;
    private Sound pressedSFX;

    public InstructionsState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBullet.WIDTH/2,FlappyBullet.HEIGHT/2);
        background = new Texture("background.png");
        playBtn = new Texture("playbtn.png");
        menuImage = new Texture("instructions.png");
        pressedSFX = Gdx.audio.newSound(Gdx.files.internal("button.mp3"));
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3,3);

    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            pressedSFX.play(0.5f);
            gsm.set(new PlayState(gsm));
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
        sb.draw(playBtn,250,cam.viewportHeight/2 - 100, playBtn.getWidth() * 2, playBtn.getHeight() * 2);
        sb.draw(menuImage, 125, cam.viewportHeight /2 + 400 );
        font.draw(sb,"Tap the screen to go up. \n\n Each bird hit is 1 point. \n\n Hit the clouds or the \n ground and it's gameover! ",100, cam.viewportHeight/2 + 400);
        sb.end();

    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        menuImage.dispose();
    }
}
