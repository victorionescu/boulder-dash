package bdash.selection;

import bdash.model.CaveElementHolder;

import java.util.*;

public class SelectionManager {
    public static enum Tools { CREATE_WALL, CREATE_BOULDER, CREATE_DIAMOND, CREATE_DIRT, CREATE_PLAYER, PLAY, EDIT }

    public static enum GameStates { WON, LOST, UNDEFINED }

    private Tools currentTool;
    private final Set<CaveElementHolder> selection;
    private final List<SelectionManagerListener> listeners;
    private GameStates gameState;

    private int diamondsToCollect;

    public SelectionManager(Tools currentTool) {
        listeners = new ArrayList<SelectionManagerListener>();
        selection = new HashSet<CaveElementHolder>();
        this.currentTool = currentTool;
        gameState = GameStates.UNDEFINED;

        diamondsToCollect = 0;
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

    public boolean isSelectionEmpty() {
        return selection.isEmpty();
    }

    public Iterator<CaveElementHolder> getSelection() {
        return selection.iterator();
    }

    public void selectElement(CaveElementHolder elementHolder) {
        selection.add(elementHolder);
        for (SelectionManagerListener l : listeners) {
            l.elementsSelected(Collections.singleton(elementHolder));
        }
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

    public boolean isSelected(CaveElementHolder elementHolder) {
        return selection.contains(elementHolder);
    }

    public void addListener(SelectionManagerListener listener) {
        listeners.add(listener);
    }
}
