package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;

public class EmptyElementPainter extends BlueSelectionPainter {
    public static final EmptyElementPainter INSTANCE = new EmptyElementPainter();

    private EmptyElementPainter() {}

    public void paint(Graphics2D g, CaveElement e) {
    }
}
