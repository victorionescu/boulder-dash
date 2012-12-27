package bdash.model;

public class DirtElement extends CaveElement {
    public CaveElement deepClone() {
        return new DirtElement();
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
