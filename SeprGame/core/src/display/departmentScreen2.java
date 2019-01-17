package display;

import banks.CoordBank;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import combat.items.Weapon;
import combat.ship.Ship;
import game_manager.GameManager;
import location.Department;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static banks.RoomUpgradeSetBank.COMP_SCI_UPGRADES;
import static banks.RoomUpgradeSetBank.LMB_UPGRADES;
import static banks.WeaponSetBank.COMP_SCI_WEPS;
import static banks.WeaponSetBank.LMB_WEPS;
import static other.Constants.STORE_SELL_PRICE_MULTIPLIER;

public class departmentScreen2 implements Screen {
    private GameManager game = new GameManager(null, null);

    private SpriteBatch batch = new SpriteBatch();
    private Stage stage = new Stage();

    private int randInt = pickRandom(2);
    private Department department = assignDepartment(randInt);

    private Ship playerShip = game.getPlayerShip();

    private BitmapFont buttonFont = new BitmapFont();
    private TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    private TextureAtlas buttonAtlas;
    private Skin skin = new Skin();

    private TextButton showShop; //May be able to remove showShop and Menu from here.
    private TextButton toMenu;

    @Override
    public void show() {
        buttonAtlas = new TextureAtlas("buttonSpriteSheet.txt");
        skin.addRegions(buttonAtlas);

        textButtonStyle.font = buttonFont;
        textButtonStyle.up = skin.getDrawable("buttonUp");
        textButtonStyle.down = skin.getDrawable("buttonDown");

        showShop = buttonShowShop(textButtonStyle);
        toMenu = buttonToMenu(textButtonStyle);
    }

