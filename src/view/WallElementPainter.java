package view;

import model.CaveElement;

import java.awt.*;

public class WallElementPainter implements CaveElementPainter {
    public static final WallElementPainter INSTANCE = new WallElementPainter();

    private WallElementPainter() {
    }

    public void paint(Graphics2D g, CaveElement e) {
    }
}
