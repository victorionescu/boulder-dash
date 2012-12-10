package bdash;

import bdash.controller.DeleteSelection;
import bdash.controller.SelectTool;
import bdash.controller.SelectWallColor;
import bdash.model.*;
import bdash.selection.SelectionManager;
import bdash.util.WallColor;
import bdash.view.CaveView;

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

        caveView = new CaveView(cave, selectionManager);

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

        JButton deleteButton = new JButton("Delete");
        new DeleteSelection(selectionManager, deleteButton);

        WallColorComboBox wallColorBox = new WallColorComboBox();
        new SelectWallColor(selectionManager, wallColorBox);

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

        return toolBar;
    }

    public static class WallColorComboBox extends JComboBox {
        public WallColorComboBox() {
            super(WallColor.COLORS.toArray());
            addItem(new WallColor());
        }

        public void selectWallColor(WallColor wallColor) {
            for (int i = 0; i < getItemCount(); i += 1) {
                WallColor item = (WallColor)getItemAt(i);
                if (item == wallColor) {
                    setSelectedIndex(i);
                }
            }
        }

        public void selectUndefined() {
            for (int i = 0; i < getItemCount(); i += 1) {
                WallColor item = (WallColor)getItemAt(i);
                if (item.getColor() == null) {
                    setSelectedIndex(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Cave cave = new Cave(new Dimension(40, 20));
        cave.setElement(0, 0, new BoulderElement(cave, new Point(0, 0)));
        cave.setElement(1, 0, new BoulderElement(cave, new Point(1, 0)));
        cave.setElement(30, 10, new DiamondElement(cave, new Point(30, 10)));
        cave.setElement(20, 9, new WallElement(cave, new Point(20, 9), WallColor.COLORS.get(0)));
        cave.setElement(20, 10, new WallElement(cave, new Point(20, 10), WallColor.COLORS.get(0)));
        cave.setElement(10, 9, new PlayerElement(cave, new Point(10, 9), PlayerElement.LastDirection.EAST));
        ApplicationFrame applicationFrame = new ApplicationFrame(cave);
        applicationFrame.setVisible(true);
    }
}
