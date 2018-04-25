package com.hhuuson97;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

class LoadingScreen {
    private Stage stage;
    private AssetManager assetManager;
    private ProgressBar progressBar;

    LoadingScreen(Stage stage, AssetManager assetManager, Skin skin) {
        this.stage = stage;
        this.assetManager = assetManager;
        progressBar = new ProgressBar(0, 1, 0.01f, false, skin);
        Table table = new Table();
        table.setFillParent(true);
        table.add(new Label("Please waiting ...", skin)).pad(5).row();
        table.add(progressBar).growX().padLeft(50).padRight(50);
        stage.addActor(table);
    }

    void render() {
        float percent = assetManager.getProgress();

        progressBar.setValue(percent);
    }

    void hide() {
        progressBar.setDisabled(true);
    }
}
