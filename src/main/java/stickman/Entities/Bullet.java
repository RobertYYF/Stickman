package stickman.Entities;

public class Bullet extends Entity {

    private static final String imagePath = "/bullet.png";

    public Bullet (double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = 20;
        this.height = 20;
        this.layer = Layer.FOREGROUND;
        this.xVel = 5;
        this.yVel = 5;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Entity newInstance() {
        return new Bullet(this.getXPos(),this.getYPos());
    }

}
