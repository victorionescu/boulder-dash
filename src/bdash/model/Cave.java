package bdash.model;

import bdash.util.Array2D;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Cave {
    private final Dimension dimension;
    private final Array2D<CaveElement> elements;
    private final List<CaveListener> listeners;

    public Cave(Dimension dimension) {
        this.dimension = dimension;
        elements = new Array2D<CaveElement>(dimension.width, dimension.height);

        /* Initialize cave with empty elements */
        for (int i = 0; i < dimension.getWidth(); i += 1) {
            for (int j = 0; j < dimension.getHeight(); j += 1) {
                elements.setElement(i, j, new EmptyElement(this, new Point(i, j)));
            }
        }

        listeners = new ArrayList<CaveListener>();
    }

    public int getWidth() {
        return dimension.width;
    }

    public int getHeight() {
        return dimension.height;
    }

    public Iterator<CaveElement> getElements() {
        return elements.iterator();
    }

    public CaveElement getElement(int x, int y) {
        return elements.getElement(x, y);
    }

    public void setElement(int x, int y, CaveElement e) {
        elements.setElement(x, y, e);
    }

    public void addListener(CaveListener listener) {
        listeners.add(listener);
    }

    public void fireCaveElementWillChange(CaveElement caveElement) {
        for (CaveListener listener : listeners) {
            listener.caveElementWillChange(this, caveElement);
        }
    }

    public void fireCaveElementChanged(CaveElement caveElement) {
        for (CaveListener listener : listeners) {
            listener.caveElementChanged(this, caveElement);
        }
    }
}
