package view;

import model.*;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

public class CaveView extends JPanel {
    private static final Map<Class<? extends CaveElement>, CaveElementPainter> painters =
            new HashMap<Class<? extends CaveElement>, CaveElementPainter>();

    static {
        painters.put(WallElement.class, WallElementPainter.INSTANCE);
        painters.put(BoulderElement.class, BoulderElementPainter.INSTANCE);
        painters.put(DiamondElement.class, DiamondElementPainter.INSTANCE);
        painters.put(DirtElement.class, DirtElementPainter.INSTANCE);
        painters.put(PlayerElement.class, PlayerElementPainter.INSTANCE);
    }

    protected CaveElementPainter getCaveElementPainter(CaveElement e) {
        return painters.get(e.getClass());
    }

    private final Cave cave;

    public CaveView(Cave cave) {
        this.cave = cave;
        setPreferredSize(new Dimension(cave.getWidth() * 30, cave.getHeight() * 30));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        Color oldColor = g2d.getColor();
        Shape oldClip = g2d.getClip();

        g2d.setColor(Color.WHITE);

        g2d.fillRect(0, 0, cave.getWidth() * 30, cave.getHeight() * 30);
        g2d.clipRect(0, 0, cave.getWidth() * 30, cave.getHeight() * 30);

        for (CaveElement e : cave.getElements()) {
            System.out.println("we get  one here");
            getCaveElementPainter(e).paint(g2d, e);
        }

        g2d.setColor(oldColor);
        g2d.setClip(oldClip);
        System.out.println("painted.");
    }
}
