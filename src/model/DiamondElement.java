package model;

import java.awt.*;

public class DiamondElement extends CaveElement {
    private boolean falling;

    public DiamondElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
        falling = false;
    }
}
