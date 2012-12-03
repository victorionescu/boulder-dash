import com.explodingpixels.macwidgets.IAppWidgetFactory;
import com.explodingpixels.macwidgets.MacUtils;
import com.explodingpixels.macwidgets.MacWidgetFactory;
import com.explodingpixels.macwidgets.UnifiedToolBar;
import com.explodingpixels.widgets.TableUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class ApplicationFrame extends JFrame {
    public ApplicationFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MacUtils.makeWindowLeopardStyle(getRootPane());

        JPanel contentPane = new JPanel(new BorderLayout());

        pack();

        //JPanel contentPane = new JPanel(new BorderLayout());
        //contentPane.add(toolBar, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        ApplicationFrame applicationFrame = new ApplicationFrame();
        applicationFrame.setVisible(true);
    }
}
