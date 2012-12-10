package bdash.model;

import java.awt.*;

public class DirtElement extends CaveElement {
    public DirtElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
    }

    public CaveElement clone() {
        Point newCoordinates = new Point(coordinates.x, coordinates.y);
        return new DirtElement(cave, newCoordinates);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
