package stickman.model;

import stickman.levels.Level;

public interface GameEngine {
    Level getCurrentLevel();

    void startLevel();
    void switchLevel();
    // Hero inputs - boolean for success (possibly for sound feedback)
    boolean jump();
    boolean moveLeft();
    boolean moveRight();
    boolean stopMoving();

    boolean save();
    boolean load();

    void tick();
}
