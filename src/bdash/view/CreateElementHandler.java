package bdash.view;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;

import java.awt.event.MouseEvent;
import java.util.Iterator;

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
