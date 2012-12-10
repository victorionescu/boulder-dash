package bdash.model;

import java.awt.*;

public class DiamondElement extends HeavyElement {
    public DiamondElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
    }

    public CaveElement clone() {
        Point newCoordinates = new Point(coordinates.x, coordinates.y);
        return new DiamondElement(cave, newCoordinates);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
