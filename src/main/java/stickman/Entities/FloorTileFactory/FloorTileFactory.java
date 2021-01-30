package stickman.Entities.FloorTileFactory;

import stickman.Entities.Entity;

public abstract class FloorTileFactory {

    public enum Path {
        DIRT,
        TOP,
        LEFT,
        RIGHT,
        LEFT_CORNER,
        RIGHT_CORNER;
    }

    public Entity buildTile(Path path, double xPos, double yPos, double width, double height) {
        Entity floorTile = createFloorTile(path, xPos, yPos, width, height);
        return floorTile;
    }

    public abstract Entity createFloorTile(Path path, double xPos, double yPos, double width, double height);
}
