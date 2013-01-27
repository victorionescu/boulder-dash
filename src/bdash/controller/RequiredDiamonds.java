package bdash.controller;

import bdash.model.CaveElementHolder;
import bdash.selection.SelectionManager;
import bdash.selection.SelectionManagerListener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;

/*
    Controller responsible with the interactions between the two SelectionManager's (used alternatively between
    'Edit' and 'Play' mode) and the JTextField used for picking the desired number of diamonds that have to be
    collected.

    Implements KeyListener and SelectionManagerListener.
 */

public class RequiredDiamonds implements KeyListener, SelectionManagerListener {
    /*
        SelectionManager used in 'Edit' mode.
        This is mainly responsible with deciding whether the button is enabled and remembering the objective
        whenever the game is reset.
    */
    private final SelectionManager selectionManager;

    /*
        SelectionManager used in 'Play' mode.
        This is responsible with displaying the remaining number of diamonds to the user.
    */
    private final SelectionManager playSelectionManager;

    /* Swing component dealing with text input. */
    private final JTextField textField;

    public RequiredDiamonds(SelectionManager selectionManager,
                            SelectionManager playSelectionManager,
                            JTextField textField) {
        this.selectionManager = selectionManager;
        this.playSelectionManager = playSelectionManager;
        this.textField = textField;

        selectionManager.addListener(this);
        playSelectionManager.addListener(this);

        textField.addKeyListener(this);

        updateTextField();
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {

    }

    /* This validates the input from the JTextField and updates both SelectionManager's accordingly. */
    public void keyReleased(KeyEvent e) {
        if (textField.getText().isEmpty()) {
            textField.setText(Integer.toString(0));
        } else {
            try {
                int diamondObjective = Integer.parseInt(textField.getText());
                textField.setText(Integer.toString(diamondObjective));
            } catch (NumberFormatException nfe) {
                textField.setText(Integer.toString(0));
            }
        }

        selectionManager.setDiamondObjective(Integer.parseInt(textField.getText()));
        playSelectionManager.setDiamondObjective(Integer.parseInt(textField.getText()));
    }

    public void gameStateChanged(SelectionManager.GameStates newGameState) {}

    public void currentToolChanged(SelectionManager.Tools newCurrentTool) {
        updateTextField();
    }

    public void selectionCleared() {}

    public void elementsSelected(Collection<CaveElementHolder> elementHolders) {}

    public void elementsDeselected(Collection<CaveElementHolder> elementHolders) {}

    public void diamondObjectiveChanged(int newDiamondObjective) {
        updateTextField();
    }

    /* This updates the JTextField whenever a change of state is encountered. */
    public void updateTextField() {
        /* Visible in both 'Edit' and 'Play' modes. */
        textField.setVisible(selectionManager.getCurrentTool() == SelectionManager.Tools.PLAY ||
                selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);

        /* Only enabled in 'Edit' mode. */
        textField.setEnabled(selectionManager.getCurrentTool() == SelectionManager.Tools.EDIT);

        /* Depending on the active mode, the displayed number is drawn from one of the two SelectionManager's */
        if (selectionManager.getCurrentTool() == SelectionManager.Tools.PLAY) {
            textField.setText(Integer.toString(playSelectionManager.getDiamondObjective()));
        } else {
            textField.setText(Integer.toString(selectionManager.getDiamondObjective()));
        }
    }
}
