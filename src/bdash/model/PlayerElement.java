package bdash.model;

import java.awt.*;

public class PlayerElement extends CaveElement {
    public static enum LastDirection {NORTH, SOUTH, EAST, WEST }

    private final LastDirection lastDirection;

    public PlayerElement(Cave cave, Point coordinates, LastDirection lastDirection) {
        super(cave, coordinates);
        this.lastDirection = lastDirection;
    }

    public CaveElement clone() {
        Point newCoordinates = new Point(coordinates.x, coordinates.y);
        return new PlayerElement(cave, newCoordinates, lastDirection);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