    @Override
    public void render(float delta) {

        batch.begin();

        drawBackground();
        drawFriendlyShip();
        drawDepartment(randInt);

        drawHealthBar();
        drawIndicators();

        batch.end();

        stage.draw();
        stage.act();
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

    public int pickRandom(int max) {
        Random rand = new Random();
        int randInt = rand.nextInt(max);

        return randInt;
    }

    public void drawBackground() {
        Texture background = new Texture("battleBackground.png");
        batch.draw(background, 0, 0);
    }

    public void drawFriendlyShip(){
        TextureAtlas roomSpriteAtlas = new TextureAtlas("roomSpriteSheet.txt");

        Sprite friendlyCrewQuaters = roomSpriteAtlas.createSprite("crewQuaters");
        friendlyCrewQuaters.setPosition(CoordBank.FRIENDLY_ROOM1.getX(),CoordBank.FRIENDLY_ROOM1.getY());

        Sprite friendlyEmptyRoom1 = roomSpriteAtlas.createSprite("EmptyRoom");
        friendlyEmptyRoom1.setPosition(CoordBank.FRIENDLY_ROOM2.getX(),CoordBank.FRIENDLY_ROOM2.getY());

        Sprite friendlyCrowsNest = roomSpriteAtlas.createSprite("crowsNest");
        friendlyCrowsNest.setPosition(CoordBank.FRIENDLY_ROOM3.getX(),CoordBank.FRIENDLY_ROOM3.getY());

        Sprite friendlyGunDeck = roomSpriteAtlas.createSprite("gunDeck");
        friendlyGunDeck.setPosition(CoordBank.FRIENDLY_ROOM4.getX(),CoordBank.FRIENDLY_ROOM4.getY());

        Sprite friendlyEmptyRoom2 = roomSpriteAtlas.createSprite("EmptyRoom");
        friendlyEmptyRoom2.setPosition(CoordBank.FRIENDLY_ROOM5.getX(),CoordBank.FRIENDLY_ROOM5.getY());

        Sprite friendlyHelm = roomSpriteAtlas.createSprite("helm");
        friendlyHelm.setPosition(CoordBank.FRIENDLY_ROOM6.getX(),CoordBank.FRIENDLY_ROOM6.getY());

        Sprite friendlyEmptyRoom3 = roomSpriteAtlas.createSprite("EmptyRoom");
        friendlyEmptyRoom3.setPosition(CoordBank.FRIENDLY_ROOM7.getX(),CoordBank.FRIENDLY_ROOM7.getY());

        Sprite friendlyEmptyRoom4 = roomSpriteAtlas.createSprite("EmptyRoom");
        friendlyEmptyRoom4.setPosition(CoordBank.FRIENDLY_ROOM8.getX(),CoordBank.FRIENDLY_ROOM8.getY());

        friendlyCrewQuaters.draw(batch);
        friendlyCrowsNest.draw(batch);
        friendlyGunDeck.draw(batch);
        friendlyHelm.draw(batch);
        friendlyEmptyRoom1.draw(batch);
        friendlyEmptyRoom2.draw(batch);
        friendlyEmptyRoom3.draw(batch);
        friendlyEmptyRoom4.draw(batch);
    }

    public Department assignDepartment(int randInt) {
        switch (randInt) {
            case 1:
                return (new Department(COMP_SCI_WEPS.getWeaponList(), COMP_SCI_UPGRADES.getRoomUpgradeList(), game));
            case 2:
                return (new Department(LMB_WEPS.getWeaponList(), LMB_UPGRADES.getRoomUpgradeList(), game));
        }
        return null;
    }

    public void drawDepartment(int randInt) {
        switch (randInt) {
            case 0:
                Texture computerScienceTexture = new Texture("ComputerScIsland.png");
                Sprite computerScienceSprite = new Sprite(computerScienceTexture);
                batch.draw(computerScienceSprite,500,256);
                break;
            case 1:
                Texture lawAndManagementTexture = new Texture("LMI.png");
                Sprite lawAndManagementSprite = new Sprite(lawAndManagementTexture);
                batch.draw(lawAndManagementSprite,400,200);
                break;
        }
    }

    public void drawHealthBar() {
        Texture hpBackground = new Texture("background.png");
        Texture hpDisabledBackground = new Texture("disabledBackground.png");

        ProgressBar.ProgressBarStyle hpBarStyle = new ProgressBar.ProgressBarStyle();
        hpBarStyle.background = new TextureRegionDrawable( new TextureRegion(hpBackground));
        hpBarStyle.disabledBackground = new TextureRegionDrawable( new TextureRegion(hpDisabledBackground));

        ProgressBar hpBar = new ProgressBar(0,1000,10,false,hpBarStyle);
        hpBar.setWidth(320);
        hpBar.setHeight(64);
        hpBar.setPosition(25,950);

        stage.addActor(hpBar);

        hpBar.setValue(500);
    } //FIXME

    public void drawIndicators(){
        BitmapFont indicatorFont = new BitmapFont();
        indicatorFont.setColor(1,1,1,1);

        indicatorFont.draw(batch, "Score: " + game.getPoints(), 25, 965);
        indicatorFont.draw(batch, "Gold: " + game.getGold(), 110, 965);
        indicatorFont.draw(batch, "Food: " + game.getFood(), 195, 965);
        indicatorFont.draw(batch, "Crew: " + playerShip.getCrew(), 280, 965);
    }

    public TextButton buttonShowShop(TextButton.TextButtonStyle textButtonStyle) {
      TextButton showShop = new TextButton("Open Shop", textButtonStyle);
      showShop.setPosition(350, 960);
      showShop.addListener(new InputListener() {
          public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //TODO Show/Hide Shop
              return true;
          }
      });
      return showShop;
    }

    public TextButton buttonToMenu(TextButton.TextButtonStyle textButtonStyle){
        TextButton toMenu = new TextButton("To Menu", textButtonStyle);
        showShop.setPosition(880, 980);
        showShop.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.getGame().setScreen(new menuScreen(game));
                return true;
            }
        });
        return toMenu;
    }

}

