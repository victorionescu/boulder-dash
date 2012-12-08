package model;

import util.WallColor;
import java.awt.*;

public class WallElement extends CaveElement {
    private final WallColor wallColor;

    public WallElement(Cave cave, Point coordinates, WallColor wallColor) {
        super(cave, coordinates);
        this.wallColor = wallColor;
    }

    public WallColor getWallColor() {
        return wallColor;
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
