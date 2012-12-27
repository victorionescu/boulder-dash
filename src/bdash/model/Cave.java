package bdash.model;

import bdash.util.Array2D;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Cave {
    private final int width, height;
    private final Array2D<CaveElementHolder> elementHolders;

    private final List<CaveListener> listeners;

    public Cave(int width, int height) {
        this.width = width;
        this.height = height;
        elementHolders = new Array2D<CaveElementHolder>(width, height);
        listeners = new ArrayList<CaveListener>();

        for (int row = 0; row < height; row += 1) {
            for (int column = 0; column < width; column += 1) {
                elementHolders.setElement(row, column, new CaveElementHolder(this, row, column));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Iterator<CaveElementHolder> getElementHolders() {
        return elementHolders.iterator();
    }

    public CaveElementHolder getElementHolder(int row, int column) {
        return elementHolders.getElement(row, column);
    }

    public void fireElementHolderWillChange(CaveElementHolder elementHolder) {
        for (CaveListener l : listeners) {
            l.caveElementWillChange(this, elementHolder);
        }
    }

    public void fireElementHolderChanged(CaveElementHolder elementHolder) {
        for (CaveListener l : listeners) {
            l.caveElementChanged(this, elementHolder);
        }
    }

    /*
    public CaveElementHolder getElementHolder(int row, int column) {
        return elementHolders.getElement(row, column);
    }
    */

    /*
    public void setElement(int x, int y, CaveElement e) {
        elements.setElement(x, y, e);
    }
    */

    /*
    public Cave clone() {
        Cave newCave = new Cave(dimension);

        Iterator<CaveElement> elementIterator = getElements();
        while (elementIterator.hasNext()) {
            CaveElement element = elementIterator.next();

            CaveElement newElement = element.clone();
            newElement.setCave(newCave);

            newCave.setElement(newElement.getX(), newElement.getY(), newElement);
        }

        for (CaveListener l : listeners) {
            newCave.addListener(l);
        }

        return newCave;
    }
    */

    public void addListener(CaveListener listener) {
        listeners.add(listener);
    }

    /*
    public void fireCaveElementWillChange(CaveElement caveElement) {
        for (CaveListener listener : listeners) {
            listener.caveElementWillChange(this, caveElement);
        }
    }

    public void fireCaveElementChanged(CaveElement caveElement) {
        for (CaveListener listener : listeners) {
            listener.caveElementChanged(this, caveElement);
        }
    }*/
}
