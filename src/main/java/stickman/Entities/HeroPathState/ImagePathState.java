package stickman.Entities.HeroPathState;

public abstract class ImagePathState {

    protected int currentImageIndex = 0;
    protected double lastXPos;
    protected int UPDATE_FREQUENCY_INDEX;
    protected int currentFrame = -1;

    public ImagePathState(double startXPos) {
        this.lastXPos = startXPos;
        UPDATE_FREQUENCY_INDEX = 7;
    }

    public abstract String handle(HeroPathStateContext context, double xpos);

}
