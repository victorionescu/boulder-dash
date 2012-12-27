package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;

public class EmptyElementPainter implements CaveElementPainter {
    public static final EmptyElementPainter INSTANCE = new EmptyElementPainter();

    private EmptyElementPainter() {}

    public void paint(Graphics2D g, CaveElement e, int row, int column) {}

    public void paintSelection(Graphics2D g, CaveElement e, int row, int column) {}
}
