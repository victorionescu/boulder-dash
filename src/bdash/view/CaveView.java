package bdash.view;

import bdash.model.*;
import bdash.selection.SelectionManager;
import bdash.selection.SelectionManagerListener;
import bdash.util.WallColor;
import sun.security.x509.CertAttrSet;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

public class CaveView extends JPanel {
    private static final Map<Class<? extends CaveElement>, CaveElementPainter> painters =
            new HashMap<Class<? extends CaveElement>, CaveElementPainter>();

    static {
        painters.put(WallElement.class, WallElementPainter.INSTANCE);
        painters.put(BoulderElement.class, BoulderElementPainter.INSTANCE);
        painters.put(DiamondElement.class, DiamondElementPainter.INSTANCE);
        painters.put(DirtElement.class, DirtElementPainter.INSTANCE);
        painters.put(PlayerElement.class, PlayerElementPainter.INSTANCE);
        painters.put(EmptyElement.class, EmptyElementPainter.INSTANCE);
    }

    private final Cave cave;
    private final SelectionManager selectionManager;
    private final CaveViewListener caveViewListener;
    private final MouseHandler editHandler;
    private final MouseHandler createWallHandler;
    private final MouseHandler createBoulderHandler;
    private final MouseHandler createDiamondHandler;
    private final MouseHandler createDirtHandler;
    private final MouseHandler createPlayerHandler;
    protected MouseHandler currentMouseHandler;



    public CaveView(Cave cave, SelectionManager selectionManager) {
        this.cave = cave;
        this.selectionManager = selectionManager;
        caveViewListener = new CaveViewListener();
        selectionManager.addListener(caveViewListener);
        cave.addListener(caveViewListener);

        MouseEventForwarder forwarder = new MouseEventForwarder();
        addMouseListener(forwarder);
        addMouseMotionListener(forwarder);
        editHandler = new EditHandler(this);
        createWallHandler = new CreateElementHandler(this, new WallElement(cave, new Point(0, 0),
                WallColor.COLORS.get(0)));
        createBoulderHandler = new CreateElementHandler(this, new BoulderElement(cave, new Point(0, 0)));
        createDiamondHandler = new CreateElementHandler(this, new DiamondElement(cave, new Point(0, 0)));
        createDirtHandler = new CreateElementHandler(this, new DirtElement(cave, new Point(0, 0)));
        createPlayerHandler = new CreateElementHandler(this, new PlayerElement(cave, new Point(0, 0),
                PlayerElement.LastDirection.WEST));

        updateMouseHandler();

        setPreferredSize(new Dimension(cave.getWidth() * 30, cave.getHeight() * 30));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Cave getCave() {
        return cave;
    }

    public SelectionManager getSelectionManager() {
        return selectionManager;
    }

    protected CaveElementPainter getCaveElementPainter(CaveElement e) {
        return painters.get(e.getClass());
    }

    public CaveElement getCaveElementAt(int x, int y) {
        Iterator<CaveElement> elements = cave.getElements();
        while (elements.hasNext()) {
            CaveElement e = elements.next();
            if (e.isHit(x, y)) {
                return e;
            }
        }
        return null;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        Color oldColor = g2d.getColor();
        Shape oldClip = g2d.getClip();

        g2d.setColor(Color.WHITE);

        g2d.fillRect(0, 0, cave.getWidth() * 30, cave.getHeight() * 30);
        g2d.clipRect(0, 0, cave.getWidth() * 30, cave.getHeight() * 30);

        Iterator<CaveElement> elements = cave.getElements();
        while (elements.hasNext()) {
            CaveElement e = elements.next();
            getCaveElementPainter(e).paint(g2d, e);
        }

        if (!selectionManager.isSelectionEmpty()) {
            Iterator<CaveElement> selectedElements = selectionManager.getSelection();
            while (selectedElements.hasNext()) {
                CaveElement e = selectedElements.next();
                getCaveElementPainter(e).paintSelection(g2d, e);
            }
        }

        g2d.setColor(oldColor);
        g2d.setClip(oldClip);
    }

    private void updateMouseHandler() {
        switch (selectionManager.getCurrentTool()) {
            case EDIT:
                currentMouseHandler = editHandler;
                break;
            case CREATE_WALL:
                currentMouseHandler = createWallHandler;
                break;
            case CREATE_BOULDER:
                currentMouseHandler = createBoulderHandler;
                break;
            case CREATE_DIAMOND:
                currentMouseHandler = createDiamondHandler;
                break;
            case CREATE_DIRT:
                currentMouseHandler = createDirtHandler;
                break;
            case CREATE_PLAYER:
                currentMouseHandler = createPlayerHandler;
                break;
            default:
                throw new IllegalArgumentException();
        }

        currentMouseHandler.makeActive();
    }

    private class CaveViewListener implements CaveListener, SelectionManagerListener {
        public void caveElementWillChange(Cave cave, CaveElement caveElement) {
            repaint(caveElement.getX() * 30, caveElement.getY() * 30, 30, 30);
        }

        public void caveElementChanged(Cave cave, CaveElement caveElement) {
            repaint(caveElement.getX() * 30, caveElement.getY() * 30, 30, 30);
        }

        public void currentToolChanged(SelectionManager.Tools newCurrentTool) {
            updateMouseHandler();
        }

        public void elementsSelected(Collection<? extends CaveElement> elements) {
            repaint();
        }

        public void elementsDeselected(Collection<? extends CaveElement> elements) {
            repaint();
        }

        public void selectionCleared() {
            repaint();
        }
    }

    private class MouseEventForwarder implements MouseListener, MouseMotionListener {
        public void mouseEntered(MouseEvent e) {
            currentMouseHandler.mouseEntered(e);
        }
        public void mouseExited(MouseEvent e) {
            currentMouseHandler.mouseExited(e);
        }
        public void mouseClicked(MouseEvent e) {
            currentMouseHandler.mouseClicked(e);
        }
        public void mousePressed(MouseEvent e) {
            currentMouseHandler.mousePressed(e);
        }
        public void mouseReleased(MouseEvent e) {
            currentMouseHandler.mouseReleased(e);
        }
        public void mouseDragged(MouseEvent e) {
            currentMouseHandler.mouseDragged(e);
        }
        public void mouseMoved(MouseEvent e) {
            currentMouseHandler.mouseMoved(e);
        }
    }
}
