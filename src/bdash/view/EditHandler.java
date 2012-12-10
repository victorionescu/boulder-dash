package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;
import java.awt.event.MouseEvent;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EditHandler extends AbstractStretchBoxHandler {
    private static enum Modes { STRETCH_BOX, DRAG_BOX }

    private Point dragBoxOrigin;
    private Point dragBoxTarget;
    private Modes currentMode;

    public EditHandler(CaveView caveView) {
        super(caveView);
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            CaveElement hitElement = caveView.getCaveElementAt(e.getPoint().x, e.getPoint().y);
            if (hitElement == null) {
                System.out.println("DOESN'T HIT ELEMENT");
                throw new IllegalStateException();
            }

            if (caveView.getSelectionManager().isSelected(hitElement)){
                if (stretchBoxOrigin == null || stretchBoxTarget == null) {
                    throw new IllegalStateException();
                }

                currentMode = Modes.DRAG_BOX;

                dragBoxOrigin = e.getPoint();
                dragBoxTarget = e.getPoint();
            } else {
                currentMode = Modes.STRETCH_BOX;

                dragBoxOrigin = null;
                dragBoxTarget = null;

                stretchBoxOrigin = e.getPoint();
                stretchBoxTarget = e.getPoint();
            }

            changeSelection();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (currentMode == null) {
                throw new IllegalStateException();
            }

            if (currentMode == Modes.STRETCH_BOX) {
                boxStretchingFinished();
            } else {
                boxDraggingFinished();
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (currentMode == null) {
            throw new IllegalStateException();
        }

        if (currentMode == Modes.STRETCH_BOX) {
            stretchBoxTarget = e.getPoint();
        } else {
            dragBoxTarget = e.getPoint();
        }

        changeSelection();
    }

    public void changeSelection() {
        caveView.getSelectionManager().clearSelection();

        if (currentMode == null) {
            return;
        }

        if (stretchBoxOrigin == null || stretchBoxTarget == null) {
            throw new IllegalStateException();
        }

        selectElements(stretchBoxOrigin, stretchBoxTarget);

        if (currentMode == Modes.DRAG_BOX) {
            if (dragBoxOrigin == null || dragBoxTarget == null) {
                throw new IllegalStateException();
            }

            int offsetX = dragBoxTarget.x - dragBoxOrigin.x;
            int offsetY = dragBoxTarget.y - dragBoxOrigin.y;

            Point newOrigin = new Point(stretchBoxOrigin.x + offsetX, stretchBoxOrigin.y + offsetY);
            Point newTarget = new Point(stretchBoxTarget.x + offsetX, stretchBoxTarget.y + offsetY);

            selectElements(newOrigin, newTarget);
        }
    }

    public void makeActive() {
        changeSelection();
    }

    protected void boxStretchingFinished() {
        stretchBoxOrigin = new Point(30 * (stretchBoxOrigin.x / 30), 30 * (stretchBoxOrigin.y / 30));
        stretchBoxTarget = new Point(30 * (stretchBoxTarget.x / 30), 30 * (stretchBoxTarget.y / 30));
    }

    protected void boxDraggingFinished() {
        int offsetX = dragBoxTarget.x - dragBoxOrigin.x;
        int offsetY = dragBoxTarget.y - dragBoxOrigin.y;

        Point newStretchBoxOrigin = new Point(stretchBoxOrigin.x + offsetX, stretchBoxOrigin.y + offsetY);
        Point newStretchBoxTarget = new Point(stretchBoxTarget.x + offsetX, stretchBoxTarget.y + offsetY);

        List<CaveElement> elementsToMove = elementsInBox(stretchBoxOrigin, stretchBoxTarget);

        List<CaveElement> elementsToReplace = elementsInBox(newStretchBoxOrigin, newStretchBoxTarget);

        if (!elementsToReplace.isEmpty()) {
            moveElements(elementsToMove.iterator(),
                         elementsToReplace.get(0).getX() - elementsToMove.get(0).getX(),
                         elementsToReplace.get(0).getY() - elementsToMove.get(0).getY());
        }

        stretchBoxOrigin = newStretchBoxOrigin;
        stretchBoxTarget = newStretchBoxTarget;

        dragBoxOrigin = null;
        dragBoxTarget = null;

        currentMode = Modes.STRETCH_BOX;

        changeSelection();
    }

    private void moveElements(Iterator<CaveElement> elementsToMove, int offsetX, int offsetY) {
        while (elementsToMove.hasNext()) {
            CaveElement element = elementsToMove.next();

            CaveElement newElement = element.clone();
            newElement.setX(element.getX() + offsetX);
            newElement.setY(element.getY() + offsetY);

            element.getCave().setElement(element.getX() + offsetX, element.getY() + offsetY, newElement);
        }
    }
}
