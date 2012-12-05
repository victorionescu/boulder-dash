import model.*;
import view.CaveView;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    private final CaveView caveView;

    public ApplicationFrame(Cave cave) {
        super("Boulder Dash");

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
        Cave cave = new Cave(new Dimension(40, 20));
        cave.putElement(0, 0, new BoulderElement(cave, new Point(0, 0)));
        cave.putElement(1, 0, new BoulderElement(cave, new Point(1, 0)));
        cave.putElement(30, 10, new DiamondElement(cave, new Point(30, 10)));
        cave.putElement(20, 9, new DirtElement(cave, new Point(20, 9)));
        ApplicationFrame applicationFrame = new ApplicationFrame(cave);
        applicationFrame.setVisible(true);
    }
}
