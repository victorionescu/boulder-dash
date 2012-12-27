package bdash.view;

import bdash.controller.GameController;
import bdash.model.*;
import bdash.selection.SelectionManager;
import bdash.selection.SelectionManagerListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.Collection;
import java.util.Iterator;

import javax.swing.*;
import java.awt.*;

public class CaveView extends JPanel {
    /* Cave object that is being monitored by the view and edited by the controller. */
    private Cave cave;

    /* SelectionManager object dealing with the current selection. */
    private final SelectionManager selectionManager;

    /* Listens to the events triggered by either cave or selection manager changes. */
    private final CaveViewListener caveViewListener;

    /* The game controller, responsible with the player's moves. */
    private GameController gameController;

    /* Mouse handlers, one for each tool provided by the toolbar. */
    private final MouseHandler editHandler;
    private final MouseHandler createWallHandler;
    private final MouseHandler createBoulderHandler;
    private final MouseHandler createDiamondHandler;
    private final MouseHandler createDirtHandler;
    private final MouseHandler createPlayerHandler;
    private final MouseHandler playHandler;

    /* Keeps track of the mouse handler corresponding to the selected tool. */
    protected MouseHandler currentMouseHandler;



    public CaveView(Cave cave, SelectionManager selectionManager) {
        this.cave = cave;
        this.selectionManager = selectionManager;

        caveViewListener = new CaveViewListener();
        selectionManager.addListener(caveViewListener);
        cave.addListener(caveViewListener);

        /* Have a MouseEventForwarder pass events to the appropriate handler. */
        MouseEventForwarder forwarder = new MouseEventForwarder();
        addMouseListener(forwarder);
        addMouseMotionListener(forwarder);

        /* Initialize mouse handlers. */
        editHandler = new EditHandler(this);
        createWallHandler = new CreateElementHandler(this, new WallElement(WallElement.WallColor.RED));
        createBoulderHandler = new CreateElementHandler(this, new BoulderElement());
        createDiamondHandler = new CreateElementHandler(this, new DiamondElement());
        createDirtHandler = new CreateElementHandler(this, new DirtElement());
        createPlayerHandler = new CreateElementHandler(this, new PlayerElement());
        playHandler = new NullHandler(this);

        /* Select the initial mouse handler. */
        updateMouseHandler();

        /* Style options. */
        setPreferredSize(new Dimension(cave.getWidth() * 30, cave.getHeight() * 30));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Cave getCave() {
        return cave;
    }

    public SelectionManager getSelectionManager() {
        return selectionManager;
    }

    public CaveElementHolder getElementHolderAt(int x, int y) {
        Iterator<CaveElementHolder> elementHolders = cave.getElementHolders();
        while (elementHolders.hasNext()) {
            CaveElementHolder elementHolder = elementHolders.next();
            if (elementHolder.isHit(x, y)) {
                return elementHolder;
            }
        }
        return null;
    }

    /* Paint the cave view. */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        Color oldColor = g2d.getColor();
        Shape oldClip = g2d.getClip();

        g2d.setColor(Color.WHITE);

        g2d.fillRect(0,
                     0,
                     cave.getWidth() * CaveElementHolder.HOLDER_SIZE_IN_PX,
                     cave.getHeight() * CaveElementHolder.HOLDER_SIZE_IN_PX);

        g2d.clipRect(0,
                     0,
                     cave.getWidth() * CaveElementHolder.HOLDER_SIZE_IN_PX,
                     cave.getHeight() * CaveElementHolder.HOLDER_SIZE_IN_PX);

        Iterator<CaveElementHolder> elementHolders = cave.getElementHolders();
        while (elementHolders.hasNext()) {
            CaveElementHolder elementHolder = elementHolders.next();
            CaveElementHolderPainter.PAINTER.paint(g2d, elementHolder);
        }

        if (!selectionManager.isSelectionEmpty()) {
            Iterator<CaveElementHolder> selectedElements = selectionManager.getSelection();
            while (selectedElements.hasNext()) {
                CaveElementHolder elementHolder = selectedElements.next();
                CaveElementHolderPainter.PAINTER.paintSelection(g2d, elementHolder);
            }
        }

        g2d.setColor(oldColor);
        g2d.setClip(oldClip);
    }

    /* Initialize game controller and save cave state whenever play mode becomes active. */
    private void handlePlayMode() {
        System.out.println("PLAY!!");
        /*if (isPlayActive) {
            cave = caveCopy;
            isPlayActive = false;

            if (gameController == null) {
                throw new IllegalStateException();
            }
            gameController.stopTimer();
        }

        if (selectionManager.getCurrentTool() == SelectionManager.Tools.PLAY) {
            caveCopy = cave;
            cave = caveCopy.clone();
            isPlayActive = true;

            gameController = new GameController(this);
        }*/
    }

    /* Activate the handler corresponding to the currently selected tool. */
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
            case PLAY:
                currentMouseHandler = playHandler;
                break;
            default:
                throw new IllegalArgumentException();
        }

        currentMouseHandler.makeActive();
    }

    /* Listener class keeping track of changes that occur at both Cave and SelectionManager level.
       Implements the Adapter pattern.
     */
    private class CaveViewListener implements CaveListener, SelectionManagerListener {
        public void caveElementWillChange(Cave cave, CaveElementHolder elementHolder) {
            repaint(elementHolder.getColumn() * CaveElementHolder.HOLDER_SIZE_IN_PX,
                    elementHolder.getRow() * CaveElementHolder.HOLDER_SIZE_IN_PX,
                    CaveElementHolder.HOLDER_SIZE_IN_PX,
                    CaveElementHolder.HOLDER_SIZE_IN_PX);
        }

        public void caveElementChanged(Cave cave, CaveElementHolder elementHolder) {
            repaint(elementHolder.getColumn() * CaveElementHolder.HOLDER_SIZE_IN_PX,
                    elementHolder.getRow() * CaveElementHolder.HOLDER_SIZE_IN_PX,
                    CaveElementHolder.HOLDER_SIZE_IN_PX,
                    CaveElementHolder.HOLDER_SIZE_IN_PX);
        }

        public void currentToolChanged(SelectionManager.Tools newCurrentTool) {
            updateMouseHandler();
            //handlePlayMode();
        }

        public void elementsSelected(Collection<CaveElementHolder> elements) {
            repaint();
        }

        public void elementsDeselected(Collection<CaveElementHolder> elements) {
            repaint();
        }

        public void selectionCleared() {
            repaint();
        }
    }

    /* Mouse event forwarder class used to forward mouse events to the appropriate handler. */
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
