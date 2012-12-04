package view;

import model.CaveElement;

import javax.swing.*;
import java.awt.*;

public interface CaveElementPainter {

    public void paint(Graphics2D g, CaveElement e);
}
