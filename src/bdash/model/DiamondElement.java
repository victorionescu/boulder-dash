package bdash.model;

import java.awt.*;

public class DiamondElement extends HeavyElement {
    public DiamondElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
