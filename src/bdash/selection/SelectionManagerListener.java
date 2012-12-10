package bdash.selection;

import bdash.model.CaveElement;

import java.util.Collection;

public interface SelectionManagerListener {
    void currentToolChanged(SelectionManager.Tools newCurrentTool);

    void elementsSelected(Collection<? extends CaveElement> elements);

    void elementsDeselected(Collection<? extends CaveElement> elements);

    void selectionCleared();
}
