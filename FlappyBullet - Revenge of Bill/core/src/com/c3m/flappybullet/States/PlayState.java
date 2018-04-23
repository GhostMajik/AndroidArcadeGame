package com.c3m.flappybullet.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.c3m.flappybullet.FlappyBullet;
import com.c3m.flappybullet.sprites.Bird;
import com.c3m.flappybullet.sprites.Tube;

import java.util.Random;

/**
 * Created by Matt and Colin on 2018-04-19.
 */

public class PlayState extends State {
    private static final int TUBE_SPACING = 250;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture bg;
    private Texture cloud;
    private Vector2 cloudPos1, cloudPos2, cloudPos3;
    private BitmapFont font;
    public static int score;
    private Sound squishSFX;
    private Sound deathSFX;
    private Sound jumpSFX;
    private Random random;

    private Array<Tube> tubes;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(280, 700);
        cam.setToOrtho(false, FlappyBullet.WIDTH/2,FlappyBullet.HEIGHT/2);
        bg = new Texture("background.png");
        cloud = new Texture("NewCloud.png");
        cloudPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, FlappyBullet.HEIGHT - 1650);
        cloudPos2 = new Vector2((cam.position.x - cam.viewportWidth/2) + cloud.getWidth(), FlappyBullet.HEIGHT - 1650);
        cloudPos3 = new Vector2((cam.position.x - cam.viewportWidth/2) + cloud.getWidth() * 2, FlappyBullet.HEIGHT - 1650);
        score = 0;
        squishSFX = Gdx.audio.newSound(Gdx.files.internal("splat.wav"));
        deathSFX = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
        jumpSFX = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(3,3);
        random = new Random();

        tubes = new Array<Tube>();

        for(int i = 1; i <= TUBE_COUNT; i++){
            tubes.add(new Tube(200 + i*(random.nextInt(TUBE_SPACING) + Tube.TUBE_WIDTH * 3)));
        }

    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            bird.jump();
            jumpSFX.play(0.5f);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateCloud();
        bird.update(dt);
        cam.position.x = bird.getPosition().x + 80;

        if(bird.getPosition().y > FlappyBullet.HEIGHT - 1690 || bird.getPosition().y < 250){
            gsm.set(new GameOverState(gsm));
            deathSFX.play();
        }

        for(Tube tube : tubes){
            if(cam.position.x - (cam.viewportWidth/2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
                score++;
                squishSFX.play();
            }
        }



        cam.update();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth/2),0, FlappyBullet.WIDTH, FlappyBullet.HEIGHT/2);
        sb.draw(bird.getTexture(),bird.getPosition().x,bird.getPosition().y);
        for(Tube tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y, tube.getTopTube().getWidth() * 2, tube.getTopTube().getHeight() * 2);
        }
        sb.draw(cloud, cloudPos1.x, cloudPos1.y);
        sb.draw(cloud, cloudPos2.x, cloudPos2.y);
        sb.draw(cloud, cloudPos3.x, cloudPos3.y);
        font.draw(sb,"Score: " + score, bird.getPosition().x - FlappyBullet.WIDTH/6,150 );
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        cloud.dispose();
        squishSFX.dispose();
        for(Tube tube : tubes)
            tube.dispose();
    }

    private void updateCloud(){
        if(cam.position.x - (cam.viewportWidth/2) > cloudPos1.x + cloud.getWidth())
            cloudPos1.add(cloud.getWidth() * 3,0);
        if(cam.position.x - (cam.viewportWidth/2) > cloudPos2.x + cloud.getWidth())
            cloudPos2.add(cloud.getWidth() * 3,0);
        if(cam.position.x - (cam.viewportWidth/2) > cloudPos3.x + cloud.getWidth())
            cloudPos3.add(cloud.getWidth() * 3, 0);
    }

}
