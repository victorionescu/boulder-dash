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

    protected void fireElementHolderWillChange(CaveElementHolder elementHolder) {
        for (CaveListener l : listeners) {
            l.caveElementWillChange(this, elementHolder);
        }
    }

    protected void fireElementHolderChanged(CaveElementHolder elementHolder) {
        for (CaveListener l : listeners) {
            l.caveElementChanged(this, elementHolder);
        }
    }

    protected void fireGameLost() {
        for (CaveListener l : listeners) {
            l.gameLost();
        }
    }


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

    public static class Coordinates {
        private final int row, column;

        public Coordinates(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }
    }
}
