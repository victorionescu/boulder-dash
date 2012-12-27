package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;

public interface CaveElementPainter {
    void paint(Graphics2D g, CaveElement e, int row, int column);

    void paintSelection(Graphics2D g, CaveElement e, int row, int column);
}
