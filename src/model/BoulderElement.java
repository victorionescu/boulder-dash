package model;

import java.awt.*;

public class BoulderElement extends CaveElement {
    private boolean falling;

    public BoulderElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
        falling = false;
    }
}
