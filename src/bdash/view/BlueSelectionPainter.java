package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;

public abstract class BlueSelectionPainter implements CaveElementPainter {
    public void paintSelection(Graphics2D g, CaveElement e) {
        int offsetX = e.getX() * 30;
        int offsetY = e.getY() * 30;

        Composite oldComposite = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));

        g.setColor(Color.BLUE);
        g.fillRect(offsetX, offsetY, 30, 30);

        g.setComposite(oldComposite);
    }

    public abstract void paint(Graphics2D g, CaveElement e);
}
