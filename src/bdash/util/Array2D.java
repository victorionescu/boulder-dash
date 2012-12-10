package bdash.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array2D<E> implements Iterable<E> {
    private Object[][] theArray;
    private int width, height;

    public Array2D(int width, int height) {
        this.width = width;
        this.height = height;
        theArray = new Object[width][height];
    }

    public void setElement(int x, int y, E element) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new ArrayIndexOutOfBoundsException();
        }
        theArray[x][y] = element;
    }

    @SuppressWarnings("unchecked")
    public E getElement(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E)theArray[x][y];
    }

    public Iterator<E> iterator() {
        return new Array2DIterator<E>(this);
    }

    private static class Array2DIterator<E> implements Iterator<E> {
        private final Array2D<E> array2D;
        private final int width, height;
        private int x, y;

        protected void makeStep() {
            x += 1;
            if (x >= width) {
                x = 0;
                y -= 1;
            }
        }

        public Array2DIterator(Array2D<E> array2D) {
            this.array2D = array2D;
            width = array2D.width;
            height = array2D.height;
            x = 0;
            y = height - 1;
        }

        public boolean hasNext() {
            while (y >= 0 && array2D.getElement(x, y) == null) {
                makeStep();
            }
            return (y >= 0);
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E element = array2D.getElement(x, y);
            makeStep();

            return element;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
