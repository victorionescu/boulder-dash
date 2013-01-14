package bdash.model;

public class GravityVisitor extends NullElementVisitor {
    public static final GravityVisitor INSTANCE = new GravityVisitor();

    private GravityVisitor() {}

    public void visit(BoulderElement boulderElement, CaveElementHolder holder) {
        pullDown(boulderElement, holder);
    }

    public void visit(DiamondElement diamondElement, CaveElementHolder holder) {
        pullDown(diamondElement, holder);
    }

    protected void pullDown(HeavyElement heavyElement, CaveElementHolder holder) {
        Cave cave = holder.cave;

        int row = holder.getRow();
        int column = holder.getColumn();

        if (row < cave.getHeight() - 1) {
            if (cave.getElementHolder(row + 1, column).getCaveElement() == null) {
                heavyElement.setFalling(true);
                cave.getElementHolder(row + 1, column).setCaveElement(heavyElement);
                holder.setCaveElement(null);
            } else if (heavyElement.isFalling()) {
                CaveElementHolder catcherHolder = cave.getElementHolder(row + 1, column);
                catcherHolder.getCaveElement().getCatcherStrategy().catchElement(heavyElement, catcherHolder);
            }
        } else {
            heavyElement.setFalling(false);
        }
    }
}
