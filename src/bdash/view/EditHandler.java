package bdash.view;

import bdash.model.Cave;
import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;
import bdash.model.CaveListener;
import bdash.selection.SelectionManager;

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

        currentMode = Modes.STRETCH_BOX;

        dragBoxOrigin = null;
        dragBoxTarget = null;

        caveView.getCave().addListener(this);
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            CaveElementHolder hitElement = caveView.getElementHolderAt(e.getPoint());

            if (selection.contains(hitElement)){
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
            if (currentMode == Modes.STRETCH_BOX) {
                boxStretchingFinished();
            } else if (currentMode == Modes.DRAG_BOX) {
                boxDraggingFinished();
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (currentMode == Modes.STRETCH_BOX) {
            stretchBoxTarget = e.getPoint();
        } else if (currentMode == Modes.DRAG_BOX) {
            dragBoxTarget = e.getPoint();
        }

        changeSelection();
    }

    public void changeSelection() {
        caveView.getSelectionManager().clearSelection();

        if (currentMode == Modes.STRETCH_BOX) {
            caveView.getSelectionManager().selectElements(elementsInBox(stretchBoxOrigin, stretchBoxTarget));
        } else {
            caveView.getSelectionManager().selectElements(selection);

            if (dragBoxOrigin != null && dragBoxTarget != null) {
                int columnOffset = (dragBoxTarget.x - dragBoxOrigin.x) / CaveElementHolder.HOLDER_SIZE_IN_PX;
                int rowOffset = (dragBoxTarget.y - dragBoxOrigin.y) / CaveElementHolder.HOLDER_SIZE_IN_PX;


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

    public void caveElementWillChange(Cave cave, CaveElementHolder elementHolder) {}

    public void caveElementChanged(Cave cave, CaveElementHolder elementHolder) {
        /*
            Whenever a tool that is different from EDIT changes an element which is part
            of the current selection, we make sure to remove the corresponding element holder
            from the selection.
         */
        if (caveView.getSelectionManager().getCurrentTool() != SelectionManager.Tools.EDIT ||
                elementHolder.getCaveElement() == null) {
            selection.remove(elementHolder);
        }
    }

    public void gameLost() {}

    protected void boxStretchingFinished() {
        currentMode = Modes.DRAG_BOX;

        selection.clear();

        for (CaveElementHolder selectedHolder : elementsInBox(stretchBoxOrigin, stretchBoxTarget)) {
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

        selection.clear();

        stretchBoxOrigin = null;
        stretchBoxTarget = null;

        dragBoxOrigin = null;
        dragBoxTarget = null;

        currentMode = Modes.STRETCH_BOX;

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

            holdersToElements.put(selectedHolder, null);
        }

        for (Map.Entry<CaveElementHolder, CaveElement> entry : holdersToElements.entrySet()) {
            entry.getKey().setCaveElement(entry.getValue());
        }
    }
}
