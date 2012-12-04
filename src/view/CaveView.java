package view;

import model.Cave;
import model.CaveElement;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

public class CaveView extends JPanel {
    private static final Map<Class<? extends CaveElement>, CaveElementPainter> painters =
            new HashMap<Class<? extends CaveElement>, CaveElementPainter>();

    private final Cave cave;

    public CaveView(Cave cave) {
        this.cave = cave;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        Color oldColor = g2d.getColor();
        Shape oldClip = g2d.getClip();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(oldColor);
        g2d.setClip(oldClip);
    }
}
