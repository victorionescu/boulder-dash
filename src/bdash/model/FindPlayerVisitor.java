package bdash.model;

/*
    Visitor used for locating the player in the cave.
 */
public class FindPlayerVisitor extends NullElementVisitor {
    private CaveElementHolder playerHolder;

    @Override
    public void visit(PlayerElement playerElement, CaveElementHolder elementHolder) {
        playerHolder = elementHolder;
    }

    public CaveElementHolder getPlayerHolder() {
        return playerHolder;
    }
}
