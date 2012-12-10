package bdash.view;

import bdash.model.CaveElement;

import java.awt.*;
import java.awt.event.MouseEvent;

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
        if (currentMode == null) {
            throw new IllegalStateException();
        }

        caveView.getSelectionManager().clearSelection();
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

    public void reset() {
        stretchBoxOrigin = null;
        stretchBoxTarget = null;
        dragBoxOrigin = null;
        dragBoxTarget = null;
        currentMode = null;
    }

    public void makeActive() {
    }

    protected void boxStretchingFinished() {
        stretchBoxOrigin = new Point(30 * (stretchBoxOrigin.x / 30), 30 * (stretchBoxOrigin.y / 30));
        stretchBoxTarget = new Point(30 * (stretchBoxTarget.x / 30), 30 * (stretchBoxTarget.y / 30));
    }

    protected void boxDraggingFinished() {
        int offsetX = dragBoxTarget.x - dragBoxOrigin.x;
        int offsetY = dragBoxTarget.y - dragBoxOrigin.y;

        stretchBoxOrigin = new Point(stretchBoxOrigin.x + offsetX, stretchBoxOrigin.y + offsetY);
        stretchBoxTarget = new Point(stretchBoxTarget.x + offsetX, stretchBoxTarget.y + offsetY);

        dragBoxOrigin = null;
        dragBoxTarget = null;

        currentMode = Modes.STRETCH_BOX;

        changeSelection();
    }
}
