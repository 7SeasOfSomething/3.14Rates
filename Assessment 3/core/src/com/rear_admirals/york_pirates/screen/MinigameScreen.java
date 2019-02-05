package com.rear_admirals.york_pirates.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.rear_admirals.york_pirates.Department;
import com.rear_admirals.york_pirates.PirateGame;
import com.rear_admirals.york_pirates.Player;
import com.rear_admirals.york_pirates.base.BaseScreen;
import com.rear_admirals.york_pirates.minigame;

public class MinigameScreen extends BaseScreen {
    private Player player;
    private Department department;
    
    private Label goldLabel;
    private Label resultLabel;

    final minigame minigame = new minigame();

    public MinigameScreen(final PirateGame main, final Department department) {
        super(main);
        player = main.getPlayer();
        this.department = department;

        Table uiTable = new Table();

        Label goldTextLabel = new Label("Gold:", main.getSkin());
        goldLabel = new Label(Integer.toString(main.getPlayer().getGold()), main.getSkin());
        goldLabel.setAlignment(Align.left);

        uiTable.add(goldTextLabel);
        uiTable.add(goldLabel).width(goldTextLabel.getWidth());

        uiTable.align(Align.topRight);
        uiTable.setFillParent(true);

        uiStage.addActor(uiTable);

        Table bettingTable = new Table();
        bettingTable.setFillParent(true);

        Label title = new Label("Heads or Tails!", main.getSkin());
        final Label status = new Label("", main.getSkin());
        Label empty = new Label(" ", main.getSkin());

        final TextButton Heads = new TextButton("Bet 5 on Heads", main.getSkin());
        Heads.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (player.getGold() >= 5) {
                    if (minigame.flipCoin() == "h") {
                        status.setText("The Coin was Heads, You Win!");
                        player.setGold(player.getGold() + 5);
                    } else {
                        status.setText("The Coin was Tails, You Lose!");
                        player.setGold(player.getGold() - 5);
                    }
                } else {
                    status.setText("Not Enough Gold!");
                }
            }
        });

        final TextButton Tails = new TextButton("Bet 5 on Tails", main.getSkin());
        Tails.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if (player.getGold() >= 5) {
                    if (minigame.flipCoin() == "h") {
                        status.setText("The Coin was Heads, You Lose!");
                        player.setGold(player.getGold() - 5);
                    } else {
                        status.setText("The Coin was Tails, You Win!");
                        player.setGold(player.getGold() + 5);
                    }
                } else {
                    status.setText("Not Enough Gold!");
                }
            }
        });

        final TextButton Exit = new TextButton("Exit", main.getSkin());
        Exit.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                pirateGame.setScreen(new DepartmentScreen(pirateGame, department));
                dispose();
            }
        });

        bettingTable.add(title);
        bettingTable.row();
        bettingTable.add(status);
        bettingTable.row();
        bettingTable.add(Heads);
        bettingTable.add(Tails);
        bettingTable.row();
        bettingTable.add(empty);
        bettingTable.row();
        bettingTable.add(Exit);


        mainStage.addActor(bettingTable);

        Gdx.input.setInputProcessor(mainStage);
    }

    @Override
    public void update(float delta) {
        goldLabel.setText(Integer.toString(pirateGame.getPlayer().getGold()));
    }
}
