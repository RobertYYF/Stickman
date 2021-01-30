package stickman.Entities.HeroPathState;

public class HeroPathStateContext {
    private ImagePathState state;
    private Actor character;

    public enum Actor {
        DEFAULT("/actors/default/");
        public final String directory;
        private Actor(String directory) {this.directory = directory;}
    }

    public HeroPathStateContext(Actor character, double xpos) {
        this.character = character;
        this.state = new StandState(xpos);
    }

    public Actor getCharacter() {
        return this.character;
    }

    public void setState(ImagePathState state) {
        this.state = state;
    }

    public String getPath(double currentXPos) {
        return this.state.handle(this, currentXPos);
    }
}
