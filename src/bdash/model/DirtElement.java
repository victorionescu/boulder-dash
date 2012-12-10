package bdash.model;

import java.awt.*;

public class DirtElement extends CaveElement {
    public DirtElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
