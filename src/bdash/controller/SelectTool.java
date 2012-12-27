package bdash.controller;

import bdash.model.CaveElementHolder;
import bdash.selection.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

public class SelectTool implements ActionListener, SelectionManagerListener {
    SelectionManager selectionManager;
    AbstractButton button;
    SelectionManager.Tools tool;

    public SelectTool(SelectionManager selectionManager, AbstractButton button, SelectionManager.Tools tool) {
        this.selectionManager = selectionManager;
        this.button = button;
        this.tool = tool;
        selectionManager.addListener(this);
        button.addActionListener(this);
        updateButton();
    }

    public void actionPerformed(ActionEvent e) {
        selectionManager.setCurrentTool(tool);
    }

    public void currentToolChanged(SelectionManager.Tools currentTool) {
        updateButton();
    }

    public void elementsSelected(Collection<CaveElementHolder> elements) {}

    public void elementsDeselected(Collection<CaveElementHolder> elements) {}

    public void selectionCleared() {}

    public void updateButton() {
        button.setSelected(selectionManager.getCurrentTool() == tool);
    }
}
