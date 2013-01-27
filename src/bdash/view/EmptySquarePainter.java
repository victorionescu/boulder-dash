package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;
/*
 * Painter class used for empty squares.
 */
public class EmptySquarePainter implements CaveElementPainter {
    public static final EmptySquarePainter INSTANCE = new EmptySquarePainter();

    private EmptySquarePainter() {}

    public void paint(Graphics2D g, CaveElement e, int row, int column) {}

    public void paintSelection(Graphics2D g, CaveElement e, int row, int column) {}
}
