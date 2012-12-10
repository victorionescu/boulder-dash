package bdash.selection;

import bdash.model.CaveElement;

import java.util.*;

public class SelectionManager {
    public static enum Tools { CREATE_WALL, CREATE_BOULDER,
        CREATE_DIAMOND, CREATE_DIRT, CREATE_PLAYER, PLAY, EDIT }

    private Tools currentTool;
    private final Set<CaveElement> selection;
    private final List<SelectionManagerListener> listeners;

    public SelectionManager() {
        listeners = new ArrayList<SelectionManagerListener>();
        selection = new HashSet<CaveElement>();
        currentTool = Tools.EDIT;
    }

    public Tools getCurrentTool() {
        return currentTool;
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

    public Iterator<CaveElement> getSelection() {
        return selection.iterator();
    }

    public void selectElement(CaveElement element) {
        selection.add(element);
        for (SelectionManagerListener l : listeners) {
            l.elementsSelected(Collections.singleton(element));
        }
    }

    public void selectElements(Collection<? extends CaveElement> elements) {
        for (CaveElement element : elements) {
            selection.add(element);
        }
        for (SelectionManagerListener l : listeners) {
            l.elementsSelected(elements);
        }
    }

    public void deselectElement(CaveElement element) {
        selection.remove(element);
        for (SelectionManagerListener l : listeners) {
            l.elementsDeselected(Collections.singleton(element));
        }
    }

    public void deselectElements(Collection<? extends CaveElement> elements) {
        for (CaveElement element : elements) {
            selection.remove(element);
        }
        for (SelectionManagerListener l : listeners) {
            l.elementsDeselected(elements);
        }
    }

    public void clearSelection() {
        selection.clear();
        for (SelectionManagerListener l : listeners) {
            l.selectionCleared();
        }
    }

    public boolean isSelected(CaveElement element) {
        return selection.contains(element);
    }

    public void addListener(SelectionManagerListener listener) {
        listeners.add(listener);
    }
}
