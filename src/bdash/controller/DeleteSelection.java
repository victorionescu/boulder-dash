package bdash.controller;

import bdash.model.CaveElement;
import bdash.selection.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

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

    public void elementsSelected(Collection<? extends CaveElement> elements) {
        updateButton();
    }

    public void elementsDeselected(Collection<? extends CaveElement> elements) {
        updateButton();
    }

    public void selectionCleared() {
        updateButton();
    }

    void updateButton() {
        button.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);
        button.setEnabled(selectionManager.isSelectionEmpty() == false);
    }
}