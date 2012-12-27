package bdash.model;

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

    public Cave getCave() {
        return cave;
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
        this.caveElement = caveElement;
    }

    public boolean isHit(int x, int y) {
        return (x >= column * HOLDER_SIZE_IN_PX && x < (column + 1) * HOLDER_SIZE_IN_PX) &&
               (y >= row * HOLDER_SIZE_IN_PX && y < (row + 1) * HOLDER_SIZE_IN_PX);
    }
}
