package bdash.view;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
/*
 * Mouse handler interface.
 * 
 * Implements MouseListener and MouseMotionListener.
 */
public interface MouseHandler extends MouseListener, MouseMotionListener {
	/* This should be called whenever a certain handler becomes active. */
    void makeActive();
}
