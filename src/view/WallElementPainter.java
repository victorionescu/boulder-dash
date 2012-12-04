package view;

import javax.swing.*;

public class WallElementPainter extends IconElementPainter {
    public static final WallElementPainter INSTANCE = new WallElementPainter();

    private WallElementPainter() {
    }

    protected ImageIcon getIcon() {
        return null;
    }
}
