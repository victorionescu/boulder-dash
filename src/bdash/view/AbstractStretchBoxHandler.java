package bdash.view;
/*
 * Abstract class for every mouse handler that uses a stretch.
 * It provides basic functionality, such as the 'elementsInBox' function.
 */
import bdash.model.CaveElementHolder;

import java.awt.*;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStretchBoxHandler implements MouseHandler {
    protected final CaveView caveView;

    protected Point stretchBoxOrigin;
    protected Point stretchBoxTarget;

    public AbstractStretchBoxHandler(CaveView caveView) {
        this.caveView = caveView;
        stretchBoxOrigin = null;
        stretchBoxTarget = null;
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}
    
    /* Returns a list of the elements that have been hit within the box. */
    protected List<CaveElementHolder> elementsInBox(Point boxOrigin, Point boxTarget) {
        CaveElementHolder start = caveView.getElementHolderAt(boxOrigin);
        CaveElementHolder end = caveView.getElementHolderAt(boxTarget);

        List<CaveElementHolder> elementList = new ArrayList<CaveElementHolder>();

        if (start != null && end != null) {
            int rowMin = Math.min(start.getRow(), end.getRow());
            int rowMax = Math.max(start.getRow(), end.getRow());
            int columnMin = Math.min(start.getColumn(), end.getColumn());
            int columnMax = Math.max(start.getColumn(), end.getColumn());

            for (int row = rowMin; row <= rowMax; row += 1) {
                for (int column = columnMin; column <= columnMax; column += 1) {
                    elementList.add(caveView.getCave().getElementHolder(row, column));
                }
            }
        }

        return elementList;
    }

    public abstract void makeActive();

    protected abstract void boxStretchingFinished();
}
