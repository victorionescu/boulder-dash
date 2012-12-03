import com.explodingpixels.macwidgets.BottomBar;
import com.explodingpixels.macwidgets.BottomBarSize;
import com.explodingpixels.macwidgets.MacUtils;
import com.explodingpixels.macwidgets.MacWidgetFactory;

import javax.swing.*;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        super("Boulder Dash");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MacUtils.makeWindowLeopardStyle(getRootPane());

        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        BottomBar bottomBar = new BottomBar(BottomBarSize.LARGE);
        bottomBar.addComponentToCenter(MacWidgetFactory.createEmphasizedLabel("" +
                "you and I, are like diamonds in the sky"));

        contentPane.add(bottomBar.getComponent(), BorderLayout.SOUTH);
        pack();
    }

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();
        applicationFrame.setVisible(true);
    }
}
