package bdash.model;

import java.awt.Point;

/*
    Class that denotes a wrapper for the CaveElement. This deals with logic that is independent of the cave
    elements, such as positioning within the cave and whether an element is hit or not by some coordinates.
 */

public class CaveElementHolder {
    /* Constant that denotes the length of the sides of an element holder, in pixels. */
    public static final int HOLDER_SIZE_IN_PX = 30;

    /* Parent cave. */
    protected final Cave cave;

    /* Position within the cave. */
    protected final int row, column;

    /* CaveElement held by the holder. */
    protected CaveElement caveElement;

    public CaveElementHolder(Cave cave, int row, int column) {
        this.cave = cave;
        this.row = row;
        this.column = column;

        caveElement = null;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public CaveElement getCaveElement() {
        return caveElement;
    }

    public void setCaveElement(CaveElement caveElement) {
        cave.fireElementHolderWillChange(this);
        this.caveElement = caveElement;
        cave.fireElementHolderChanged(this);
    }

    public boolean isHit(Point point) {
        if (point == null) {
            return false;
        }

        int x = point.x;
        int y = point.y;

        return (x >= column * HOLDER_SIZE_IN_PX && x < (column + 1) * HOLDER_SIZE_IN_PX) &&
               (y >= row * HOLDER_SIZE_IN_PX && y < (row + 1) * HOLDER_SIZE_IN_PX);
    }
}
