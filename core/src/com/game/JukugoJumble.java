package com.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class JukugoJumble {

    private Stage stage;
    private Table table;
    private Skin glassy;
    private Skin sgx;
    private Skin golden;
    private Array<String> quests;
    private String[] quest;
    private Label labelQuest;
    private Label note;
    private TextButton[] listAnswer;
    private TextButton[] answerMap;
    private TextButton[] listChosen;
    private Table answerTab;
    private int countAnswer;

    private Main main;

    JukugoJumble(Stage stage, Skin glassy, Skin sgx, Skin golden, Main main) {
        this.stage = stage;
        this.main = main;
        this.glassy = glassy;
        this.sgx = sgx;
        this.golden = golden;
    }

    private void gameTitle(Table table) {
        Label label = new Label("Jukugo Jumble", glassy.get("big", Label.LabelStyle.class));
        table.add(label).padBottom(60);
        table.row();
    }

    private ProgressBar createProgressBar(Skin skin, String name, Table table) {
        ProgressBar progressBar = new ProgressBar(0,100,1f, false, skin, name);
        progressBar.setValue(50);
        table.add(progressBar);
        return progressBar;
    }

    private TextButton createButton(String text, Skin skin, String name, Table table, ChangeListener changeListener) {
        TextButton btn = new TextButton(text, skin, name);
        btn.addListener(changeListener);
        table.add(btn);
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
        table = createTable(stage);
        gameTitle(table);
        TextButton btn = createButton("Grade 1", glassy,"small", table, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startmode(1);
            }
        });
        table.getCell(btn).width(300).padBottom(10).row();

        btn = createButton("Grade 2", glassy,"small", table, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startmode(2);
            }
        });
        table.getCell(btn).width(300).padBottom(10).row();

        btn = createButton("Grade 3", glassy,"small", table, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startmode(3);
            }
        });
        table.getCell(btn).width(300).padBottom(10).row();

        btn = createButton("Grade 4", glassy,"small", table, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startmode(4);
            }
        });
        table.getCell(btn).width(300).padBottom(10).row();

        btn = createButton("Grade 5", glassy,"small", table, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startmode(5);
            }
        });
        table.getCell(btn).width(300).padBottom(10).row();

        btn = createButton("Grade 6", glassy,"small", table, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startmode(6);
            }
        });
        table.getCell(btn).width(300).padBottom(10).row();

        btn = createButton("Back", glassy,"small", table, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.wellcomeScreen.menuScreen();
            }
        });
        table.getCell(btn).width(300).padBottom(10).row();

        main.status = 3;
    }

    private void windowQuest() {
        quest = quests.get((int)(Math.random() * quests.size)).trim().split("\\s+");
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        main.simsunFont.setColor(Color.WHITE);
        labelStyle.font = main.simsunFont;
        labelStyle.fontColor = Color.WHITE;
        labelQuest = new Label(quest[0], labelStyle);
        table.add(labelQuest).padTop(50).padBottom(5).row();
        note = new Label(quest[2], glassy.get("default", Label.LabelStyle.class));
        table.add(note).padTop(50).padTop(5).row();
    }

    private TextButton[] answer() {
        answerTab.clearChildren();
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = main.simsunFont;
        textButtonStyle.fontColor = Color.BLACK;
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        quest[1] = quest[1].substring(1, quest[1].length() - 1);
        final TextButton[] textButtons = new TextButton[quest[1].length()];
        answerMap = new TextButton[quest[1].length()];
        countAnswer = 0;
        Table ansRow = null;
        for (int i = 0; i < quest[1].length(); i++) {
            if (i % 5 == 0) {
                ansRow = new Table();
                answerTab.add(ansRow).row();
            }
            textButtons[i] = new TextButton(" ", textButtonStyle);
            textButtons[i].addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (!((TextButton) actor).getText().toString().equals(" ")) {
                        for (int i = 0; i < listAnswer.length; i++)
                            if (listAnswer[i] == actor) {
                                answerMap[i].setVisible(true);
                                answerMap[i] = null;
                                break;
                            }
                        ((TextButton) actor).setText(" ");
                        countAnswer--;
                    }
                }
            });
            Table cell = new Table();
            cell.add(textButtons[i]);
            assert ansRow != null;
            ansRow.add(cell).fill().width(50).height(50).pad(20);
        }
        return textButtons;
    }

    private String[] RandomizeArray(String[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            String temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    private boolean addAnswer(TextButton textButton) {
        for (int i = 0; i < listAnswer.length; i++) {
            if (listAnswer[i].getText().toString().equals(" ")) {
                listAnswer[i].setText(textButton.getText().toString());
                answerMap[i] = textButton;
                countAnswer++;
                checkUpdate();
                return true;
            }
        }
        return false;
    }

    private void btnSelect() {
        Table selectTab = new Table();
        table.add(selectTab).padBottom(30).row();
        listChosen = new TextButton[24];
        for (int i = 0; i < 24; i++) {
            TextButton.TextButtonStyle textButtonStyle = sgx.get("big", TextButton.TextButtonStyle.class);
            textButtonStyle.font = main.simsunFontSmall;
            textButtonStyle.fontColor = Color.WHITE;
            listChosen[i] = new TextButton(" ", sgx, "big");
            listChosen[i].addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    if (addAnswer((TextButton)actor))
                        actor.setVisible(false);
                }
            });
            selectTab.add(listChosen[i]);
            if (i % 8 == 7) selectTab.row();
        }
        btnSelectUpdate();
    }

    private void btnSelectUpdate() {
        String[] hiraganaList = new String[24];
        int i = 0;
        for (; i < quest[1].length(); i++) hiraganaList[i] = quest[1].charAt(i) + "";
        for (; i < 24; i++) hiraganaList[i] = main.hiragana.charAt((int)(Math.random() * main.hiragana.length())) + "";
        hiraganaList = RandomizeArray(hiraganaList);
        for (i = 0; i < 24; i++) {
            listChosen[i].setText(hiraganaList[i]);
            listChosen[i].setVisible(true);
        }
    }

    private void header() {
        Table header = new Table();
        table.add(header).growX().padTop(20).row();
        TiledDrawable tiledDrawable = golden.getTiledDrawable("progress-bar-life");
        tiledDrawable.setMinWidth(0f);
        golden.get("default-horizontal", ProgressBar.ProgressBarStyle.class).knobBefore = tiledDrawable;

        ProgressBar progressBar = createProgressBar(golden, "default-horizontal", header);
        progressBar.setWidth(250);
        header.getCell(progressBar).expandX().padLeft(20).left();

        TextButton btnPause = createButton("Back", glassy, "small", header, new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.wellcomeScreen.menuScreen();
            }
        });
        header.getCell(btnPause).padRight(20).row();
    }

    private void getData(int level) {
        String[] groupQuest = main.kunGrade[level - 1].split("\\r\\n\\r\\n");
        quests = new Array<String>();
        for (String aGroupQuest : groupQuest) {
            quests.addAll(aGroupQuest.split("[\\r\\n]+"));
        }
    }

    private void startmode(int level) {
        stage.clear();
        table = createTable(stage);
        header();
        getData(level);
        windowQuest();
        answerTab = new Table();
        table.add(answerTab).grow().row();
        listAnswer = answer();
        btnSelect();
    }

    private void checkUpdate() {
        if (countAnswer == quest[1].length()) {
            StringBuilder playerAns = new StringBuilder();
            for (TextButton aListAnswer : listAnswer)
                playerAns.append(aListAnswer.getText().toString());
            if (playerAns.toString().equals(quest[1])) Update();
        }
    }

    private void Update() {
        quest = quests.get((int)(Math.random() * quests.size)).trim().split("\\s+");
        labelQuest.setText(quest[0]);
        note.setText(quest[2]);
        listAnswer = answer();
        btnSelectUpdate();
    }
}
