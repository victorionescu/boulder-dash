package model;

import java.awt.*;

public class WallElement extends CaveElement {
    private final Color color;

    public WallElement(Cave cave, Point coordinates, Color color) {
        super(cave, coordinates);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
