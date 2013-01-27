package bdash.selection;

import bdash.model.CaveElementHolder;

import java.util.Collection;
/*
    Interface for the SelectionManager listener.
 */
public interface SelectionManagerListener {
    /* Fired when the tool has changed. */
    void currentToolChanged(SelectionManager.Tools newCurrentTool);

    /* Fired when elements are selected. */
    void elementsSelected(Collection<CaveElementHolder> elements);

    /* Fired when elements are deselected. */
    void elementsDeselected(Collection<CaveElementHolder> elements);

    /* Fired when selection is cleared. */
    void selectionCleared();

    /* Fired when game state has changed. (e.g. game is won/lost) */
    void gameStateChanged(SelectionManager.GameStates newGameState);

    /* Fired when the required number of diamonds to collect has changed. */
    void diamondObjectiveChanged(int newDiamondObjective);
}
