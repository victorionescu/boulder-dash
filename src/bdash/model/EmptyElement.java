package bdash.model;

import java.awt.*;

public class EmptyElement extends CaveElement {
    public EmptyElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
