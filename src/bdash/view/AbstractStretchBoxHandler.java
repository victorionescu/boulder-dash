package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractStretchBoxHandler implements MouseHandler {
    protected final CaveView caveView;

    protected Point stretchBoxOrigin;
    protected Point stretchBoxTarget;

    public AbstractStretchBoxHandler(CaveView caveView) {
        this.caveView = caveView;
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    protected List<CaveElement> elementsInBox(Point boxOrigin, Point boxTarget) {
        CaveElement start = caveView.getCaveElementAt(boxOrigin.x, boxOrigin.y);
        CaveElement end = caveView.getCaveElementAt(boxTarget.x, boxTarget.y);

        List<CaveElement> elementList = new ArrayList<CaveElement>();

        if (start != null && end != null) {
            int xMin = Math.min(start.getX(), end.getX());
            int xMax = Math.max(start.getX(), end.getX());
            int yMin = Math.min(start.getY(), end.getY());
            int yMax = Math.max(start.getY(), end.getY());

            for (int i = xMin; i <= xMax; i += 1) {
                for (int j = yMin; j <= yMax; j += 1) {
                    elementList.add(caveView.getCave().getElement(i, j));
                }
            }
        }

        return elementList;
    }

    protected void selectElements(Point boxOrigin, Point boxTarget) {
        caveView.getSelectionManager().selectElements(elementsInBox(boxOrigin, boxTarget));
    }

    protected void deselectElements(Point boxOrigin, Point boxTarget) {
        caveView.getSelectionManager().deselectElements(elementsInBox(boxOrigin, boxTarget));
    }

    protected abstract void boxStretchingFinished();
}
