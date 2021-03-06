package com.c3m.flappybullet.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Christian on 4/19/2018.
 */

public class Bird {
    private static final int GRAVITY = -50;
    private static final int MOVEMENT = 100;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;

    private Texture bird;

    public Bird(int x, int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bullet2.png");
        bounds = new Rectangle(x, y, bird.getWidth(), bird.getHeight()/2);
    }

    public void update(float dt){
        if(position.y > 0)
            velocity.add(0,GRAVITY/2,0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y,0);




        velocity.scl(1/dt);
        bounds.setPosition(position.x,position.y);

    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }

    public void jump(){
        velocity.y = 500;
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        bird.dispose();
    }

}
