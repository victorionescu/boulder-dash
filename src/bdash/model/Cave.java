package bdash.model;

import bdash.util.Array2D;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/*

    Central element of the model, representing the cave.

    It consists of an Array2D (which has been chosen in order to implement the iteration order that interests us)
    of CaveElementHolder's which may or may not be empty.

    The Cave will always consist of width * height CaveElementHolder's.

    Listeners implement CaveListener.
 */

public class Cave {
    /* Width and height of the cave. */
    private final int width, height;

    /* Array of CaveElementHolder's. */
    private final Array2D<CaveElementHolder> elementHolders;

    /* Listener list. */
    private final List<CaveListener> listeners;

    public Cave(int width, int height) {
        this.width = width;
        this.height = height;
        elementHolders = new Array2D<CaveElementHolder>(width, height);
        listeners = new ArrayList<CaveListener>();

        /* We initialize the array of holders. */
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

    /* Get an iterator through all holders, bottom-top, left-right. */
    public Iterator<CaveElementHolder> getElementHolders() {
        return elementHolders.iterator();
    }

    /* Get holder at a specified position. */
    public CaveElementHolder getElementHolder(int row, int column) {
        return elementHolders.getElement(row, column);
    }

    /* Fire the 'caveElementWillChange' event. */
    protected void fireElementHolderWillChange(CaveElementHolder elementHolder) {
        for (CaveListener l : listeners) {
            l.caveElementWillChange(this, elementHolder);
        }
    }

    /* Fire the 'caveElementChanged' event. */
    protected void fireElementHolderChanged(CaveElementHolder elementHolder) {
        for (CaveListener l : listeners) {
            l.caveElementChanged(this, elementHolder);
        }
    }

    /* Fire the 'gameLost' event. */
    protected void fireGameLost() {
        for (CaveListener l : listeners) {
            l.gameLost();
        }
    }

    /* Fire the 'diamondCollected' event. */
    protected void fireDiamondCollected() {
        for (CaveListener l : listeners) {
            l.diamondCollected();
        }
    }


    /* Returns a deep clone of the cave. This is used to interchange between 'Play' and 'Edit' modes. */
    public Cave clone() {
        Cave newCave = new Cave(width, height);

        for (int row = 0; row < height; row += 1) {
            for (int column = 0; column < width; column += 1) {
                CaveElement newElement = null;
                if (elementHolders.getElement(row, column).getCaveElement() != null) {
                    newElement = elementHolders.getElement(row, column).getCaveElement().clone();
                }

                newCave.getElementHolder(row, column).setCaveElement(newElement);
            }
        }

        return newCave;
    }


    public void addListener(CaveListener listener) {
        listeners.add(listener);
    }
}
