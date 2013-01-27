package bdash.selection;

import bdash.model.CaveElementHolder;

import java.util.*;

/*
    This represents the application state model.

    (1) It keeps track of whether the game has been won, lost or is still in progress.

    (2) It also keeps track of the currently selected tool and the current selection.

    An instance of this class deals with (2) and is active in the 'Edit' mode and another instance
    deals with (1) and is active in the 'Play' mode.

    Listeners implement SelectionManagerListener.
 */
public class SelectionManager {
    public static enum Tools { CREATE_WALL, CREATE_BOULDER, CREATE_DIAMOND, CREATE_DIRT, CREATE_PLAYER, PLAY, EDIT }

    public static enum GameStates { WON, LOST, UNDEFINED }

    private Tools currentTool;
    private final Set<CaveElementHolder> selection;
    private final List<SelectionManagerListener> listeners;
    private GameStates gameState;

    private int diamondObjective;

    public SelectionManager(Tools currentTool) {
        listeners = new ArrayList<SelectionManagerListener>();
        selection = new HashSet<CaveElementHolder>();
        this.currentTool = currentTool;
        gameState = GameStates.UNDEFINED;

        diamondObjective = 0;
    }

    public Tools getCurrentTool() {
        return currentTool;
    }

    public GameStates getGameState() {
        return gameState;
    }

    public void changeGameState(GameStates gameState) {
        this.gameState = gameState;

        for (SelectionManagerListener l : listeners) {
            l.gameStateChanged(gameState);
        }
    }

    public void setCurrentTool(Tools currentTool) {
        this.currentTool = currentTool;
        for (SelectionManagerListener l : listeners) {
            l.currentToolChanged(currentTool);
        }
    }

    public int getDiamondObjective() {
        return diamondObjective;
    }

    public void setDiamondObjective(int diamondObjective) {
        this.diamondObjective = diamondObjective;
        for (SelectionManagerListener l : listeners) {
            l.diamondObjectiveChanged(diamondObjective);
        }
    }

    public void decreaseDiamondObjective() {
        diamondObjective -= 1;

        for (SelectionManagerListener l : listeners) {
            l.diamondObjectiveChanged(diamondObjective);
        }

        if (diamondObjective == 0) {
            changeGameState(GameStates.WON);
        }
    }

    public boolean isSelectionEmpty() {
        return selection.isEmpty();
    }

    public Iterator<CaveElementHolder> getSelection() {
        return selection.iterator();
    }

    public void selectElements(Collection<CaveElementHolder> elementHolders) {
        for (CaveElementHolder elementHolder : elementHolders) {
            selection.add(elementHolder);
        }
        for (SelectionManagerListener l : listeners) {
            l.elementsSelected(elementHolders);
        }
    }

    public void deselectElement(CaveElementHolder elementHolder) {
        selection.remove(elementHolder);
        for (SelectionManagerListener l : listeners) {
            l.elementsDeselected(Collections.singleton(elementHolder));
        }
    }

    public void deselectElements(Collection<CaveElementHolder> elementHolders) {
        for (CaveElementHolder elementHolder : elementHolders) {
            selection.remove(elementHolder);
        }
        for (SelectionManagerListener l : listeners) {
            l.elementsDeselected(elementHolders);
        }
    }

    public void clearSelection() {
        selection.clear();
        for (SelectionManagerListener l : listeners) {
            l.selectionCleared();
        }
    }

    public void addListener(SelectionManagerListener listener) {
        listeners.add(listener);
    }
}
