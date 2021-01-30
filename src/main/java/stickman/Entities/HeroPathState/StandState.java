package stickman.Entities.HeroPathState;

public class StandState extends ImagePathState {

    private final String[] imagePathsLookRight = {"ch_stand1.png", "ch_stand2.png", "ch_stand3.png", "ch_stand2.png"};
    private final String[] imagePathsLookLeft = {"ch_stand4.png", "ch_stand5.png", "ch_stand6.png", "ch_stand5.png"};
    private boolean isLookingRight = true;
    private final int LOOKING_LIMIT = 5;
    private int currentLookingIteration = 0;

    public StandState(double startXPos) {
        super(startXPos);
        UPDATE_FREQUENCY_INDEX = 20;
    }

    @Override
    public String handle(HeroPathStateContext context, double xpos) {
        if (xpos < lastXPos) {
            context.setState(new WalkLeftState(lastXPos));
            return context.getPath(xpos);
        }

        if (xpos > lastXPos) {
            context.setState(new WalkRightState(lastXPos));
            return context.getPath(xpos);
        }

        if (currentFrame < UPDATE_FREQUENCY_INDEX) {
            currentFrame += 1;
            return getImage(context);
        } else {
            currentFrame = 0;
        }

        // Look left for a little while, then look right for a little while, and so on.
        if (currentLookingIteration > LOOKING_LIMIT) {
            if (isLookingRight)
                isLookingRight = false;
            else
                isLookingRight = true;

            currentLookingIteration = 0;
            currentImageIndex = -1;
        }

        currentImageIndex = (currentImageIndex + 1) % imagePathsLookLeft.length;
        if (currentImageIndex == 0) currentLookingIteration += 1;
        return getImage(context);
    }

    private String getImage(HeroPathStateContext context) {
        String dir = context.getCharacter().directory;
        if (isLookingRight)
            return dir + imagePathsLookRight[currentImageIndex];
        else
            return dir + imagePathsLookLeft[currentImageIndex];
    }
}
