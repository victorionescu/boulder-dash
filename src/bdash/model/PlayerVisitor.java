package bdash.model;
/*
    Visitor that represents the player. It determines what happens when a player runs into another object, as a
    consequence of moving.
 */
public class PlayerVisitor extends NullElementVisitor {
    CaveElementHolder playerHolder;

    public PlayerVisitor(CaveElementHolder playerHolder) {
        this.playerHolder = playerHolder;
    }

    /*
        Dirt is 'eaten'.
     */
    @Override
    public void visit(DirtElement dirtElement, CaveElementHolder holder) {
        holder.setCaveElement(playerHolder.getCaveElement());
        playerHolder.setCaveElement(null);
    }

    /*
        Diamonds are 'eaten' and appropriate events are fired.
     */
    @Override
    public void visit(DiamondElement diamondElement, CaveElementHolder holder) {
        holder.setCaveElement(playerHolder.getCaveElement());
        playerHolder.setCaveElement(null);

        holder.cave.fireDiamondCollected();
    }

    /*
        Boulders are moved to the left or right if possible.
     */
    @Override
    public void visit(BoulderElement boulderElement, CaveElementHolder holder) {
        if (holder.getRow() == playerHolder.getRow()) {
            int columnOffset = holder.getColumn() - playerHolder.getColumn();

            int newColumn = holder.getColumn() + columnOffset;


            if (newColumn >= 0 && newColumn < holder.cave.getWidth()) {
                CaveElementHolder neighborHolder = holder.cave.getElementHolder(holder.getRow(), newColumn);

                if (neighborHolder.getCaveElement() == null) {
                    neighborHolder.setCaveElement(holder.getCaveElement());
                    holder.setCaveElement(playerHolder.getCaveElement());
                    playerHolder.setCaveElement(null);
                }
            }

        }
    }
}
