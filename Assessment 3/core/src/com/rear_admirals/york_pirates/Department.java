package com.rear_admirals.york_pirates;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class Department {

    private final String name;
    private String product;
    private int base_price;
    private PirateGame pirateGame;
    private int upgradeCounter; //Added for Assessment 4

    public Department(String name, String product, PirateGame pirateGame) {
        this.name = name;
        this.product = product;
        this.base_price = 10;
        this.pirateGame = pirateGame;
        this.upgradeCounter = 0;
    }

    //Altered For Assessment 3

    /**
     * Upgrade ship by spending gold.
     * @return - Boolean, purchase successful
     */
    public boolean purchase() {
        if (pirateGame.getPlayer().payGold(getPrice())) {
            Ship playerShip = pirateGame.getPlayer().getPlayerShip();
            //Added for Assessment 4
            upgradeCounter += 1;
            //End Altered
            if (product == "Defence") {
                playerShip.setDefence(playerShip.getDefence() + 1);
                return true;
            } else if (product == "Attack") {
                playerShip.setAttack(playerShip.getAttack() + 1);
                return true;
            }
        }return false;}

    /**
     * Get the price of an upgrade from this department, taking into account current upgrade level
     * @return price of upgrade
     */

    //Altered for Assessment 4
    public int getPrice() {
        if (product == "Defence") {
            return (int) (base_price * pow(2, upgradeCounter));
        } else if (product == "Attack") {
            return (int) (base_price * pow(2, upgradeCounter));
        } else {
            throw new IllegalArgumentException("Invalid Department Product");
        }
    }
    //End Altered (Assessment 4)
    //End Altered

    public String getName() {
        return name;
    }

    public String getProduct() {
        return product;
    }
}