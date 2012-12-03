import com.explodingpixels.macwidgets.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        super("Boulder Dash");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));

        MacUtils.makeWindowLeopardStyle(getRootPane());

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        JPanel gameScreen = new JPanel(new BorderLayout());
        gameScreen.setBorder(BorderFactory.createLineBorder(Color.black));

        contentPane.add(getBottomBar(), BorderLayout.SOUTH);
        contentPane.add(gameScreen, BorderLayout.CENTER);
        contentPane.add(getToolbar(), BorderLayout.NORTH);

        pack();
    }

    public JComponent getToolbar() {
        UnifiedToolBar toolBar = new UnifiedToolBar();

        /*java.net.URL imgURL = getClass().getResource("blue_wall.png");

        if (imgURL != null) {
            System.out.println("YES! SCORE!");
        } else {
            System.out.println("NAH");
        }

        Icon imageIcon = new ImageIcon(imgURL, "ceva wall");

        JComponent button = MacButtonFactory.createGradientButton(imageIcon, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
                System.out.println(getWidth() + " " + getHeight());
            }
        });

        System.out.println(button.getWidth() + " " + button.getHeight());
        toolBar.addComponentToCenter(button); */

        /*JButton editButton = new JButton("Edit");
        editButton.putClientProperty("JButton.buttonType", "textured");

        toolBar.addComponentToLeft(editButton);  */

        return toolBar.getComponent();

    }

    public JComponent getBottomBar() {
        BottomBar bottomBar = new BottomBar(BottomBarSize.LARGE);
        bottomBar.addComponentToCenter(MacWidgetFactory.createEmphasizedLabel("" +
                "you and I, are like diamonds in the sky"));

        return bottomBar.getComponent();
    }

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();
        applicationFrame.setVisible(true);
    }
}
