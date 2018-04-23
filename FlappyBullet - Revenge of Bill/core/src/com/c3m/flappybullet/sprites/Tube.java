package com.c3m.flappybullet.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Christian on 4/19/2018.
 */

public class Tube {
    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 600;
    private static final int TUBE_GAP = 400;
    private static final int LOWEST_OPENING = 200;
    private Texture topTube;
    private Vector2 posTopTube;
    private Rectangle boundsTop;
    private Random rand;

    public Tube(float x){
        topTube = new Texture("bird.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + 300);
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public void reposition(float x){
        posTopTube.set(x,rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        boundsTop.set(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
   }

   public boolean collides(Rectangle player){
        return  player.overlaps(boundsTop);
   }

   public void dispose(){
       topTube.dispose();
   }

}
