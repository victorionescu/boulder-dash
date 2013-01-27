package bdash.view;

import java.awt.event.MouseEvent;
/*
 * MouseHandler implementing the null object pattern.
 * Used in play mode, when mouse is deactivated.
 */
public class NullHandler implements MouseHandler {
    private final CaveView caveView;

    public NullHandler(CaveView caveView) {
        this.caveView = caveView;
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) {}

    public void makeActive() {
        caveView.getSelectionManager().clearSelection();
    }
}
