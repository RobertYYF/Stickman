package stickman.levels;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import stickman.Entities.*;
import stickman.Entities.FloorTileFactory.DefaultFloorTileFactory;
import stickman.Entities.FloorTileFactory.FloorTileFactory;
import stickman.Entities.HeroPathState.HeroPathStateContext;
import stickman.view.SkyOnlyBackground;
import java.util.ArrayList;
import java.util.Random;

public class DefaultLevelBuilder extends LevelBuilder {

    public DefaultLevelBuilder(JSONObject configuration) {
        this.configuration = configuration;
        this.bg_drawer = new SkyOnlyBackground();
        this.entities = new ArrayList<>();
    }

    @Override
    protected void buildPlatforms() {
        JSONArray platforms = (JSONArray)configuration.get("platforms");

        double x_from;
        double x_to;
        int qty;
        double ypos;
        double width;
        for (int i = 0; i < platforms.size(); i++) {
            JSONObject group = (JSONObject)platforms.get(i);
            x_from = ((Double)group.get("x_from"));
            x_to = ((Double)group.get("x_to"));
            qty = ((Long)group.get("qty")).intValue();
            ypos = ((Double)group.get("ypos"));
            width = (x_to - x_from) / qty;
            Entity platform = new Platform(x_from, ypos, width, 15);
            buildRow(platform , qty);
        }
    }

    @Override
    protected void buildMushroom() {
        JSONArray mushrooms = (JSONArray)configuration.get("mushrooms");

        double x;
        double y;
        double w;
        double h;
        for (int i = 0; i < mushrooms.size(); i++) {
            JSONObject group = (JSONObject) mushrooms.get(i);
            x = ((Double) group.get("x"));
            y = ((Double) group.get("y"));
            w = ((Double) group.get("w"));
            h = ((Double) group.get("h"));
            Entity mushroom = new Mushroom(x, y, w, h);
            entities.add(mushroom);
        }
    }

    @Override
    protected void buildFlags() {
        JSONArray flags = (JSONArray)configuration.get("flags");

        double x;
        double y;
        double w;
        double h;
        for (int i = 0; i < flags.size(); i++) {
            JSONObject group = (JSONObject) flags.get(i);
            x = ((Double) group.get("x"));
            y = ((Double) group.get("y"));
            w = ((Double) group.get("w"));
            h = ((Double) group.get("h"));
            Entity flag = new Flag(x, y, w, h);
            entities.add(flag);
        }
    }

    @Override
    protected void buildTrees() {
        JSONArray trees = (JSONArray)configuration.get("trees");

        double x;
        double y;
        double w;
        double h;
        for (int i = 0; i < trees.size(); i++) {
            JSONObject group = (JSONObject) trees.get(i);
            x = ((Double) group.get("x"));
            y = ((Double) group.get("y"));
            w = ((Double) group.get("w"));
            h = ((Double) group.get("h"));
            Entity tree = new Tree(x, y, w, h);
            entities.add(tree);
        }
    }

    @Override
    protected void buildSlimes() {
        JSONArray slimes = (JSONArray)configuration.get("slimes");

        double x;
        double y;
        for (int i = 0; i < slimes.size(); i++) {
            JSONObject group = (JSONObject) slimes.get(i);
            x = ((Double) group.get("x"));
            y = ((Double) group.get("y"));
            Entity slime = new Slime(x, y, randomColourSelector());
            entities.add(slime);
        }
    }

    // Helper for buildSlimes() to choose slime colour.
    private Slime.Colour randomColourSelector() {
        Random ran = new Random();
        int choice = ran.nextInt(5);
        switch (choice) {
            case 0:
                return Slime.Colour.BLUE;
            case 1:
                return Slime.Colour.GREEN;
            case 2:
                return Slime.Colour.PURPLE;
            case 3:
                return Slime.Colour.RED;
            default:
                return Slime.Colour.YELLOW;
        }
    }

    @Override
    protected void buildHero() {

        JSONObject hero_obj = (JSONObject)configuration.get("hero");
        double x = ((Double)hero_obj.get("x"));
        double y = ((Double)hero_obj.get("y"));
        boolean isSmall = ((Boolean)hero_obj.get("small"));
        hero = HeroSingle.getInstance();
        hero.setXPos(x);
        hero.setYPos(y);
        //hero = new Hero(x, y, HeroPathStateContext.Actor.DEFAULT);
        if (isSmall) {
            hero.setWidth(20);
            hero.setHeight(34);
        } else {
            hero.setWidth(30);
            hero.setHeight(51);
        }
        entities.add(hero);
    }

    @Override
    protected void buildFloor() {
        FloorTileFactory tileFactory = new DefaultFloorTileFactory();
        JSONObject floors = (JSONObject)configuration.get("floor");
        JSONArray design;

        design = (JSONArray)floors.get("dirt");
        floorBuilderHelper(design, tileFactory, FloorTileFactory.Path.DIRT);

        design = (JSONArray)floors.get("top");
        floorBuilderHelper(design, tileFactory, FloorTileFactory.Path.TOP);

        design = (JSONArray)floors.get("left");
        floorBuilderHelper(design, tileFactory, FloorTileFactory.Path.LEFT);

        design = (JSONArray)floors.get("right");
        floorBuilderHelper(design, tileFactory, FloorTileFactory.Path.RIGHT);

        design = (JSONArray)floors.get("left_cnr");
        floorBuilderHelper(design, tileFactory, FloorTileFactory.Path.LEFT_CORNER);

        design = (JSONArray)floors.get("right_cnr");
        floorBuilderHelper(design, tileFactory, FloorTileFactory.Path.RIGHT_CORNER);



    }

    private void floorBuilderHelper(JSONArray groups, FloorTileFactory tileFactory, FloorTileFactory.Path type) {
        for (int i = 0; i < groups.size(); i++) {
            JSONObject group = (JSONObject) groups.get(i);
            boolean asRow = ((Boolean)group.get("x_axis"));
            double from = ((Double) group.get("from"));
            double to = ((Double) group.get("to"));
            int qty = ((Long) group.get("qty")).intValue();
            double pos = ((Double) group.get("pos"));
            double stretch = (to - from) / qty;
            Entity tile;
            if (asRow) {
                tile = tileFactory.createFloorTile(type, from, pos, stretch, 50);
                buildRow(tile, qty);
            }else {
                tile = tileFactory.createFloorTile(type, pos, from, 50, stretch);
                buildColumn(tile, qty);
            }
        }
    }
}
