import view.CaveView;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    private final CaveView caveView;

    public ApplicationFrame(int gridWidth, int gridHeight) {
        super("Boulder Dash");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);


        caveView = new CaveView(gridWidth, gridHeight);

        contentPane.add(caveView, BorderLayout.CENTER);
        contentPane.add(getToolbar(), BorderLayout.NORTH);

        pack();
    }

    public JComponent getToolbar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        JToggleButton editButton = new JToggleButton("Edit");

        JToggleButton createWallButton = new JToggleButton("Create Wall");

        JToggleButton createBoulderButton = new JToggleButton("Create Boulder");

        JToggleButton createDiamondButton = new JToggleButton("Create Diamond");

        JToggleButton createDirtButton = new JToggleButton("Create Dirt");

        JToggleButton createPlayerButton = new JToggleButton("Create Player");

        JToggleButton playButton = new JToggleButton("Play");

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
        ApplicationFrame applicationFrame = new ApplicationFrame(40, 20);
        applicationFrame.setVisible(true);
    }
}
