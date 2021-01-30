package stickman.levels;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import stickman.Entities.Entity;
import stickman.view.BackgroundDrawer;
import java.util.List;

public abstract class LevelBuilder {

    protected BackgroundDrawer bg_drawer = null;
    protected List<Entity> entities = null;
    protected Entity hero = null;
    protected JSONObject configuration;

    // All methods are declared with default empty definitions.
    // This way, only methods that are required by the subclasses
    // need to be overridden and specified within the
    // subclass body.
    // This enables the subclasses to adhere to the
    // SOLID interface segregation principle.

    public Level getLevel() {
        return new LevelImp(entities, hero, bg_drawer, configuration);
    }

    public BackgroundDrawer getBackgroundDrawer() {
        return this.bg_drawer;
    }

    public void buildLevel() {
        buildPlatforms();
        buildFloor();
        buildClouds();
        buildLandscape();
        buildSlimes();
        buildFlags();
        buildTrees();
        buildHero();
        buildMushroom();
    }

    protected void buildFloor() {
        return;
    }
    protected void buildClouds() {
        return;
    }
    protected void buildPlatforms() {
        return;
    }
    protected void buildLandscape() {
        return;
    }
    protected void buildSlimes() {
        return;
    }
    protected void buildFlags() {
        return;
    }
    protected void buildMushroom() {
        return;
    }
    protected void buildTrees() {
        return;
    }
    protected void buildHero() {
        return;
    }



    // Helpers

    // Builds a row of 'count' many T type Entity's next to each other.
    // Assumes that the passed T Entity has NOT already been added to the
    // entities list.
    protected  <T extends Entity> Entity buildRow(T entity, int count) {
        entities.add(entity);
        Entity lastAdded = entity;
        for (int i = 1; i < count; i++) {
            Entity newEntity = entity.newInstance();
            newEntity.setXPos(lastAdded.getXPos() + lastAdded.getWidth());
            entities.add(newEntity);
            lastAdded = newEntity;
        }

        return lastAdded;
    }

    // Builds a column of 'count' many T type Entity's on top of each other.
    // Assumes that the passed T Entity has NOT already been added to the
    // entities list.
    protected  <T extends Entity> Entity buildColumn(T entity, int count) {
        entities.add(entity);
        Entity lastAdded = entity;
        for (int i = 1; i < count; i++) {
            Entity newEntity = entity.newInstance();
            newEntity.setYPos(lastAdded.getYPos() + (lastAdded.getHeight()));
            entities.add(newEntity);
            lastAdded = newEntity;
        }

        return lastAdded;
    }


}