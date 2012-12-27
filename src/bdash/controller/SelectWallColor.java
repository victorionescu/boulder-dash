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

    public void updateBox() {
        comboBox.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);

        Iterator<CaveElementHolder> selection = selectionManager.getSelection();

        WallCheckVisitor wallCheckVisitor = new WallCheckVisitor();

        while (selection.hasNext() && wallCheckVisitor.hasWallsOnly()) {
            CaveElement element = selection.next().getCaveElement();
            if (element != null) {
                element.accept(wallCheckVisitor);
            }
        }

        comboBox.setEnabled(!selectionManager.isSelectionEmpty() &&
                wallCheckVisitor.hasWallsOnly());

        comboBox.selectWallColor(wallCheckVisitor.getWallColor());
    }

    public void updateWalls() {
        WallElement.WallColor newWallColor = (WallElement.WallColor)comboBox.getItemAt(comboBox.getSelectedIndex());
        if (newWallColor != WallElement.WallColor.UNDEFINED) {
            Iterator<CaveElementHolder> selection = selectionManager.getSelection();

            while (selection.hasNext()) {
                CaveElementHolder elementHolder = selection.next();

                if (elementHolder.getCaveElement() != null) {
                    Cave cave = elementHolder.getCave();

                    cave.fireElementHolderWillChange(elementHolder);

                    WallElement wallElement = (WallElement)elementHolder.getCaveElement();
                    wallElement.setWallColor(newWallColor);

                    cave.fireElementHolderChanged(elementHolder);
                }
            }
        }
    }
}
