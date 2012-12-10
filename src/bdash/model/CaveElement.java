package bdash.model;

import java.awt.*;

public abstract class CaveElement {
    private Cave cave;
    private Point coordinates;


    public CaveElement(Cave cave, Point coordinates) {
        this.cave = cave;
        this.coordinates = coordinates;
    }

    public Cave getCave() {
        return cave;
    }

    public int getX() {
        return coordinates.x;
    }

    public int getY() {
        return coordinates.y;
    }

    public boolean isHit(int x, int y) {
        if (x >= getX() * 30 && x < (getX() + 1) * 30 &&
                y >= getY() * 30 && y < (getY() + 1) * 30) {
            return true;
        } else {
            return false;
        }
    }

    public abstract void accept(CaveElementVisitor visitor);
}
