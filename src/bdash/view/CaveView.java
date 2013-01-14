package bdash.view;

import bdash.ApplicationFrame;
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
    /* Parent ApplicationFrame. */
    private final ApplicationFrame applicationFrame;

    /* Cave object that is used for editing the contents. */
    private Cave cave;

    /* SelectionManager object dealing with the current selection. */
    private final SelectionManager selectionManager;

    /* Listens to the events triggered by either cave or selection manager changes. */
    private final CaveViewListener caveViewListener;

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



    public CaveView(ApplicationFrame applicationFrame, Cave cave, SelectionManager selectionManager) {
        this.applicationFrame = applicationFrame;
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

    public CaveElementHolder getElementHolderAt(Point point) {
        Iterator<CaveElementHolder> elementHolders = cave.getElementHolders();
        while (elementHolders.hasNext()) {
            CaveElementHolder elementHolder = elementHolders.next();
            if (elementHolder.isHit(point)) {
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

        if (selectionManager.getGameState() == SelectionManager.GameStates.WON) {
            drawNotificationScreen(g2d, "Game won!");
        } else if (selectionManager.getGameState() == SelectionManager.GameStates.LOST) {
            drawNotificationScreen(g2d, "Game lost!");
        }

        g2d.setColor(oldColor);
        g2d.setClip(oldClip);
    }

    private void drawNotificationScreen(Graphics2D g, String message) {
        Color oldColor = g.getColor();
        Shape oldClip = g.getClip();
        Composite oldComposite = g.getComposite();
        Font oldFont = g.getFont();

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g.setColor(Color.BLACK);

        int offsetX = CaveElementHolder.HOLDER_SIZE_IN_PX;
        int offsetY = CaveElementHolder.HOLDER_SIZE_IN_PX;

        int width = CaveElementHolder.HOLDER_SIZE_IN_PX * (cave.getWidth() - 2);
        int height = CaveElementHolder.HOLDER_SIZE_IN_PX * (cave.getHeight() - 2);

        g.fillRect(offsetX, offsetY, width, height);

        g.setColor(Color.WHITE);

        g.setFont(oldFont.deriveFont(100f));

        int textOffsetX = offsetX + (width * 30) / 100;
        int textOffsetY = offsetY + (height * 10) / 100;

        g.drawString(message, textOffsetX, textOffsetY + height / 2);

        g.setColor(oldColor);
        g.setClip(oldClip);
        g.setComposite(oldComposite);
        g.setFont(oldFont);
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

        public void gameLost() {
            applicationFrame.stopPlay();
            selectionManager.changeGameState(SelectionManager.GameStates.LOST);
        }

        public void currentToolChanged(SelectionManager.Tools newCurrentTool) {
            updateMouseHandler();
            if (selectionManager.getCurrentTool() == SelectionManager.Tools.PLAY) {
                applicationFrame.startPlay();
            } else {
                applicationFrame.restoreEdit();
            }
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

        public void gameStateChanged(SelectionManager.GameStates newGameState) {
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
