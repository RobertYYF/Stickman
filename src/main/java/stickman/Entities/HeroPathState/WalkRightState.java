package stickman.Entities.HeroPathState;

public class WalkRightState extends ImagePathState {

    private final String[] imagePaths = {"ch_walk1.png", "ch_walk2.png", "ch_walk3.png", "ch_walk4.png"};

    public WalkRightState(double startXPos) {
        super(startXPos);
    }

    @Override
    public String handle(HeroPathStateContext context, double xpos) {
        if (xpos < lastXPos) {
            context.setState(new WalkLeftState(lastXPos));
            return context.getPath(xpos);
        }

        if (xpos == lastXPos) {
            context.setState(new StandState(lastXPos));
            return context.getPath(xpos);
        }

        if (currentFrame < UPDATE_FREQUENCY_INDEX) {
            currentFrame += 1;
            return context.getCharacter().directory + imagePaths[currentImageIndex];
        } else {
            currentFrame = 0;
        }

        this.lastXPos = xpos;
        this.currentImageIndex = (this.currentImageIndex + 1) % imagePaths.length;
        return context.getCharacter().directory + imagePaths[this.currentImageIndex];
    }
}
