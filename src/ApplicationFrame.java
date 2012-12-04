import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        super("Boulder Dash");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        JPanel gameScreen = new JPanel(new BorderLayout());
        gameScreen.setBorder(BorderFactory.createLineBorder(Color.black));

        contentPane.add(gameScreen, BorderLayout.CENTER);
        contentPane.add(getToolbar(), BorderLayout.NORTH);

        pack();
    }

    public JComponent getToolbar() {
        JToolBar toolBar = new JToolBar();

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
        ApplicationFrame applicationFrame = new ApplicationFrame();
        applicationFrame.setVisible(true);
    }
}
