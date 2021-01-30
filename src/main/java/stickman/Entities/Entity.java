package stickman.Entities;

public abstract class Entity {

    public enum Layer {
        BACKGROUND, FOREGROUND, EFFECT
    }

    protected Layer layer = Layer.BACKGROUND;
    protected double xPos = 0;
    protected double yPos = 0;
    protected double width = 0;
    protected double height = 0;
    protected double xVel = 0;
    protected double yVel = 0;


    //
    // Abstract methods
    //

    public abstract String getImagePath();
    public abstract Entity newInstance();

    //
    // Default methods
    //

    public double getXPos() {
        return this.xPos;
    }

    public void setXPos(double xpos) {
        this.xPos = xpos;
    }

    public double getYPos() {
        return this.yPos;
    }

    public void setYPos(double ypos) {
        this.yPos = ypos;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setXVel(double xVel) {
        this.xVel = xVel;
    }

    public double getXVel() {
        return this.xVel;
    }

    public void setYVel(double yVel) {
        this.yVel = yVel;
    }

    public double getYVel() {
        return yVel;
    }

    public Layer getLayer() {
        return this.layer;
    }

    public boolean intersects(Entity entity) {
        return (this.xPos < (entity.xPos + entity.width)) &&
                (entity.xPos < (this.xPos + this.width)) &&
                (this.yPos < (entity.yPos + entity.height)) &&
                (entity.yPos < (this.yPos + this.height));
    }
}
