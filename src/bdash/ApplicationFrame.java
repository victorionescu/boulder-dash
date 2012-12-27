package bdash;

import bdash.controller.DeleteSelection;
import bdash.controller.SelectTool;
import bdash.controller.SelectWallColor;
import bdash.model.*;
import bdash.selection.SelectionManager;
import bdash.view.CaveView;

import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.ArrayList;

public class ApplicationFrame extends JFrame {
    private final CaveView caveView;
    private final SelectionManager selectionManager;

    public ApplicationFrame(int width, int height) {
        super("Boulder Dash");

        selectionManager = new SelectionManager();
        caveView = new CaveView(new Cave(width, height), selectionManager);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        contentPane.add(getToolbar(), BorderLayout.NORTH);
        contentPane.add(caveView, BorderLayout.CENTER);

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

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame(40, 20);
        applicationFrame.setVisible(true);
    }
}
