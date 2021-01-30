package stickman.Entities;

public class Flag extends Entity {

    private static final String imagePath = "/flag.png";

    public Flag (double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.layer = Layer.FOREGROUND;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Entity newInstance() {
        return new Flag(this.getXPos(),this.getYPos(), this.width, this.height);
    }

}
