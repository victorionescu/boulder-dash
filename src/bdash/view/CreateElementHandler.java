package bdash.view;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;
import bdash.model.PlayerElement;
import bdash.model.RemoveDuplicatePlayerVisitor;

import java.awt.event.MouseEvent;
import java.util.Iterator;
/*
 * MouseHandler responsible with creating elements within a box.
 */
public class CreateElementHandler extends AbstractStretchBoxHandler {
    private final CaveElement elementToClone;

    public CreateElementHandler(CaveView caveView, CaveElement elementToClone) {
        super(caveView);
        this.elementToClone = elementToClone;
    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            stretchBoxOrigin = e.getPoint();
            stretchBoxTarget = e.getPoint();

            changeSelection();
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            boxStretchingFinished();
        }
    }

    public void mouseDragged(MouseEvent e) {
        stretchBoxTarget = e.getPoint();

        changeSelection();
    }

    public void changeSelection() {
        caveView.getSelectionManager().clearSelection();
        if (stretchBoxOrigin != null && stretchBoxTarget != null) {
            caveView.getSelectionManager().selectElements(elementsInBox(stretchBoxOrigin, stretchBoxTarget));
        }
    }

    public void boxStretchingFinished() {
        Iterator<CaveElementHolder> selection = caveView.getSelectionManager().getSelection();

        while (selection.hasNext()) {
            CaveElementHolder elementHolder = selection.next();
            CaveElement newElement = elementToClone.clone();

            elementHolder.setCaveElement(newElement);
        }

        RemoveDuplicatePlayerVisitor removeDuplicatePlayerVisitor = new RemoveDuplicatePlayerVisitor();

        Iterator<CaveElementHolder> elementHolders = caveView.getCave().getElementHolders();

        while (elementHolders.hasNext()) {
            CaveElementHolder elementHolder = elementHolders.next();

            if (elementHolder.getCaveElement() != null) {
                elementHolder.getCaveElement().accept(removeDuplicatePlayerVisitor, elementHolder);
            }
        }

        if (removeDuplicatePlayerVisitor.playersEncountered() > 1) {
            caveView.applicationFrame.popDialog("No more than one player allowed on the map!");
        }

        stretchBoxOrigin = null;
        stretchBoxTarget = null;

        changeSelection();
    }

    public void makeActive() {
        stretchBoxOrigin = null;
        stretchBoxTarget = null;

        changeSelection();
    }
}
