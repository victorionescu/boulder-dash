package model;

import java.awt.*;

public class Cave {
    private Dimension dimension;

    public Cave(Dimension dimension) {
        this.dimension = dimension;
    }

    public int getWidth() {
        return dimension.width;
    }

    public int getHeight() {
        return dimension.height;
    }
}
