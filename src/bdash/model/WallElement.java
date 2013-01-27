package bdash.model;

import java.awt.Color;

public class WallElement extends CaveElement {
    private WallColor wallColor;

    public WallElement(WallColor wallColor) {
        this.wallColor = wallColor;
    }

    public WallColor getWallColor() {
        return wallColor;
    }

    public CaveElement deepClone() {
        return new WallElement(wallColor);
    }

    public void accept(CaveElementVisitor visitor, CaveElementHolder holder) {
        visitor.visit(this, holder);
    }

    public boolean equals(Object object) {
        if (object.getClass() == WallColor.class) {
            WallElement that = (WallElement)object;
            return this.wallColor == that.wallColor;
        } else {
            return false;
        }
    }

    /*
        Objects stop falling after landing on walls.
     */
    public CatcherStrategy getCatcherStrategy() {
        return DryCatcherStrategy.INSTANCE;
    }

    /*
        Ancillary class used to better represent the color of a wall.

        Also, it is used to associate colors with string representations and restrict the choice of colors
        for walls.
     */
    public static final class WallColor {
        /* Currently available colors. */
        public static final WallColor UNDEFINED = new WallColor(null, "");
        public static final WallColor RED = new WallColor(Color.RED, "Red");
        public static final WallColor GREEN = new WallColor(Color.GREEN, "Green");
        public static final WallColor BLUE = new WallColor(Color.BLUE, "Blue");

        private final Color color;
        private final String stringRepresentation;

        private WallColor(Color color, String stringRepresentation) {
            this.color = color;
            this.stringRepresentation = stringRepresentation;
        }

        public Color getColor() {
            return color;
        }

        public String toString() {
            return stringRepresentation;
        }
    }
}
