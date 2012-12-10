package bdash.model;

import bdash.util.WallColor;
import java.awt.*;

public class WallElement extends CaveElement {
    private WallColor wallColor;

    public WallElement(Cave cave, Point coordinates, WallColor wallColor) {
        super(cave, coordinates);
        this.wallColor = wallColor;
    }

    public WallColor getWallColor() {
        return wallColor;
    }

    public void setWallColor(WallColor wallColor) {
        this.wallColor = wallColor;
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
