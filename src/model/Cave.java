package model;

import util.Array2D;

import java.awt.*;

public class Cave {
    private final Dimension dimension;
    private final Array2D<CaveElement> elements;

    public Cave(Dimension dimension) {
        this.dimension = dimension;
        elements = new Array2D<CaveElement>(dimension.width, dimension.height);
    }

    public int getWidth() {
        return dimension.width;
    }

    public int getHeight() {
        return dimension.height;
    }

    public Array2D<CaveElement> getElements() {
        return elements;
    }

    public CaveElement getElement(int x, int y) {
        return elements.getElement(x, y);
    }

    public void putElement(int x, int y, CaveElement e) {
        elements.setElement(x, y, e);
    }
}
