package com.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

class KanjiSum {

    private Stage stage;
    private Table tableKanjiSum;
    private Skin glassy;
    private Skin sgx;
    private Skin golden;

    private Main main;

    KanjiSum(Stage stage, Skin glassy, Skin sgx, Skin golden, Main main) {
        this.stage = stage;
        this.main = main;
        this.glassy = glassy;
        this.sgx = sgx;
        this.golden = golden;
    }

    private ProgressBar createProgressBar(Skin skin, String name, Table table) {
        ProgressBar progressBar = new ProgressBar(0,100,1f, false, skin, name);
        progressBar.setValue(50);
        table.add(progressBar);
        return progressBar;
    }

    private Button createButton(String text, Skin skin, String name, Table table, ChangeListener changeListener) {
        TextButton btn = new TextButton(text, skin, name);
        btn.addListener(changeListener);
        if (name.equals("default"))
           table.add(btn).padBottom(30);
        else
            table.add(btn);
        table.row();
        return btn;
    }

    private Table createTable(Stage stage) {
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        return table;
    }

    void selectScreen() {
        stage.clear();
        tableKanjiSum = createTable(stage);
        createButton("Easy", glassy,"default", tableKanjiSum, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startmode();
            }
        });
        createButton("Medium", glassy,"default", tableKanjiSum, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
        createButton("Hard", glassy,"default", tableKanjiSum, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
        createButton("Extreme", glassy,"default", tableKanjiSum, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });
        createButton("Back", glassy,"default", tableKanjiSum, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.wellcomeScreen.menuScreen();
            }
        });
        main.status = 3;
    }

    private void startmode() {
        stage.clear();
        Table table = createTable(stage);
        table.debug();
        TiledDrawable tiledDrawable = golden.getTiledDrawable("progress-bar-life");
        tiledDrawable.setMinWidth(0f);
        golden.get("default-horizontal", ProgressBar.ProgressBarStyle.class).knobBefore = tiledDrawable;
        ProgressBar progressBar = createProgressBar(golden, "default-horizontal", table);
        table.getCell(progressBar).expandX().padLeft(10).left();
        Button btnPause = createButton("Pause", glassy, "small", table, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.wellcomeScreen.menuScreen();
            }
        });
        table.getCell(btnPause).padRight(10);
        table.row();

    }
}