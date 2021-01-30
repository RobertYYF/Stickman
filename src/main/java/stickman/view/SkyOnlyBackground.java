package stickman.view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import stickman.model.GameEngine;

public class SkyOnlyBackground implements BackgroundDrawer {
    private Rectangle sky;
    private Pane pane;
    private GameEngine model;

    @Override
    public void draw(GameEngine model, Pane pane) {
        this.model = model;
        this.pane = pane;

        double width = pane.getWidth();
        double height = pane.getHeight();


        this.sky = new Rectangle(0, 0, width, height);
        sky.setFill(Paint.valueOf("LIGHTBLUE"));
        sky.setViewOrder(1000.0);


        pane.getChildren().add(sky);
    }

    @Override
    public void update(double xViewportOffset, double yViewportOffset) {
        // do nothing since this is a static bg
    }
}
