package bdash.model;
/*
    Visitor used for checking the selection.
    It keeps track of the following facts:
    - if it has encountered elements
    - if all encountered elements are walls
    - if all walls have the same color
    - the color of the walls

    It is used by the 'Delete' button and the ComboBox that selects the color of the walls.
 */
public class SelectionCheckVisitor implements CaveElementVisitor {
    /* If it contains elements. */
    private boolean containsElements;

    /* If it only contains walls. */
    private boolean wallsOnly;

    /* If all walls have the same color. */
    private boolean sameColor;

    /* The color of the walls. */
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
