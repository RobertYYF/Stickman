package stickman.Entities;

public class Ghost extends Entity {

    private static final String imagePath = "/ghost.png";

    public Ghost (double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = 76;
        this.height = 106;
        this.layer = Layer.FOREGROUND;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Entity newInstance() {
        return new Ghost(this.getXPos(),this.getYPos());
    }

}
