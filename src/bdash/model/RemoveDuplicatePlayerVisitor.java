package bdash.model;
/*
    Visitor used for removing duplicate players in 'Edit' mode. It is used after the 'Create Player' tool is used.
 */
public class RemoveDuplicatePlayerVisitor extends NullElementVisitor {
    private int numberOfPlayers;

    public RemoveDuplicatePlayerVisitor() {
        numberOfPlayers = 0;
    }


    public int playersEncountered() {
        return numberOfPlayers;
    }

    @Override
    public void visit(PlayerElement playerElement, CaveElementHolder holder) {
        numberOfPlayers += 1;

        if (numberOfPlayers > 1) {
            holder.setCaveElement(null);
        }
    }
}
