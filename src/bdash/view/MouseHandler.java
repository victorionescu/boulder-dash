package bdash.view;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public interface MouseHandler extends MouseListener, MouseMotionListener {
    void makeActive();
}
