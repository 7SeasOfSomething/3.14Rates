package com.rear_admirals.york_pirates;

import com.badlogic.gdx.graphics.Texture;

public class ShipType {
    private int attack;
    private int defence;
    private int accuracy;
    //Added For Assessment 3
    private int goldValue;
    private int pointValue;
    //End Added
    private String name;
    private Texture texture;

    //Altered For Assessment 3
    public ShipType(String name, int attack, int defence, int accuracy, int health, int goldValue, int pointValue) {
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.accuracy = accuracy;
        this.goldValue = goldValue;
        this.pointValue = pointValue;
        this.texture = new Texture("ship4.png"); //TESTING (without assets created)
    } // There is currently no way to give ships a custom texture. Do we need this?
    //End Altered

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getAccuracy() {
        return accuracy;
    }

    //Added For Assessment 3
    public int getGoldValue() {
        return goldValue;
    }

    public int getPointValue() {
        return pointValue;
    }
    //End Added

    public Texture getTexture() {
        return texture;
    }

    // Static Ship Types go here
    //Altered For Assessment 3
    //Player and encountered enemy ships
    public static ShipType Enemy = new ShipType("Schooner", 4, 4, 5, 80, 40, 40);
    public static ShipType Player = new ShipType("Brig", 5, 5, 5, 100, 60, 60);
    //Altered for Assessment 4
    //College Bosses
    public static ShipType James = new ShipType("Galleon", 6, 9, 5, 180, 80, 80);
    public static ShipType Van = new ShipType("Frigate", 9, 16, 5, 320, 100, 100);
    public static ShipType Good = new ShipType("Man o' War", 6, 20, 5, 320, 120, 120);
    public static ShipType Lan = new ShipType("Leviathan", 10, 23, 5, 450, 140, 140);
    //End Altered
    //End Altered
}