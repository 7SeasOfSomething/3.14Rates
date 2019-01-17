package display;

import banks.WeaponBank;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import combat.items.Weapon;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class departmentScreen implements Screen {
    //private Game game = new GameManager(null, null);

    private SpriteBatch batch = new SpriteBatch();
    private Stage stage = new Stage();

    private TextButton toMenu;
    private BitmapFont buttonFont = new BitmapFont();
    private TextButton.TextButtonStyle myTextButtonStyle = new TextButton.TextButtonStyle();
    private TextureAtlas menuButtonAtlas = new TextureAtlas("buttonSpriteSheet.txt");
    private Skin skin = new Skin();

    private Texture departmentBackground = new Texture("battleBackground.png");
    //private Texture shopBackground = new Texture();

    private Texture hpBackground = new Texture("background.png");
    private Texture hpDisabledBackground = new Texture("disabledBackground.png");
    private ProgressBar.ProgressBarStyle healthBarStyle = new ProgressBar.ProgressBarStyle();
    private ProgressBar healthBar;

    private int score;
    private String scoreName;
    private int gold;
    private String goldName;
    private int crew;
    private String crewName;
    private int food;
    private String foodName;
    private BitmapFont indicatorFont = new BitmapFont();

    private Texture shipBackground = new Texture("shipBackground.png");
    private Sprite playerShipBackground = new Sprite(shipBackground);

    private TextureAtlas buttonAtlas = new TextureAtlas("roomSpriteSheet.txt");
    private Sprite friendlyCrewQuaters = buttonAtlas.createSprite("crewQuaters");
    private Sprite friendlyCrowsNest = buttonAtlas.createSprite("crowsNest");
    private Sprite friendlyGunDeck = buttonAtlas.createSprite("gunDeck");
    private Sprite friendlyHelm = buttonAtlas.createSprite("helm");
    private Sprite friendlyEmptyRoom1 = buttonAtlas.createSprite("EmptyRoom");
    private Sprite friendlyEmptyRoom2 = buttonAtlas.createSprite("EmptyRoom");
    private Sprite friendlyEmptyRoom3 = buttonAtlas.createSprite("EmptyRoom");
    private Sprite friendlyEmptyRoom4 = buttonAtlas.createSprite("EmptyRoom");

    private Sprite computerScience = new Sprite (new Texture("ComputerScIsland.png"));
    private Sprite lawAndManagment = new Sprite (new Texture("LMI.png"));
    private int randDepartment;

    private TextButton openShop;
    private Sprite shopBackground = new Sprite (new Texture("shopBackground.png"));

    private BitmapFont weaponTitleFont = new BitmapFont();
    private BitmapFont weaponDetailsFont = new BitmapFont();

    private List<Weapon> weapons = new ArrayList<Weapon>();
    private TextButton buyWeapon1;
    private TextButton buyWeapon2;
    private TextButton buyWeapon3;
    private int randWeapon1;
    private int randWeapon2;


    @Override
    public void show() {
        weapons.add(WeaponBank.SEPR.getWeapon());
        weapons.add(WeaponBank.LAWBRINGER.getWeapon());
        weapons.add(WeaponBank.CRITTER.getWeapon());
        weapons.add(WeaponBank.STORM.getWeapon());
        weapons.add(WeaponBank.BOOM.getWeapon());
        weapons.add(WeaponBank.GATLING.getWeapon());
        weapons.add(WeaponBank.MORTAR.getWeapon());
        weapons.add(WeaponBank.SCATTER.getWeapon());
        weapons.add(WeaponBank.TREB.getWeapon());
        weapons.add(WeaponBank.WATER.getWeapon());
        weapons.add(WeaponBank.WIN.getWeapon());

        skin.addRegions(menuButtonAtlas);
        myTextButtonStyle.font = buttonFont;
        myTextButtonStyle.up = skin.getDrawable("buttonUp");
        myTextButtonStyle.down = skin.getDrawable("buttonDown");

        //TODO switching back to main menu doesnt work
        toMenu = new TextButton("To Menu", myTextButtonStyle);
        toMenu.setPosition(880,980);
        /*toMenu.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new menuScreen(game));
                return true;
            }
        });*/
        stage.addActor(toMenu);

        healthBarStyle.background = new TextureRegionDrawable(new TextureRegion(hpBackground));
        healthBarStyle.disabledBackground = new TextureRegionDrawable(new TextureRegion(hpDisabledBackground));
        healthBar = new ProgressBar(0,1000,1,false, healthBarStyle);
        healthBar.setValue(500);
        healthBar.setWidth(256);
        healthBar.setHeight(64);
        healthBar.setPosition(50,950);

        stage.addActor(healthBar);

        score = 0;
        scoreName = "Score: 0";

        food = 0;
        foodName = "Food: 0";

        gold = 0;
        goldName = "Gold: 0";

        crew = 0;
        crewName = "Crew: 0";

        playerShipBackground.setPosition(100,256);
        friendlyCrewQuaters.setPosition(100,256);
        friendlyCrowsNest.setPosition(228,256);
        friendlyEmptyRoom1.setPosition(100,384);
        friendlyEmptyRoom2.setPosition(228,384);
        friendlyGunDeck.setPosition(100,512);
        friendlyEmptyRoom3.setPosition(228,512);
        friendlyEmptyRoom4.setPosition(100,640);
        friendlyHelm.setPosition(228,640);

        Random rand = new Random();
        randDepartment = rand.nextInt(2);

        openShop = new TextButton("Shop", myTextButtonStyle);
        openShop.setPosition(350,960);
        openShop.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                shopBackground.setAlpha(0.85f);
                weaponTitleFont.setColor(1,1,1,1);
                weaponDetailsFont.setColor(1,1,1,1);
                buyWeapon1.setColor(1,1,1,1f);
                buyWeapon2.setColor(1,1,1,1f);
                buyWeapon3.setColor(1,1,1,1f);
                return true;
            }
        });
        stage.addActor(openShop);

        shopBackground.setScale(1.5f,1.5f);
        shopBackground.setAlpha(0);
        shopBackground.setPosition(256,256);

        weaponTitleFont.setColor(1f,1f,1f,0f);
        weaponTitleFont.getData().setScale(1.5f);
        weaponDetailsFont.setColor(1f,1f,1f,0f);
        weaponDetailsFont.getData().setScale(1f);

        randWeapon1 = rand.nextInt(9) + 2;
        randWeapon2 = rand.nextInt(9) + 2;

        buyWeapon1 = new TextButton("Buy " + weapons.get(randDepartment).getName(), myTextButtonStyle);
        buyWeapon1.setPosition(160, 740);
        stage.addActor(buyWeapon1);
        buyWeapon1.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                if (gold <= weapons.get(randDepartment).getCost()){
                    buyWeapon1.setDisabled(true);
                } else {

                }
                return true;
            }
        });

        buyWeapon2 = new TextButton("Buy " + weapons.get(randWeapon1).getName(), myTextButtonStyle);
        buyWeapon2.setPosition(160, 590);
        stage.addActor(buyWeapon2);
        buyWeapon2.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                return true;
            }
        });

        buyWeapon3 = new TextButton("Buy " + weapons.get(randWeapon2).getName(), myTextButtonStyle);
        buyWeapon3.setPosition(160, 450);
        stage.addActor(buyWeapon3);
        buyWeapon3.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){

                return true;
            }
        });

        buyWeapon1.setColor(1,1,1,0f);
        buyWeapon2.setColor(1,1,1,0f);
        buyWeapon3.setColor(1,1,1,0f);
        }

    @Override
    public void render(float delta) {
        Gdx.input.setInputProcessor(stage);

        batch.begin();

        batch.draw(departmentBackground,0,0);

        friendlyCrewQuaters.draw(batch);
        friendlyCrowsNest.draw(batch);
        friendlyEmptyRoom1.draw(batch);
        friendlyEmptyRoom2.draw(batch);
        friendlyGunDeck.draw(batch);
        friendlyEmptyRoom3.draw(batch);
        friendlyEmptyRoom4.draw(batch);
        friendlyHelm.draw(batch);

        //TODO fix department randomisation (if rand is 1, it gets reset to 0)
        switch (randDepartment) {
            case 0:
                computerScience.draw(batch);
                computerScience.setPosition(500,256);

                shopBackground.draw(batch);

                weaponTitleFont.draw(batch, weapons.get(0).getName(), 160,880);
                weaponDetailsFont.draw(batch, "Damage: " + weapons.get(0).getBaseDamage(), 160, 850);
                weaponDetailsFont.draw(batch, "Cooldown: " + weapons.get(0).getCurrentCooldown(), 160,830);
                weaponDetailsFont.draw(batch, "Crit Chance: " + weapons.get(0).getBaseCritChance(), 160, 810);
                weaponDetailsFont.draw(batch, "Hit Chance: " + weapons.get(0).getBaseChanceToHit(), 160, 790);
                break;
            case 1:
                lawAndManagment.draw(batch);
                lawAndManagment.setPosition(500,256);

                shopBackground.draw(batch);

                weaponTitleFont.draw(batch, weapons.get(1).getName(), 160,880);
                weaponDetailsFont.draw(batch, "Damage: " + weapons.get(1).getBaseDamage(), 160, 850);
                weaponDetailsFont.draw(batch, "Cooldown: " + weapons.get(1).getCurrentCooldown(), 160,830);
                weaponDetailsFont.draw(batch, "Crit Chance: " + weapons.get(1).getBaseCritChance(), 160, 810);
                weaponDetailsFont.draw(batch, "Hit Chance: " + weapons.get(1).getBaseChanceToHit(), 160, 790);
                break;
        }

        indicatorFont.setColor(1f,1f,1f,1f);
        indicatorFont.draw(batch, scoreName, 55, 970);
        indicatorFont.draw(batch, goldName, 120, 970);
        indicatorFont.draw(batch, crewName, 185, 970);
        indicatorFont.draw(batch, foodName, 250, 970);

        System.out.print(randWeapon1);
        weaponTitleFont.draw(batch, weapons.get(randWeapon1).getName(), 160,730);
        weaponDetailsFont.draw(batch, "Damage: " + weapons.get(randWeapon1).getBaseDamage(), 160, 700);
        weaponDetailsFont.draw(batch, "Cooldown: " + weapons.get(randWeapon1).getCurrentCooldown(), 160,680);
        weaponDetailsFont.draw(batch, "Crit Chance: " + weapons.get(randWeapon1).getBaseCritChance(), 160, 660);
        weaponDetailsFont.draw(batch, "Hit Chance: " + weapons.get(randWeapon1).getBaseChanceToHit(), 160, 640);

        weaponTitleFont.draw(batch, weapons.get(randWeapon2).getName(), 160,580);
        weaponDetailsFont.draw(batch, "Damage: " + weapons.get(randWeapon2).getBaseDamage(), 160, 560);
        weaponDetailsFont.draw(batch, "Cooldown: " + weapons.get(randWeapon2).getCurrentCooldown(), 160,540);
        weaponDetailsFont.draw(batch, "Crit Chance: " + weapons.get(randWeapon2).getBaseCritChance(), 160, 520);
        weaponDetailsFont.draw(batch, "Hit Chance: " + weapons.get(randWeapon2).getBaseChanceToHit(), 160, 500);

        batch.end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
