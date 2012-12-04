package view;

import model.CaveElement;
import model.WallElement;

import java.awt.*;

public class WallElementPainter implements CaveElementPainter {
    public static final WallElementPainter INSTANCE = new WallElementPainter();

    private WallElementPainter() {
    }

    public void paint(Graphics2D g, CaveElement e) {
        WallElement wallElement = (WallElement)e;
        g.setColor(wallElement.getColor());

        int offsetX = wallElement.getX() * 30;
        int offsetY = wallElement.getY() * 30;

        g.fillRect(offsetX, offsetY, 30, 30);
    }
}
