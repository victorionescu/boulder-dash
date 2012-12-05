package view;

import model.*;

import java.awt.*;

public class PlayerElementPainter implements CaveElementPainter {
    public static PlayerElementPainter INSTANCE = new PlayerElementPainter();

    private PlayerElementPainter() {}

    public void paint(Graphics2D g, CaveElement e) {
        g.setColor(Color.GREEN);
        g.drawOval(e.getX() * 30, e.getY() * 30, 30, 30);
    }
}
