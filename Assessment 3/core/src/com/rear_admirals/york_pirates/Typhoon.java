//All new additions for Assessment 4.

package com.rear_admirals.york_pirates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rear_admirals.york_pirates.base.PhysicsActor;


/**
 * Added for Assessment 4.
 * - A physics actor that is randomly placed around the world map
 *   which negatively affects the player if they are overlapping the object.
 */
public class Typhoon extends PhysicsActor {

    private Texture typhoonTexture;

    public Typhoon(){
        typhoonTexture = new Texture("typhoon.png");

        //sets up PhysicsActor data
        this.setWidth(this.typhoonTexture.getWidth());
        this.setHeight(this.typhoonTexture.getHeight());
        this.setOriginCentre();
        this.setMaxSpeed(200);
        this.setDeceleration(20);
        this.setEllipseBoundary();
    }

    @Override
    public void draw(Batch batch, float alpha){
        batch.setColor(1,1,1,alpha);
        batch.draw(new TextureRegion(typhoonTexture), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), 1, 1, getRotation());

    }

    public void setTyphoonTexture(Texture in){
        typhoonTexture = in;
    }

    public Texture getTyphoonTexture(){
        return typhoonTexture;
    }

}
