package bdash.model;

public class DirtElement extends CaveElement {
    public CaveElement deepClone() {
        return new DirtElement();
    }

    public void accept(CaveElementVisitor visitor, CaveElementHolder holder) {
        visitor.visit(this, holder);
    }

    public CatcherStrategy getCatcherStrategy() {
        return DryCatcherStrategy.INSTANCE;
    }
}
