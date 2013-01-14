package bdash.controller;

import bdash.ApplicationFrame;

import bdash.model.*;
import bdash.selection.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Iterator;

public class SelectWallColor implements ItemListener, SelectionManagerListener {
    private final SelectionManager selectionManager;
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

    public void updateBox() {
        comboBox.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);

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
