package bdash.model;

import java.awt.*;

public class EmptyElement extends CaveElement {
    public EmptyElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
    }

    public CaveElement clone() {
        Point newCoordinates = new Point(coordinates.x, coordinates.y);
        return new EmptyElement(cave, newCoordinates);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
