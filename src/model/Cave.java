package model;

import java.awt.*;

public class Cave {
    private Dimension dimension;
    private CaveElement[][] elements;

    public Cave(Dimension dimension) {
        this.dimension = dimension;
        elements = new CaveElement[dimension.width][dimension.height];
    }

    public int getWidth() {
        return dimension.width;
    }

    public int getHeight() {
        return dimension.height;
    }
}
