package stickman.Entities.FloorTileFactory;

import stickman.Entities.Entity;

public class DefaultFloorTileFactory extends FloorTileFactory {


    @Override
    public Entity createFloorTile(Path path, double xPos, double yPos, double width, double height) {
        switch (path) {
            case TOP:
                return new DefaultTopFloorTile(xPos, yPos, width, height);
            case DIRT:
                return new DefaultDirtFloorTile(xPos, yPos, width, height);
            case LEFT:
                return new DefaultLeftFloorTile(xPos, yPos, width, height);
            case RIGHT:
                return new DefaultRightFloorTile(xPos, yPos, width, height);
            case LEFT_CORNER:
                return new DefaultLeftCornerFloorTile(xPos, yPos, width, height);
            case RIGHT_CORNER:
                return new DefaultRightCornerFloorTile(xPos, yPos, width, height);

            default: // Will never happen.
                return null;
        }
    }
}
