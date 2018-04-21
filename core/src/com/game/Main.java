package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class Main extends ApplicationAdapter {
	private static final String[] gradeName = {"firstGrade", "secondGrade", "thirdGrade", "fourthGrade", "fifthGrade", "sixthGrade"};

	private SpriteBatch batch;
	BitmapFont simsunFont;
	BitmapFont simsunFontSmall;
	private BitmapFont bitmapFont;
	private Stage stage;
	private AssetManager assetManager;
	private Skin glassy;

	int status;
	private String radicals;
	private String[] kanjiGrade;
	String[] kunGrade;
	String hiragana;
	private boolean loadingDone;

	private LoadingScreen loadingScreen;
	WellcomeScreen wellcomeScreen;
	KanjiSum kanjiSum;
	JukugoJumble jukugoJumble;

	@Override
	public void create () {
		batch = new SpriteBatch();
		stage = new Stage();
		stage.setActionsRequestRendering(true);
		Gdx.input.setInputProcessor(stage);

		batch = new SpriteBatch();
		bitmapFont = new BitmapFont();

		radicals = Gdx.files.internal("radicals.txt").readString();
		hiragana = Gdx.files.internal("hiragana.txt").readString();
		kanjiGrade = new String[gradeName.length];
		kunGrade = new String[gradeName.length];
		for (int i = 0; i < gradeName.length; i++) {
			kanjiGrade[i] = Gdx.files.internal(gradeName[i] + "/kanji.txt").readString();
			kunGrade[i] = Gdx.files.internal(gradeName[i] + "/kun.txt").readString();
		}

		assetManager = new AssetManager();
		assetManager.load("simsun/simsun.fnt", BitmapFont.class);
		assetManager.load("sgx/sgx-ui.json", Skin.class);
		assetManager.load("golden-ui/golden-ui-skin.json", Skin.class);
		glassy = new Skin(Gdx.files.internal("glassy/glassy-ui.json"));

		loadingScreen = new LoadingScreen(stage, assetManager, glassy);
		loadingDone = false;
	}

	private void onAssetUpdate() {
		simsunFontSmall = assetManager.get("simsun/simsun.fnt", BitmapFont.class);
		simsunFont = new BitmapFont(simsunFontSmall.getData().fontFile);
		simsunFont.getData().setScale(2);
		Skin sgx = assetManager.get("sgx/sgx-ui.json", Skin.class);
		Skin golden = assetManager.get("golden-ui/golden-ui-skin.json", Skin.class);
		wellcomeScreen = new WellcomeScreen(stage, glassy, this);
		wellcomeScreen.menuScreen();
		kanjiSum = new KanjiSum(stage, glassy, sgx, golden, this);
		jukugoJumble = new JukugoJumble(stage, glassy, sgx, golden, this);
		loadingScreen.hide();
		loadingDone = true;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		if (assetManager.update() && !loadingDone) onAssetUpdate();
		VectorGradient.getInstance().rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Color.WHITE, Color.NAVY);
		if (!loadingDone) loadingScreen.render();
		stage.act();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose () {
		assetManager.dispose();
		batch.dispose();
		simsunFont.dispose();
		bitmapFont.dispose();
		stage.dispose();
	}
}
