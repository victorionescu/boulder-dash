package bdash.controller;

import bdash.model.CaveElementHolder;
import bdash.selection.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

/*
    Controller class that handles changes UI interactions that switch between the different tools.

    This is instantiated once per tool and binds to the tool's button.

    Implements ActionListener and SelectionManagerListener.
 */

public class SelectTool implements ActionListener, SelectionManagerListener {
    /* SelectionManager for the 'Edit' mode. */
    private SelectionManager selectionManager;

    /* Swing button used for the current tool. */
    AbstractButton button;

    /* Tool represented by this button. */
    private SelectionManager.Tools tool;

    public SelectTool(SelectionManager selectionManager, AbstractButton button, SelectionManager.Tools tool) {
        this.selectionManager = selectionManager;
        this.button = button;
        this.tool = tool;
        selectionManager.addListener(this);
        button.addActionListener(this);

        updateButton();
    }

    /* We switch to this tool whenever the button is pressed. */
    public void actionPerformed(ActionEvent e) {
        selectionManager.setCurrentTool(tool);
    }

    public void currentToolChanged(SelectionManager.Tools currentTool) {
        updateButton();
    }

    public void elementsSelected(Collection<CaveElementHolder> elements) {}

    public void elementsDeselected(Collection<CaveElementHolder> elements) {}

    public void selectionCleared() {}

    public void gameStateChanged(SelectionManager.GameStates newGameState) {}

    public void diamondObjectiveChanged(int newDiamondObjective) {}

    /* Button should be selected only if 'tool' is the one currently used by the SelectionManager. */
    public void updateButton() {
        button.setSelected(selectionManager.getCurrentTool() == tool);
    }
}
