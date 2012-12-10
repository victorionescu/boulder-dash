package bdash.view;

import bdash.model.CaveElement;

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

            stretchBoxOrigin = null;
            stretchBoxTarget = null;

            changeSelection();
        }
    }

    public void mouseDragged(MouseEvent e) {
        stretchBoxTarget = e.getPoint();

        changeSelection();
    }

    public void changeSelection() {
        caveView.getSelectionManager().clearSelection();
        if (stretchBoxOrigin != null && stretchBoxTarget != null) {
            selectElements(stretchBoxOrigin, stretchBoxTarget);
        }
    }

    public void boxStretchingFinished() {
        Iterator<CaveElement> selection = caveView.getSelectionManager().getSelection();
        while (selection.hasNext()) {
            CaveElement element = selection.next();
            CaveElement newElement = elementToClone.clone();
            newElement.setX(element.getX());
            newElement.setY(element.getY());
            element.getCave().setElement(element.getX(), element.getY(), newElement);
        }

        changeSelection();
    }

    public void makeActive() {
        stretchBoxOrigin = null;
        stretchBoxTarget = null;

        changeSelection();
    }
}
