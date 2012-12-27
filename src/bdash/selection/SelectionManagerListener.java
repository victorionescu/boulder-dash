package bdash.selection;

import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;

import java.util.Collection;

public interface SelectionManagerListener {
    void currentToolChanged(SelectionManager.Tools newCurrentTool);

    void elementsSelected(Collection<CaveElementHolder> elements);

    void elementsDeselected(Collection<CaveElementHolder> elements);

    void selectionCleared();
}
