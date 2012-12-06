package selection;

public class SelectionManager {
    public static enum Tools { CREATE_WALL, CREATE_BOULDER, CREATE_DIAMOND, CREATE_PLAYER, PLAY, EDIT }

    private Tools currentTool;

    public SelectionManager() {

    }

    public Tools getCurrentTool() {
        return currentTool;
    }
}
