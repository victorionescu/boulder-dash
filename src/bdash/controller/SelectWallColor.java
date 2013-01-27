package bdash.controller;

import bdash.ApplicationFrame;

import bdash.model.*;
import bdash.selection.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Iterator;

/*
    Controller responsible with the actions of the ComboBox used for changing wall colors.

    Implements ItemListener and SelectionManagerListener.
 */

public class SelectWallColor implements ItemListener, SelectionManagerListener {
    /* SelectionManager used for 'Edit' mode. */
    private final SelectionManager selectionManager;

    /* Swing component that denotes the ComboBox. */
    private final ApplicationFrame.WallColorComboBox comboBox;

    public SelectWallColor(SelectionManager selectionManager, ApplicationFrame.WallColorComboBox comboBox) {
        this.selectionManager = selectionManager;
        this.comboBox = comboBox;

        selectionManager.addListener(this);
        comboBox.addItemListener(this);

        updateBox();
    }

    public void itemStateChanged(ItemEvent e) {
        updateWalls();
    }

    public void currentToolChanged(SelectionManager.Tools tool) {
        updateBox();
    }

    public void elementsSelected(Collection<CaveElementHolder> elements) {
        updateBox();
    }

    public void elementsDeselected(Collection<CaveElementHolder> elements) {
        updateBox();
    }

    public void selectionCleared() {
        updateBox();
    }

    public void gameStateChanged(SelectionManager.GameStates newGameState) {}

    public void diamondObjectiveChanged(int newDiamondObjective) {}

    /* This is responsible with updating the properties of the ComboBox itself. */
    public void updateBox() {
        comboBox.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);

        /*
            A SelectionCheckVisitor is used to check if the selection is empty, if the only elements it contains are
            walls and if the walls have the same color.
         */
        SelectionCheckVisitor selectionCheckVisitor = new SelectionCheckVisitor();
        Iterator<CaveElementHolder> selection = selectionManager.getSelection();

        while (selection.hasNext()) {
            CaveElementHolder elementHolder = selection.next();
            CaveElement caveElement = elementHolder.getCaveElement();
            if (caveElement != null) {
                caveElement.accept(selectionCheckVisitor, elementHolder);
            }
        }

        comboBox.setEnabled(selectionCheckVisitor.doesContainElements() && selectionCheckVisitor.hasWallsOnly());

        comboBox.selectWallColor(selectionCheckVisitor.getWallColor());
    }

    /* This updates the currently selected walls, keeping the in sync with the ComboBox. */
    public void updateWalls() {
        WallElement.WallColor newWallColor = (WallElement.WallColor)comboBox.getItemAt(comboBox.getSelectedIndex());

        if (newWallColor != WallElement.WallColor.UNDEFINED) {
            Iterator<CaveElementHolder> selection = selectionManager.getSelection();

            while (selection.hasNext()) {
                CaveElementHolder elementHolder = selection.next();

                if (elementHolder.getCaveElement() != null) {
                    elementHolder.setCaveElement(new WallElement(newWallColor));
                }
            }
        }
    }
}
