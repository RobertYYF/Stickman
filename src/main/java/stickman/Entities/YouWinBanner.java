package stickman.Entities;

public class YouWinBanner extends Entity {

    private static final String imagePath = "/youwin.png";

    public YouWinBanner (double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.layer = Layer.FOREGROUND;
    }

    @Override
    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Entity newInstance() {
        return new YouWinBanner(this.getXPos(),this.getYPos());
    }

}
