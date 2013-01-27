package bdash.view;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;

import javax.swing.*;
import java.awt.*;
/*
 * Abstract painter class for elements that are drawn as icons, such as boulders, diamonds and dirt.
 */
public abstract class IconElementPainter implements CaveElementPainter {
    abstract String imagePath();

    private ImageIcon getIcon() {
        java.net.URL imageURL = getClass().getResource(imagePath());
        if (imageURL != null) {
            return new ImageIcon(imageURL);
        } else {
            System.out.println("ERROR: Resource " + imagePath() + " not found.");
            return null;
        }
    }

    public void paint(Graphics2D g, CaveElement e, int row, int column) {
        int offsetX = column * CaveElementHolder.HOLDER_SIZE_IN_PX;
        int offsetY = row * CaveElementHolder.HOLDER_SIZE_IN_PX;

        g.setColor(Color.WHITE);
        g.fillRect(offsetX, offsetY, CaveElementHolder.HOLDER_SIZE_IN_PX, CaveElementHolder.HOLDER_SIZE_IN_PX);

        try {
            getIcon().paintIcon(null, g, offsetX, offsetY);
        } catch (NullPointerException npe) {
            System.out.println("FATAL: Could not load icon.");
        }
    }

    public void paintSelection(Graphics2D g, CaveElement e, int row, int column) {}
}
