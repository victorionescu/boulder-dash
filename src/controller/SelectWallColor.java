package controller;

import selection.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectWallColor implements ActionListener, SelectionManagerListener {
    private final SelectionManager selectionManager;
    private final JComboBox comboBox;

    public SelectWallColor(SelectionManager selectionManager, JComboBox comboBox) {
        this.selectionManager = selectionManager;
        this.comboBox = comboBox;
        selectionManager.addListener(this);
        comboBox.addActionListener(this);
        updateBox();
    }

    public void actionPerformed(ActionEvent e) {

    }

    public void currentToolChanged(SelectionManager.Tools tool) {
        updateBox();
    }

    public void updateBox() {
        comboBox.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);
        comboBox.setEnabled(true);
    }
}
