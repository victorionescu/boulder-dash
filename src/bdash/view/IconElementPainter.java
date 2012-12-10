package bdash.view;

import bdash.model.CaveElement;

import javax.swing.*;
import java.awt.*;

public abstract class IconElementPainter extends BlueSelectionPainter {
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

    public void paint(Graphics2D g, CaveElement e) {
        int offsetX = e.getX() * 30;
        int offsetY = e.getY() * 30;

        g.setColor(Color.WHITE);
        g.fillRect(offsetX, offsetY, 30, 30);

        try {
            getIcon().paintIcon(null, g, offsetX, offsetY);
        } catch (NullPointerException npe) {
            System.out.println("FATAL: Could not load icon.");
        }
    }
}