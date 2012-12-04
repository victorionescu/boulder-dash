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

        return toolBar;
    }

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();
        applicationFrame.setVisible(true);
    }
}
