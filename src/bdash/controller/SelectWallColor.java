package bdash.controller;

import bdash.ApplicationFrame;

import bdash.model.Cave;
import bdash.model.CaveElement;
import bdash.model.WallElement;
import bdash.selection.*;
import bdash.util.WallColor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

public class SelectWallColor implements ActionListener, SelectionManagerListener {
    private final SelectionManager selectionManager;
    private final ApplicationFrame.WallColorComboBox comboBox;

    public SelectWallColor(SelectionManager selectionManager, ApplicationFrame.WallColorComboBox comboBox) {
        this.selectionManager = selectionManager;
        this.comboBox = comboBox;
        selectionManager.addListener(this);
        comboBox.addActionListener(this);
        updateBox();
    }

    public void actionPerformed(ActionEvent e) {
        WallColor newWallColor = (WallColor)comboBox.getItemAt(comboBox.getSelectedIndex());
        if (newWallColor.getColor() != null) {
            Iterator<CaveElement> selection = selectionManager.getSelection();

            while (selection.hasNext()) {
                WallElement element = (WallElement)selection.next();
                Cave cave = element.getCave();

                cave.fireCaveElementWillChange(element);
                element.setWallColor(newWallColor);
                cave.fireCaveElementChanged(element);
            }
        }
    }

    public void currentToolChanged(SelectionManager.Tools tool) {
        updateBox();
    }

    public void elementsSelected(Collection<? extends CaveElement> elements) {
        updateBox();
    }

    public void elementsDeselected(Collection<? extends CaveElement> elements) {
        updateBox();
    }

    public void selectionCleared() {
        updateBox();
    }

    public void updateBox() {
        comboBox.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);

        Iterator<CaveElement> selection = selectionManager.getSelection();
        WallCountVisitor wallCountVisitor = new WallCountVisitor();

        while (selection.hasNext() && wallCountVisitor.hasWallsOnly()) {
            CaveElement element = selection.next();
            element.accept(wallCountVisitor);
        }

        comboBox.setEnabled(!selectionManager.isSelectionEmpty() &&
                wallCountVisitor.hasWallsOnly());
        if (comboBox.isEnabled()) {
            if (wallCountVisitor.isSameColor()) {
                comboBox.selectWallColor(wallCountVisitor.getWallColor());
            } else {
                comboBox.selectUndefined();
            }
        }
    }
}
