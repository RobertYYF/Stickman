package stickman.Entities;

import stickman.Entities.HeroPathState.HeroPathStateContext;

public class HeroSingle extends Entity{

    private HeroPathStateContext pathContext;
    private static HeroSingle instance = new HeroSingle();

    private HeroSingle() {
        pathContext = new HeroPathStateContext(HeroPathStateContext.Actor.DEFAULT, xPos);
        this.xPos = 0;
        this.yPos = 0;
        this.xVel = 1;
        layer = Layer.FOREGROUND;
    }

    public static HeroSingle getInstance(){
        return instance;
    }

    @Override
    public String getImagePath() {
        return pathContext.getPath(xPos);
    }

    @Override
    public Entity newInstance() {
        return null;
    }

    @Override
    public void setXVel(double xVel) {
        // Do nothing as the Hero's velocity is constant.
    }
}
