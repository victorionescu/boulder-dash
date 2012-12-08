package model;

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

    public abstract void accept(CaveElementVisitor visitor);
}
