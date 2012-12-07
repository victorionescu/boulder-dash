import controller.SelectTool;
import model.*;
import selection.SelectionManager;
import view.CaveView;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    private final CaveView caveView;
    private final SelectionManager selectionManager;

    public ApplicationFrame(Cave cave) {
        super("Boulder Dash");

        selectionManager = new SelectionManager();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        caveView = new CaveView(cave);

        contentPane.add(caveView, BorderLayout.CENTER);
        contentPane.add(getToolbar(), BorderLayout.NORTH);

        pack();
    }

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

        toolBar.add(editButton);
        toolBar.add(createWallButton);
        toolBar.add(createBoulderButton);
        toolBar.add(createDiamondButton);
        toolBar.add(createDirtButton);
        toolBar.add(createPlayerButton);
        toolBar.add(playButton);

        return toolBar;
    }

    public static void main(String[] args) {
        Cave cave = new Cave(new Dimension(40, 20));
        cave.putElement(0, 0, new BoulderElement(cave, new Point(0, 0)));
        cave.putElement(1, 0, new BoulderElement(cave, new Point(1, 0)));
        cave.putElement(30, 10, new DiamondElement(cave, new Point(30, 10)));
        cave.putElement(20, 9, new DirtElement(cave, new Point(20, 9)));
        cave.putElement(10, 9, new PlayerElement(cave, new Point(10, 9), PlayerElement.LastDirection.EAST));
        ApplicationFrame applicationFrame = new ApplicationFrame(cave);
        applicationFrame.setVisible(true);
    }
}
