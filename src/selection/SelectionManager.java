package selection;

import java.util.List;
import java.util.ArrayList;

public class SelectionManager {
    public static enum Tools { CREATE_WALL, CREATE_BOULDER,
        CREATE_DIAMOND, CREATE_DIRT, CREATE_PLAYER, PLAY, EDIT }

    private Tools currentTool;
    private List<SelectionManagerListener> listeners;

    public SelectionManager() {
        listeners = new ArrayList<SelectionManagerListener>();
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

    public void addListener(SelectionManagerListener listener) {
        listeners.add(listener);
    }
}
