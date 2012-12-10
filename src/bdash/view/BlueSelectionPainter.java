package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;

public abstract class BlueSelectionPainter implements CaveElementPainter {
    public void paintSelection(Graphics2D g, CaveElement e) {
        int offsetX = e.getX() * 30;
        int offsetY = e.getY() * 30;

        Composite oldComposite = g.getComposite();
        Color oldColor = g.getColor();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
        g.setColor(Color.BLUE);
        g.fillRect(offsetX, offsetY, 30, 30);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g.setColor(Color.WHITE);
        g.drawLine(offsetX + 2, offsetY + 2, offsetX + 27, offsetY + 2);
        g.drawLine(offsetX + 2, offsetY + 27, offsetX + 27, offsetY + 27);
        g.drawLine(offsetX + 2, offsetY + 2, offsetX + 2, offsetY + 27);
        g.drawLine(offsetX + 27, offsetY + 2, offsetX + 27, offsetY + 27);

        g.setComposite(oldComposite);
        g.setColor(oldColor);
    }

    public abstract void paint(Graphics2D g, CaveElement e);
}
