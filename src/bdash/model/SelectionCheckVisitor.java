package bdash.model;

import bdash.model.*;

public class SelectionCheckVisitor implements CaveElementVisitor {
    private boolean containsElements;
    private boolean wallsOnly;
    private boolean sameColor;
    private WallElement.WallColor wallColor;

    public SelectionCheckVisitor() {
        containsElements = false;

        wallsOnly = true;

        sameColor = true;

        wallColor = null;
    }

    public boolean doesContainElements() {
        return containsElements;
    }

    public boolean hasWallsOnly() {
        return wallsOnly;
    }

    public WallElement.WallColor getWallColor() {

        if (containsElements && wallsOnly && sameColor) {
            return wallColor;
        } else {
            return WallElement.WallColor.UNDEFINED;
        }
    }

    public void visit(WallElement e, CaveElementHolder holder) {
        containsElements = true;

        if (wallColor == null) {
            wallColor = e.getWallColor();
        } else {
            sameColor = sameColor && (e.getWallColor() == wallColor);
        }
    }

    public void visit(BoulderElement e, CaveElementHolder holder) {
        containsElements = true;

        wallsOnly = false;
    }

    public void visit(DiamondElement e, CaveElementHolder holder) {
        containsElements = true;

        wallsOnly = false;
    }

    public void visit(DirtElement e, CaveElementHolder holder) {
        containsElements = true;

        wallsOnly = false;
    }

    public void visit(PlayerElement e, CaveElementHolder holder) {
        containsElements = true;

        wallsOnly = false;
    }
}
