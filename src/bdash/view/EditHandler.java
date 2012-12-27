package bdash.view;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;

import java.awt.*;
import java.awt.event.MouseEvent;

import java.util.*;
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
            CaveElementHolder hitElement = caveView.getElementHolderAt(e.getPoint().x, e.getPoint().y);
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
        stretchBoxOrigin =
            new Point(CaveElementHolder.HOLDER_SIZE_IN_PX * (stretchBoxOrigin.x / CaveElementHolder.HOLDER_SIZE_IN_PX),
                      CaveElementHolder.HOLDER_SIZE_IN_PX * (stretchBoxOrigin.y / CaveElementHolder.HOLDER_SIZE_IN_PX));

        stretchBoxTarget =
            new Point(CaveElementHolder.HOLDER_SIZE_IN_PX * (stretchBoxTarget.x / CaveElementHolder.HOLDER_SIZE_IN_PX),
                      CaveElementHolder.HOLDER_SIZE_IN_PX * (stretchBoxTarget.y / CaveElementHolder.HOLDER_SIZE_IN_PX));

        List<CaveElementHolder> selectedEmptyHolders = new ArrayList<CaveElementHolder>();
        Iterator<CaveElementHolder> selection = caveView.getSelectionManager().getSelection();
        while (selection.hasNext()) {
            CaveElementHolder elementHolder = selection.next();
            if (elementHolder.getCaveElement() == null) {
                selectedEmptyHolders.add(elementHolder);
            }
        }
        caveView.getSelectionManager().deselectElements(selectedEmptyHolders);
    }

    protected void boxDraggingFinished() {
        int offsetX = dragBoxTarget.x - dragBoxOrigin.x;
        int offsetY = dragBoxTarget.y - dragBoxOrigin.y;

        Point newStretchBoxOrigin = new Point(stretchBoxOrigin.x + offsetX, stretchBoxOrigin.y + offsetY);
        Point newStretchBoxTarget = new Point(stretchBoxTarget.x + offsetX, stretchBoxTarget.y + offsetY);

        List<CaveElementHolder> elementsToMove = elementsInBox(stretchBoxOrigin, stretchBoxTarget);

        List<CaveElementHolder> elementsToReplace = elementsInBox(newStretchBoxOrigin, newStretchBoxTarget);

        if (!elementsToReplace.isEmpty()) {
            moveElements(elementsToMove.iterator(),
                         elementsToReplace.get(0).getRow() - elementsToMove.get(0).getRow(),
                         elementsToReplace.get(0).getColumn() - elementsToMove.get(0).getColumn());
        }

        stretchBoxOrigin = newStretchBoxOrigin;
        stretchBoxTarget = newStretchBoxTarget;

        dragBoxOrigin = null;
        dragBoxTarget = null;

        currentMode = Modes.STRETCH_BOX;

        changeSelection();
    }

    private void moveElements(Iterator<CaveElementHolder> elementsToMove, int rowOffset, int columnOffset) {
        Map<CaveElementHolder, CaveElement>  holdersToElements = new HashMap<CaveElementHolder, CaveElement>();

        while (elementsToMove.hasNext()) {
            CaveElementHolder elementHolder = elementsToMove.next();

            CaveElementHolder targetHolder =
                    elementHolder.getCave().getElementHolder(elementHolder.getRow() + rowOffset,
                                                             elementHolder.getColumn() + columnOffset);

            if (elementHolder.getCaveElement() != null) {
                holdersToElements.put(targetHolder, elementHolder.getCaveElement().clone());
            } else {
                holdersToElements.put(targetHolder, null);
            }

            elementHolder.setCaveElement(null);
        }

        for (Map.Entry<CaveElementHolder, CaveElement> entry : holdersToElements.entrySet()) {
            entry.getKey().setCaveElement(entry.getValue());
        }
    }
}
