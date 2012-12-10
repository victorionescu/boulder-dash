package bdash.model;

import java.awt.*;

public class PlayerElement extends CaveElement {
    public static enum LastDirection {NORTH, SOUTH, EAST, WEST }

    private final LastDirection lastDirection;

    public PlayerElement(Cave cave, Point coordinates, LastDirection lastDirection) {
        super(cave, coordinates);
        this.lastDirection = lastDirection;
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
