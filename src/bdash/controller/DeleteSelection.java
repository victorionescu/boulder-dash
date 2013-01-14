package bdash.controller;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;
import bdash.model.SelectionCheckVisitor;
import bdash.selection.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

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
        Iterator<CaveElementHolder> selection = selectionManager.getSelection();
        while (selection.hasNext()) {
            selection.next().setCaveElement(null);
        }
        selectionManager.clearSelection();
    }

    public void currentToolChanged(SelectionManager.Tools tool) {
        updateButton();
    }

    public void elementsSelected(Collection<CaveElementHolder> elements) {
        updateButton();
    }

    public void elementsDeselected(Collection<CaveElementHolder> elements) {
        updateButton();
    }

    public void selectionCleared() {
        updateButton();
    }

    public void gameStateChanged(SelectionManager.GameStates newGameState) {}

    void updateButton() {
        button.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);

        SelectionCheckVisitor selectionCheckVisitor = new SelectionCheckVisitor();
        Iterator<CaveElementHolder> selection = selectionManager.getSelection();

        while (selection.hasNext()) {
            CaveElementHolder elementHolder = selection.next();
            CaveElement caveElement = elementHolder.getCaveElement();
            if (caveElement != null) {
                caveElement.accept(selectionCheckVisitor, elementHolder);
            }
        }

        button.setEnabled(selectionCheckVisitor.doesContainElements());
    }
}
