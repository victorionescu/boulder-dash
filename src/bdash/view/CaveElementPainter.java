package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;

public interface CaveElementPainter {
    void paint(Graphics2D g, CaveElement e);

    void paintSelection(Graphics2D g, CaveElement e);
}
