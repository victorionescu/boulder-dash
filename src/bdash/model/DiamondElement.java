package bdash.model;

/*
    Class representing the diamonds.
 */

public class DiamondElement extends HeavyElement {
    /* Diamonds are lethal. */
    public boolean isLethal() {
        return true;
    }

    public CaveElement deepClone() {
        return new DiamondElement();
    }

    public void accept(CaveElementVisitor visitor, CaveElementHolder holder) {
        visitor.visit(this, holder);
    }

    /* Diamonds are slippery. */
    public CatcherStrategy getCatcherStrategy() {
        return SlipperyCatcherStrategy.INSTANCE;
    }
}
