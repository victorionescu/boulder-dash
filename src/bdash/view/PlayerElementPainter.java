package bdash.view;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;
import bdash.model.PlayerElement;

import java.awt.*;

public class PlayerElementPainter implements CaveElementPainter {
    public static final PlayerElementPainter INSTANCE = new PlayerElementPainter();

    private PlayerElementPainter() {}

    public void paint(Graphics2D g, CaveElement e, int row, int column) {
        PlayerElement playerElement = (PlayerElement)e;
        g.setColor(Color.GREEN);

        int offsetX = column * CaveElementHolder.HOLDER_SIZE_IN_PX;
        int offsetY = row * CaveElementHolder.HOLDER_SIZE_IN_PX;

        g.drawOval(offsetX, offsetY, CaveElementHolder.HOLDER_SIZE_IN_PX, CaveElementHolder.HOLDER_SIZE_IN_PX);

        int startX = CaveElementHolder.HOLDER_SIZE_IN_PX / 2;
        int startY = CaveElementHolder.HOLDER_SIZE_IN_PX / 2;

        int endX = startX;
        int endY = startY;

        switch (playerElement.getLastDirection()) {
            case EAST:
                endX = 0;
                break;
            case WEST:
                endX = CaveElementHolder.HOLDER_SIZE_IN_PX;
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
