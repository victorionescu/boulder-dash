package bdash.model;

import java.awt.Point;

public class CaveElementHolder {
    public static final int HOLDER_SIZE_IN_PX = 30;

    protected final Cave cave;
    protected final int row, column;
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
