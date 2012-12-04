package view;

import model.Cave;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: victor
 * Date: 03/12/2012
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class CaveView extends JPanel {
    private Cave cave;

    public CaveView(Cave cave) {
        int pixelWidth = cave.getWidth() * 30;
        int pixelHeight = cave.getHeight() * 30;

        setPreferredSize(new Dimension(pixelWidth, pixelHeight));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
}
