package stickman.Entities.HeroPathState;

public class WalkLeftState extends ImagePathState {

    private final String[] imagePaths = {"ch_walk5.png", "ch_walk6.png", "ch_walk7.png", "ch_walk8.png"};

    public WalkLeftState(double startXPos) {
        super(startXPos);
    }

    @Override
    public String handle(HeroPathStateContext context, double xpos) {
        if (xpos > lastXPos) {
            context.setState(new WalkRightState(lastXPos));
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
