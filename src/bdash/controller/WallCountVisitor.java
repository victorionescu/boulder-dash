package bdash.controller;

import bdash.model.*;
import bdash.util.WallColor;

public class WallCountVisitor implements CaveElementVisitor {
    private boolean wallsOnly;
    private boolean sameColor;
    private WallColor wallColor;

    public WallCountVisitor() {
        wallsOnly = true;
        sameColor = true;
        wallColor = null;
    }

    public boolean hasWallsOnly() {
        return wallsOnly;
    }

    public boolean isSameColor() {
        return sameColor;
    }

    public WallColor getWallColor() {
        return wallColor;
    }

    public void visit(WallElement e) {
        if (wallColor == null) {
            wallColor = e.getWallColor();
        } else {
            sameColor = (e.getWallColor() == wallColor);
        }
    }

    public void visit(HeavyElement e) {
        wallsOnly = false;
    }

    public void visit(DirtElement e) {
        wallsOnly = false;
    }

    public void visit(PlayerElement e) {
        wallsOnly = false;
    }

    public void visit(EmptyElement e) {
        wallsOnly = false;
    }
}
