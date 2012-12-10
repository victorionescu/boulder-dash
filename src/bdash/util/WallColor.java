package bdash.util;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class WallColor {
    public static List<WallColor> COLORS = new ArrayList<WallColor>();

    static {
        COLORS.add(new WallColor(Color.RED, "Red"));
        COLORS.add(new WallColor(Color.GREEN, "Green"));
        COLORS.add(new WallColor(Color.BLUE, "Blue"));
    }

    private final Color color;
    private final String stringRepresentation;

    public WallColor() {
        color = null;
        stringRepresentation = "";
    }

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
