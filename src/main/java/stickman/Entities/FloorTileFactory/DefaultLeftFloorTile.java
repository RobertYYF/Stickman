package stickman.Entities.FloorTileFactory;

import stickman.Entities.Entity;

public class DefaultLeftFloorTile extends Entity {

    private final static String IMAGE_PATH = "/floor_tile/default/left.png";

    public DefaultLeftFloorTile(double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.layer = Layer.FOREGROUND;
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

    @Override
    public Entity newInstance() {
        return new DefaultLeftFloorTile(this.xPos, this.yPos, this.width, this.height);
    }
}
