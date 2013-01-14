package bdash.view;

import bdash.model.*;

import java.awt.*;

import java.util.Map;
import java.util.HashMap;

public class CaveElementHolderPainter {
    public static final CaveElementHolderPainter PAINTER = new CaveElementHolderPainter();

    protected static final Map<Class<? extends CaveElement>, CaveElementPainter> painters =
            new HashMap<Class<? extends CaveElement>, CaveElementPainter>();

    static {
        painters.put(WallElement.class, WallElementPainter.INSTANCE);
        painters.put(BoulderElement.class, BoulderElementPainter.INSTANCE);
        painters.put(DiamondElement.class, DiamondElementPainter.INSTANCE);
        painters.put(DirtElement.class, DirtElementPainter.INSTANCE);
        painters.put(PlayerElement.class, PlayerElementPainter.INSTANCE);
    }

    protected CaveElementHolderPainter() {}

    protected CaveElementPainter getCaveElementPainter(CaveElement element) {
        if (element != null) {
            return painters.get(element.getClass());
        } else {
            return EmptySquarePainter.INSTANCE;
        }
    }

    public void paint(Graphics2D g, CaveElementHolder elementHolder) {
        CaveElement element = elementHolder.getCaveElement();
        getCaveElementPainter(element).paint(g, element, elementHolder.getRow(), elementHolder.getColumn());
    }

    public void paintSelection(Graphics2D g, CaveElementHolder elementHolder) {
        CaveElement element = elementHolder.getCaveElement();
        getCaveElementPainter(element).paintSelection(g, element, elementHolder.getRow(), elementHolder.getColumn());

        int offsetX = elementHolder.getColumn() * CaveElementHolder.HOLDER_SIZE_IN_PX;
        int offsetY = elementHolder.getRow() * CaveElementHolder.HOLDER_SIZE_IN_PX;

        Composite oldComposite = g.getComposite();
        Color oldColor = g.getColor();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f));
        g.setColor(Color.BLUE);
        g.fillRect(offsetX, offsetY, CaveElementHolder.HOLDER_SIZE_IN_PX, CaveElementHolder.HOLDER_SIZE_IN_PX);

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g.setColor(Color.WHITE);
        g.drawLine(offsetX + 2,
                   offsetY + 2,
                   offsetX + CaveElementHolder.HOLDER_SIZE_IN_PX - 3,
                   offsetY + 2);
        g.drawLine(offsetX + 2,
                   offsetY + CaveElementHolder.HOLDER_SIZE_IN_PX - 3,
                   offsetX + CaveElementHolder.HOLDER_SIZE_IN_PX - 3,
                   offsetY + CaveElementHolder.HOLDER_SIZE_IN_PX - 3);
        g.drawLine(offsetX + 2,
                   offsetY + 2,
                   offsetX + 2,
                   offsetY + CaveElementHolder.HOLDER_SIZE_IN_PX - 3);
        g.drawLine(offsetX + CaveElementHolder.HOLDER_SIZE_IN_PX - 3,
                   offsetY + 2,
                   offsetX + CaveElementHolder.HOLDER_SIZE_IN_PX - 3,
                   offsetY + CaveElementHolder.HOLDER_SIZE_IN_PX - 3);

        g.setComposite(oldComposite);
        g.setColor(oldColor);
    }
}
