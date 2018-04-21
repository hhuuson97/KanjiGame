package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

class WellcomeScreen {

    private Stage stage;
    private Skin skin;
    private Table tableMenu;
    private Table tableSelectGame;

    private Main main;

    WellcomeScreen(Stage stage, Skin skin, Main main) {
        this.stage = stage;
        this.main = main;
        this.skin = skin;
    }

    private void gameTitle(Table table) {
        Label label = new Label("Kanji game", skin.get("big", Label.LabelStyle.class));
        table.add(label).padBottom(60);
        table.row();
    }

    private void createButton(String text, Skin skin, Table table, ChangeListener changeListener) {
        TextButton btn = new TextButton(text, skin);
        btn.addListener(changeListener);
        table.add(btn).padBottom(30);
        table.row();
    }

    private Table createTable(Stage stage) {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        return table;
    }

    void menuScreen() {
        stage.clear();
        tableMenu = createTable(stage);
        gameTitle(tableMenu);
        createButton("Start", skin, tableMenu, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                selectScreen();
            }
        });
        createButton("About", skin, tableMenu, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        createButton("Exit", skin, tableMenu, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        main.status = 0;
    }

    private void selectScreen() {
        stage.clear();
        tableSelectGame = createTable(stage);
        gameTitle(tableSelectGame);
        createButton("Jukugo", skin, tableSelectGame, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.jukugoJumble.selectScreen();
            }
        });
        createButton("Back", skin, tableSelectGame, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                menuScreen();
            }
        });
        main.status = 1;
    }
}
