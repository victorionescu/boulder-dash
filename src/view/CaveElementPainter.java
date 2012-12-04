package view;

import model.CaveElement;

import java.awt.*;

public interface CaveElementPainter {
    void paint(Graphics2D g, CaveElement e);
}
