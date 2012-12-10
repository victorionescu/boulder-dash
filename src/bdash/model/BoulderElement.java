package bdash.model;

import java.awt.*;

public class BoulderElement extends HeavyElement {
    public BoulderElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
