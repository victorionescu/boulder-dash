package bdash.view;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;
import bdash.model.PlayerElement;

import java.awt.*;
/*
    Painter for player element.
 */
public class PlayerElementPainter implements CaveElementPainter {
    public static final PlayerElementPainter INSTANCE = new PlayerElementPainter();

    private PlayerElementPainter() {}

    public void paint(Graphics2D g, CaveElement e, int row, int column) {
        PlayerElement playerElement = (PlayerElement)e;
        g.setColor(Color.GREEN);

        int offsetX = column * CaveElementHolder.HOLDER_SIZE_IN_PX;
        int offsetY = row * CaveElementHolder.HOLDER_SIZE_IN_PX;

        g.drawOval(offsetX, offsetY, CaveElementHolder.HOLDER_SIZE_IN_PX - 1, CaveElementHolder.HOLDER_SIZE_IN_PX - 1);

        int startX = CaveElementHolder.HOLDER_SIZE_IN_PX / 2;
        int startY = CaveElementHolder.HOLDER_SIZE_IN_PX / 2;

        int endX = startX;
        int endY = startY;

        switch (playerElement.getDirection()) {
            case EAST:
                endX = CaveElementHolder.HOLDER_SIZE_IN_PX;
                break;
            case WEST:
                endX = 0;
                break;
            case NORTH:
                endY = 0;
                break;
            case SOUTH:
                endY = CaveElementHolder.HOLDER_SIZE_IN_PX;
                break;
            default:
        }

        g.drawLine(offsetX + startX, offsetY + startY, offsetX + endX, offsetY + endY);
    }

    public void paintSelection(Graphics2D g, CaveElement e, int row, int column) {}
}
