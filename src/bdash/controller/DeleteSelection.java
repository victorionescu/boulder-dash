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

/*
    This class defines the interactions for the 'Delete' button that is available in 'Edit' mode.
    This button should be enabled whenever the current selection is not empty and should delete all selected
    elements when pressed.

    Implements ActionListener (for user clicks) and SelectionManagerListener (for changes in the application state).
 */



public class DeleteSelection implements ActionListener, SelectionManagerListener {
    /* SelectionManager the 'Delete' button listens to. */
    private final SelectionManager selectionManager;

    /* The Swing component that denotes the button. */
    private final AbstractButton button;

    public DeleteSelection(SelectionManager selectionManager, AbstractButton button) {
        this.selectionManager = selectionManager;
        this.button = button;

        selectionManager.addListener(this);
        button.addActionListener(this);

        updateButton();
    }

    /*
        Whenever the button is pressed, all selected elements are deleted from the cave.
     */
    public void actionPerformed(ActionEvent e) {
        Iterator<CaveElementHolder> selection = selectionManager.getSelection();
        while (selection.hasNext()) {
            /* Deleting element in the selected CaveElementHolder. */
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

    public void diamondObjectiveChanged(int newDiamondObjective) {}

    /*
        This updates the button's state whenever there is a relevant change in the application state.
        It uses an instance of SelectionCheckVisitor to check whether the selection is empty.
     */
    private void updateButton() {
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

        /* Enabled if selection is not empty. */
        button.setEnabled(selectionCheckVisitor.doesContainElements());
    }
}
