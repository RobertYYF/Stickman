package stickman.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import stickman.Entities.Entity;
import stickman.levels.Level;
import stickman.model.GameEngine;
import java.util.ArrayList;
import java.util.List;

public class GameWindow {
    private final int width;
    private final int height;
    private Scene scene;
    private Pane pane;
    public static Text currentLevelScore = new Text();;
    public static Text totalScore = new Text();
    public static Text time = new Text();
    public static Text life = new Text();
    public static Text level = new Text();
    public static GameEngine model;
    private List<EntityView> entityViews;

    private double xViewportOffset = 0.0;
    private double yViewportOffset = 0.0;
    private static final double X_VIEWPORT_MARGIN = 200;
    private static final double Y_VIEWPORT_MARGIN = 100;

    public GameWindow(GameEngine model, int width, int height) {
        this.model = model;
        this.pane = new Pane(currentLevelScore, level, time, life, totalScore);
        this.width = width;
        this.height = height;
        this.scene = new Scene(pane, width, height);

        this.entityViews = new ArrayList<>();

        currentLevelScore.setLayoutX(550);
        currentLevelScore.setLayoutY(20);

        level.setLayoutX(550);
        level.setLayoutY(40);

        time.setLayoutX(550);
        time.setLayoutY(60);

        life.setLayoutX(550);
        life.setLayoutY(80);

        totalScore.setLayoutX(550);
        totalScore.setLayoutY(100);

        KeyboardInputHandler keyboardInputHandler = new KeyboardInputHandler(model);

        scene.setOnKeyPressed(keyboardInputHandler::handlePressed);
        scene.setOnKeyReleased(keyboardInputHandler::handleReleased);

        model.getCurrentLevel().getBGDrawer().draw(model, pane);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void run() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(17),
                t -> this.draw()));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void draw() {
        model.tick();
        Level currentLevel = model.getCurrentLevel();
        List<Entity> entities = currentLevel.getEntities();

        for (EntityView entityView: entityViews) {
            entityView.markForDelete();
        }

        double heroXPos = model.getCurrentLevel().getHeroX();
        double heroYPos = model.getCurrentLevel().getHeroY();
        heroXPos -= xViewportOffset;
        heroYPos -= yViewportOffset;

        // Correct X-axis camera
        if (heroXPos < X_VIEWPORT_MARGIN) {
            if (xViewportOffset >= 0) { // Don't go further left than the start of the level
                xViewportOffset -= X_VIEWPORT_MARGIN - heroXPos;
                if (xViewportOffset < 0) {
                    xViewportOffset = 0;
                    model.getCurrentLevel().setHeroX(X_VIEWPORT_MARGIN);
                }
            }
        } else if (heroXPos > width - X_VIEWPORT_MARGIN) {
            xViewportOffset += heroXPos - (width - X_VIEWPORT_MARGIN);
        }

        // Correct Y-axis camera
        if (heroYPos > (height - Y_VIEWPORT_MARGIN)) {
            yViewportOffset += heroYPos - (height - Y_VIEWPORT_MARGIN);
            if (yViewportOffset > 0)
                yViewportOffset = 0;
        } else if (heroYPos < Y_VIEWPORT_MARGIN) {
            yViewportOffset -= Y_VIEWPORT_MARGIN - heroYPos;
        }

        currentLevel.getBGDrawer().update(xViewportOffset, yViewportOffset);

        for (Entity entity: entities) {
            boolean notFound = true;
            for (EntityView view: entityViews) {
                if (view.matchesEntity(entity)) {
                    notFound = false;
                    view.update(xViewportOffset, yViewportOffset);
                    break;
                }
            }
            if (notFound) {
                EntityView entityView = new EntityViewImpl(entity);
                entityViews.add(entityView);
                pane.getChildren().add(entityView.getNode());
            }
        }

        for (EntityView entityView: entityViews) {
            if (entityView.isMarkedForDelete()) {
                pane.getChildren().remove(entityView.getNode());
            }
        }
        entityViews.removeIf(EntityView::isMarkedForDelete);
    }
}
