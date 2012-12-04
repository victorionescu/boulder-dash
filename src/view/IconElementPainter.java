package view;

import model.CaveElement;

import javax.swing.*;
import java.awt.*;

public abstract class IconElementPainter implements CaveElementPainter {
    protected abstract ImageIcon getIcon();

    public void paint(Graphics2D g, CaveElement e) {
        int offsetX = e.getX() * 30;
        int offsetY = e.getY() * 30;
        try {
            getIcon().paintIcon(null, g, offsetX, offsetY);
        } catch (NullPointerException npe) {
            System.out.println("FATAL: Could not load icon.");
        }
    }
}
