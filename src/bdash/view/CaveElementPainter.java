package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;
/*
 * Interface for the cave element painter.
 */
public interface CaveElementPainter {
	/* Paint an element at (row, column) */
    void paint(Graphics2D g, CaveElement e, int row, int column);

    /* Paint a selection on an element at (row, column) */
    void paintSelection(Graphics2D g, CaveElement e, int row, int column);
}
