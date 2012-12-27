package bdash.model;

public class EmptyElement extends CaveElement {
    public CaveElement deepClone() {
        return new EmptyElement();
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
