package bdash.view;

import bdash.model.Cave;
import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;
import bdash.model.CaveListener;

import java.awt.*;
import java.awt.event.MouseEvent;

import java.util.*;
import java.util.List;

public class EditHandler extends AbstractStretchBoxHandler implements CaveListener {
    private static enum Modes { STRETCH_BOX, DRAG_BOX }

    private Point dragBoxOrigin;
    private Point dragBoxTarget;
    private Modes currentMode;
    private final List<CaveElementHolder> selection;

    public EditHandler(CaveView caveView) {
        super(caveView);

        selection = new ArrayList<CaveElementHolder>();
        currentMode = null;
        dragBoxOrigin = null;
        dragBoxTarget = null;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            CaveElementHolder hitElement = caveView.getElementHolderAt(e.getPoint().x, e.getPoint().y);
            if (hitElement == null) {
                throw new IllegalStateException();
            }

            if (caveView.getSelectionManager().isSelected(hitElement)){
                if (stretchBoxOrigin == null || stretchBoxTarget == null) {
                    throw new IllegalStateException();
                }

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

        if (currentMode == Modes.STRETCH_BOX) {
            selectElementsInBox(stretchBoxOrigin, stretchBoxTarget);
        } else {
            caveView.getSelectionManager().selectElements(selection);

            if (dragBoxOrigin != null && dragBoxTarget != null) {
                int columnOffset = (dragBoxTarget.x - dragBoxOrigin.x) / 30;
                int rowOffset = (dragBoxTarget.y - dragBoxOrigin.y) / 30;


                List<CaveElementHolder> elementsToSelect = new ArrayList<CaveElementHolder>();

                for (CaveElementHolder selectedHolder : selection) {
                    int newRow = selectedHolder.getRow() + rowOffset;
                    int newColumn = selectedHolder.getColumn() + columnOffset;

                    if (newRow >= 0 && newRow < caveView.getCave().getHeight() &&
                            newColumn >= 0 && newColumn < caveView.getCave().getWidth()) {
                        elementsToSelect.add(caveView.getCave().getElementHolder(newRow, newColumn));
                    }
                }

                caveView.getSelectionManager().selectElements(elementsToSelect);
            }
        }
    }

    public void makeActive() {
        changeSelection();
    }

    public void caveElementWillChange(Cave cave, CaveElementHolder elementHolder) {
        selection.remove(elementHolder);
    }

    public void caveElementChanged(Cave cave, CaveElementHolder elementHolder) {
        selection.remove(elementHolder);
    }

    protected void boxStretchingFinished() {
        currentMode = Modes.DRAG_BOX;

        selection.clear();
        List<CaveElementHolder> boxElements = elementsInBox(stretchBoxOrigin, stretchBoxTarget);
        for (CaveElementHolder selectedHolder : boxElements) {
            if (selectedHolder.getCaveElement() != null) {
                selection.add(selectedHolder);
            }
        }

        changeSelection();
    }

    protected void boxDraggingFinished() {
        int columnOffset = (dragBoxTarget.x - dragBoxOrigin.x) / CaveElementHolder.HOLDER_SIZE_IN_PX;
        int rowOffset = (dragBoxTarget.y - dragBoxOrigin.y) / CaveElementHolder.HOLDER_SIZE_IN_PX;

        moveElements(rowOffset, columnOffset);

        stretchBoxOrigin = null;
        stretchBoxTarget = null;

        dragBoxOrigin = null;
        dragBoxTarget = null;

        currentMode = null;

        changeSelection();
    }

    private void moveElements(int rowOffset, int columnOffset) {
        Map<CaveElementHolder, CaveElement>  holdersToElements = new HashMap<CaveElementHolder, CaveElement>();

        for (CaveElementHolder selectedHolder : selection) {

            if (selectedHolder.getCaveElement() == null) {
                throw new IllegalStateException();
            }

            int newRow = selectedHolder.getRow() + rowOffset;
            int newColumn = selectedHolder.getColumn() + columnOffset;

            if (newRow >= 0 && newRow < caveView.getCave().getHeight() &&
                    newColumn >= 0 && newColumn < caveView.getCave().getWidth()) {
                holdersToElements.put(caveView.getCave().getElementHolder(newRow, newColumn),
                                      selectedHolder.getCaveElement().clone());
            }

            selectedHolder.setCaveElement(null);
        }

        for (Map.Entry<CaveElementHolder, CaveElement> entry : holdersToElements.entrySet()) {
            entry.getKey().setCaveElement(entry.getValue());
        }
    }
}
