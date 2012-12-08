package controller;

import selection.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteSelection implements ActionListener, SelectionManagerListener {
    private final SelectionManager selectionManager;
    private final AbstractButton button;

    public DeleteSelection(SelectionManager selectionManager, AbstractButton button) {
        this.selectionManager = selectionManager;
        this.button = button;
        selectionManager.addListener(this);
        button.addActionListener(this);
        updateButton();
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void currentToolChanged(SelectionManager.Tools tool) {
        updateButton();
    }

    void updateButton() {
        button.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);
        button.setEnabled(selectionManager.isSelectionEmpty() == false);
    }
}
