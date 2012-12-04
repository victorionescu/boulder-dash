package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: victor
 * Date: 03/12/2012
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class GameView extends JPanel {
    public GameView(int gridWidth, int gridHeight) {
        int pixelWidth = gridWidth * 30;
        int pixelHeight = gridHeight * 30;

        setPreferredSize(new Dimension(pixelWidth, pixelHeight));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
