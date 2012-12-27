package bdash.controller;

import bdash.model.*;

public class WallCheckVisitor implements CaveElementVisitor {
    private boolean wallsOnly;
    private boolean sameColor;
    private WallElement.WallColor wallColor;

    public WallCheckVisitor() {
        wallsOnly = true;
        sameColor = true;
        wallColor = null;
    }

    public boolean hasWallsOnly() {
        return wallsOnly;
    }

    public WallElement.WallColor getWallColor() {

        if (wallsOnly && sameColor && wallColor != null) {
            return wallColor;
        } else {
            return WallElement.WallColor.UNDEFINED;
        }
    }

    public void visit(WallElement e) {
        if (wallColor == null) {
            wallColor = e.getWallColor();
        } else {
            sameColor = sameColor && (e.getWallColor() == wallColor);
        }
    }

    public void visit(BoulderElement e) {
        wallsOnly = false;
    }

    public void visit(DiamondElement e) {
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
