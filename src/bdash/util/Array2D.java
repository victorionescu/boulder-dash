package bdash.util;
/*
 * Class used to implement a bidimensional array with an iterator that goes bottom-top, left-right.
 * This is used because we need that order in order to apply gravity.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array2D<E> implements Iterable<E> {
    protected Object[][] theArray;
    protected int width, height;

    public Array2D(int width, int height) {
        this.width = width;
        this.height = height;
        theArray = new Object[height][width];
    }

    public void setElement(int row, int column, E element) {
        if (row < 0 || row >= height || column < 0 || column >= width) {
            throw new ArrayIndexOutOfBoundsException();
        }
        theArray[row][column] = element;
    }

    @SuppressWarnings("unchecked")
    public E getElement(int row, int column) {
        if (row < 0 || row >= height || column < 0 || column >= width) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E)theArray[row][column];
    }

    public Iterator<E> iterator() {
        return new Array2DIterator<E>(this);
    }

    private static class Array2DIterator<E> implements Iterator<E> {
        private final Array2D<E> array2D;
        private int currentRow, currentColumn;

        protected void makeStep() {
            currentColumn += 1;
            if (currentColumn >= array2D.width) {
                currentColumn = 0;
                currentRow -= 1;
            }
        }

        public Array2DIterator(Array2D<E> array2D) {
            this.array2D = array2D;

            currentColumn = 0;
            currentRow = array2D.height - 1;
        }

        public boolean hasNext() {
            while (currentRow >= 0 && array2D.getElement(currentRow, currentColumn) == null) {
                makeStep();
            }
            return (currentRow >= 0);
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            E element = array2D.getElement(currentRow, currentColumn);
            makeStep();

            return element;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
