package stickman.levels;
import stickman.Entities.Entity;
import stickman.view.BackgroundDrawer;

import java.util.List;

public interface Level {
    List<Entity> getEntities();
    BackgroundDrawer getBGDrawer();
    double getHeight();
    double getWidth();
    void tick();
    double getFloorHeight();
    Entity getHero();
    double getHeroX();
    double getHeroY();
    void setHeroX(double xPos);
    boolean jump();
    boolean shoot();
    boolean moveLeft();
    boolean moveRight();
    boolean stopMoving();
    boolean levelLost();
    boolean levelWon();
    boolean delete(Entity e);
    boolean add(Entity e);
    boolean getShoot();
    void setShoot(boolean b);
    int getLife();
    void setLife(int life);
}
