package other;

import banks.ShipBank;
import combat.ship.Ship;

public class Constants {
    /**
     * The reduction applied to the value of an item when a store buys it from you. Eg. if this is 0.5 and the item
     * costs 100, the shop will offer you 50.
     */
    public static final double STORE_SELL_PRICE_MULTIPLIER = 0.66;
    public static final int DEFAULT_SHIP_HP = 1000;
    public static final int DEFAULT_SHIP_CREW = 25;
    public static final int DEFAULT_UPGRADE_COST = 100;
    public static final int DEFAULT_WEAPON_COST = 100;
    public static final int DEFAULT_WEAPON_DAMAGE = 100;
    public static final int DEFAULT_WEAPON_COOLDOWN = 10000;
    public static final double DEFAULT_WEAPON_CRIT_CHANCE = 0.2;
    public static final double DEFAULT_WEAPON_HIT_CHANCE = 0.9;
    public static final int STARTING_GOLD = 250;
    public static final int STARTING_FOOD = 100;
    public static final ShipBank STARTING_SHIP = ShipBank.STARTER_SHIP;
}