package bdash.model;

public class BoulderElement extends HeavyElement {
    public boolean isLethal() {
        return true;
    }

    public CaveElement deepClone() {
        return new BoulderElement();
    }

    public void accept(CaveElementVisitor visitor, CaveElementHolder holder) {
        visitor.visit(this, holder);
    }

    public CatcherStrategy getCatcherStrategy() {
        return SlipperyCatcherStrategy.INSTANCE;
    }
}
