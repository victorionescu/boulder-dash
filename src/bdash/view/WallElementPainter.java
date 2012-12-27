package bdash.view;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;
import bdash.model.WallElement;

import java.awt.*;

public class WallElementPainter implements CaveElementPainter {
    public static final WallElementPainter INSTANCE = new WallElementPainter();

    private WallElementPainter() {
    }

    public void paint(Graphics2D g, CaveElement e, int row, int column) {
        WallElement wallElement = (WallElement)e;
        g.setColor(wallElement.getWallColor().getColor());

        int offsetX = column * CaveElementHolder.HOLDER_SIZE_IN_PX;
        int offsetY = row * CaveElementHolder.HOLDER_SIZE_IN_PX;

        g.fillRect(offsetX, offsetY, CaveElementHolder.HOLDER_SIZE_IN_PX, CaveElementHolder.HOLDER_SIZE_IN_PX);
    }

    public void paintSelection(Graphics2D g, CaveElement e, int row, int column) {}
}
