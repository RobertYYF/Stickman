package stickman.view;

import javafx.scene.Node;
import stickman.Entities.Entity;

public interface EntityView {
    void update(double xViewportOffset, double yViewportUpdate);

    boolean matchesEntity(Entity entity);

    void markForDelete();

    Node getNode();

    boolean isMarkedForDelete();
}
