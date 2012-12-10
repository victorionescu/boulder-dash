package bdash.model;

import java.awt.*;

public class BoulderElement extends HeavyElement {
    public BoulderElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
    }

    public CaveElement clone() {
        Point newCoordinates = new Point(coordinates.x, coordinates.y);
        return new BoulderElement(cave, newCoordinates);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
