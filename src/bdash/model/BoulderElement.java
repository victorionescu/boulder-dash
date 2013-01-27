package bdash.model;

/*
    Class representing the boulders.
 */

public class BoulderElement extends HeavyElement {
    /* Boulders are lethal. */
    public boolean isLethal() {
        return true;
    }

    public CaveElement deepClone() {
        return new BoulderElement();
    }

    public void accept(CaveElementVisitor visitor, CaveElementHolder holder) {
        visitor.visit(this, holder);
    }

    /* Boulders are slippery. */
    public CatcherStrategy getCatcherStrategy() {
        return SlipperyCatcherStrategy.INSTANCE;
    }
}
