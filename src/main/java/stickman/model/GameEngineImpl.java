package stickman.model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import stickman.Entities.Entity;
import stickman.Entities.HeroSingle;
import stickman.Entities.YouWinBanner;
import stickman.Handler.TimelineHandler;
import stickman.Memento.CareTaker;
import stickman.Memento.Originator;
import stickman.levels.*;
import org.json.simple.JSONObject;
import stickman.view.BackgroundDrawer;
import stickman.view.GameWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameEngineImpl implements GameEngine {
    Level currentLevel;
    int currentLevelNumber;
    LevelDirector levelDirector;
    JSONObject configuration;
    public static TimelineHandler timelineHandler;
    public static CareTaker careTaker= new CareTaker();
    public static Originator originator = new Originator();

    public GameEngineImpl(JSONObject configuration) {
        this.configuration = configuration;
        this.currentLevelNumber = 1;
        startLevel();
    }

    public void loadLevel(int levelNumber) {

        timelineHandler = new TimelineHandler();
        timelineHandler.init();

        JSONObject levels = (JSONObject)configuration.get("levels");

        String key = String.valueOf(levelNumber);
        JSONObject level = (JSONObject)levels.get(key);

        if (level != null) {
            if (currentLevel != null) {
                int life = currentLevel.getLife();
                levelDirector = new LevelDirector(new DefaultLevelBuilder(level));
                currentLevel = levelDirector.construct();
                currentLevel.setLife(life);
            } else {
                levelDirector = new LevelDirector(new DefaultLevelBuilder(level));
                currentLevel = levelDirector.construct();
            }
        } else {
            Entity youWin = new YouWinBanner(currentLevel.getHero().getXPos() - 300, currentLevel.getHero().getYPos());
            currentLevel.add(youWin);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                    t -> {youWin.setYPos(youWin.getYPos() + 2);}));

            timeline.setCycleCount(110);
            timeline.play();

            timeline.setOnFinished(e -> {
                currentLevel.delete(youWin);
                timelineHandler.stopTime();
            });

            currentLevelNumber --;
        }

        GameWindow.level.setText("Level: " + String.valueOf(levelNumber));
        LevelImp.currentLevelScore = 0;
        timelineHandler.getTimeline().play();
    }

    public void switchLevel() {
        currentLevelNumber ++;
        loadLevel(currentLevelNumber);
    }

    @Override
    public Level getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void startLevel() {
        loadLevel(this.currentLevelNumber);
    }

    @Override
    public boolean jump() {
        return currentLevel.jump();
    }

    @Override
    public boolean moveLeft() {
        currentLevel.moveLeft();
        return false;
    }

    @Override
    public boolean moveRight() {
        currentLevel.moveRight();
        return false;
    }

    @Override
    public boolean stopMoving() {
        currentLevel.stopMoving();
        return false;
    }

    @Override
    public boolean save() {

        careTaker.clear();
        System.out.println("saving!");
        System.out.println(currentLevel.getEntities().size());
        List<Entity> backup = new ArrayList<>();
        List<double[]> backupPos = new ArrayList<>();
        backup.addAll(currentLevel.getEntities());
        backup.stream().forEach(e -> backupPos.add(new double[] {e.getXPos(), e.getYPos()}));

        Map<String, Object> saveState = new HashMap<>();
        saveState.put("entities", backup);
        saveState.put("entitiesPos", backupPos);
        saveState.put("currentLevelScore", LevelImp.currentLevelScore);
        saveState.put("totalScore", LevelImp.score);
        saveState.put("levelNumber", currentLevelNumber);
        saveState.put("heroX", currentLevel.getHeroX());
        saveState.put("heroY", currentLevel.getHeroY());
        saveState.put("level", currentLevel);
        saveState.put("ableToShoot", currentLevel.getShoot());
        saveState.put("life", currentLevel.getLife());

        originator.setState(saveState);
        careTaker.add(originator.saveStateToMemento());

        return true;
    }

    @Override
    public boolean load() {

        originator.getStateFromMemento(careTaker.get(0));
        Map<String, Object> history = originator.getState();
        List<Entity> entities = (List<Entity>) history.get("entities");
        List<double[]> pos = (List<double[]>) history.get("entitiesPos");
        System.out.println(entities.size());
        int currentLevelScore = (Integer) history.get("currentLevelScore");
        int totalScore = (Integer) history.get("totalScore");
        int levelNum = (Integer) history.get("levelNumber");
        int life = (Integer) history.get("life");
        boolean ableToShoot = (boolean)  history.get("ableToShoot");
        JSONObject levels = (JSONObject)configuration.get("levels");
        String key = String.valueOf(levelNum);
        JSONObject level = (JSONObject)levels.get(key);
        Entity hero = HeroSingle.getInstance();
        hero.setXPos((Double) history.get("heroX"));
        hero.setYPos((Double) history.get("heroY"));
        BackgroundDrawer bg_drawer = currentLevel.getBGDrawer();
        currentLevel = new LevelImp(entities, hero, bg_drawer,level);
        for (int i = 0; i < entities.size(); i++) {
            currentLevel.getEntities().get(i).setXPos(pos.get(i)[0]);
            currentLevel.getEntities().get(i).setYPos(pos.get(i)[1]);
        }
        currentLevel.setShoot(ableToShoot);
        currentLevel.setLife(life);
        hero.setXVel(0);
        hero.setYVel(0);
        currentLevelNumber = levelNum;
        GameWindow.level.setText("Level: " + String.valueOf(levelNum));

        LevelImp.score = totalScore;
        LevelImp.currentLevelScore = currentLevelScore;

        return true;
    }

    @Override
    public void tick() {
        if (currentLevel.levelLost()){
            currentLevelNumber = 1;
            LevelImp.score = 0;
            LevelImp.currentLevelScore = 0;
            LevelImp.heroLife = LevelImp.startLife;
            startLevel();
        }
        currentLevel.tick();

    }
}
