package model;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Victor
 * Date: 12/4/12
 * Time: 8:19 AM
 * To change this template use File | Settings | File Templates.
 */
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
