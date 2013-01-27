package bdash;

import bdash.controller.*;
import bdash.model.*;
import bdash.selection.SelectionManager;
import bdash.view.CaveView;

import javax.swing.*;
import java.awt.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;
/*
    Main class, representing the window of the game.
 */
public class ApplicationFrame extends JFrame {
    private final CaveView caveView;

    private final Cave cave;

    /* SelectionManager for edit mode. */
    private final SelectionManager selectionManager;

    /* SelectionManager for play mode. */
    private final SelectionManager playSelectionManager;

    private final JComponent toolBar;

    /* GameController instantiated when play mode is active. */
    private GameController gameController;

    private final KeyListener keyListener;

    public ApplicationFrame(int width, int height) {
        super("Boulder Dash");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        selectionManager = new SelectionManager(SelectionManager.Tools.EDIT);
        playSelectionManager = new SelectionManager(SelectionManager.Tools.PLAY);

        cave = new Cave(width, height);

        toolBar = getToolbar();

        caveView = new CaveView(this, cave, selectionManager);

        buildContentPane(caveView);

        keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {}

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (playSelectionManager.getGameState() != SelectionManager.GameStates.UNDEFINED) {
                    return;
                }

                if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                    gameController.movePlayer(PlayerElement.Direction.SOUTH);
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                    gameController.movePlayer(PlayerElement.Direction.NORTH);
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                    gameController.movePlayer(PlayerElement.Direction.WEST);
                } else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                    gameController.movePlayer(PlayerElement.Direction.EAST);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {}
        };
    }

    /* Resets the content pane to display 'caveView'. Used to switch between game modes. */
    private void buildContentPane(CaveView caveView) {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(caveView, BorderLayout.CENTER);

        pack();
    }

    /* Instantiates a controller and starts the timer. */
    public void startPlay() {
        Cave playCave = cave.clone();

        gameController = new GameController(playCave);

        playSelectionManager.changeGameState(SelectionManager.GameStates.UNDEFINED);
        playSelectionManager.setDiamondObjective(selectionManager.getDiamondObjective());

        buildContentPane(new CaveView(this, playCave, playSelectionManager));


        if (playSelectionManager.getDiamondObjective() == 0) {
            playSelectionManager.changeGameState(SelectionManager.GameStates.WON);
        }

        getContentPane().addKeyListener(keyListener);

        getContentPane().setFocusable(true);
        getContentPane().requestFocusInWindow();

    }

    /* Stops the timer in the controller, hence the game. */
    public void stopPlay() {
        if (gameController != null) {
            gameController.stopTimer();
        }
    }

    /*
        Restores state to edit mode. Stops timer and resets caveView.
     */
    public void restoreEdit() {
        stopPlay();

        buildContentPane(caveView);
    }

    /* Builds up the toolbar. */
    public JComponent getToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JToggleButton editButton = new JToggleButton("Edit");
        new SelectTool(selectionManager, editButton, SelectionManager.Tools.EDIT);

        JToggleButton createWallButton = new JToggleButton("Create Wall");
        new SelectTool(selectionManager, createWallButton, SelectionManager.Tools.CREATE_WALL);

        JToggleButton createBoulderButton = new JToggleButton("Create Boulder");
        new SelectTool(selectionManager, createBoulderButton, SelectionManager.Tools.CREATE_BOULDER);

        JToggleButton createDiamondButton = new JToggleButton("Create Diamond");
        new SelectTool(selectionManager, createDiamondButton, SelectionManager.Tools.CREATE_DIAMOND);

        JToggleButton createDirtButton = new JToggleButton("Create Dirt");
        new SelectTool(selectionManager, createDirtButton, SelectionManager.Tools.CREATE_DIRT);

        JToggleButton createPlayerButton = new JToggleButton("Create Player");
        new SelectTool(selectionManager, createPlayerButton, SelectionManager.Tools.CREATE_PLAYER);

        JToggleButton playButton = new JToggleButton("Play");
        new SelectTool(selectionManager, playButton, SelectionManager.Tools.PLAY);

        JButton deleteButton = new JButton("Delete");
        new DeleteSelection(selectionManager, deleteButton);

        WallColorComboBox wallColorBox = new WallColorComboBox();
        new SelectWallColor(selectionManager, wallColorBox);

        JTextField diamondsTextField = new JTextField();
        new RequiredDiamonds(selectionManager, playSelectionManager, diamondsTextField);

        toolBar.add(editButton);
        toolBar.add(createWallButton);
        toolBar.add(createBoulderButton);
        toolBar.add(createDiamondButton);
        toolBar.add(createDirtButton);
        toolBar.add(createPlayerButton);
        toolBar.add(playButton);

        toolBar.addSeparator();

        toolBar.add(deleteButton);
        toolBar.add(wallColorBox);
        toolBar.add(diamondsTextField);

        return toolBar;
    }

    /* Class implementing the custom ComboBox used for picking the wall color in edit mode. */
    public static class WallColorComboBox extends JComboBox {
        public static final List<WallElement.WallColor> COLORS = new ArrayList<WallElement.WallColor>();

        static {
            COLORS.add(WallElement.WallColor.UNDEFINED);
            COLORS.add(WallElement.WallColor.RED);
            COLORS.add(WallElement.WallColor.GREEN);
            COLORS.add(WallElement.WallColor.BLUE);
        }

        public WallColorComboBox() {
            super(COLORS.toArray());
        }

        public void selectWallColor(WallElement.WallColor wallColor) {
            for (int i = 0; i < getItemCount(); i += 1) {
                if (getItemAt(i) == wallColor) {
                    setSelectedIndex(i);
                }
            }
        }
    }

    public void popDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame(40, 20);
        applicationFrame.setVisible(true);
    }
}
