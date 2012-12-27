package bdash.model;

public class DiamondElement extends HeavyElement {
    public boolean isLethal() {
        return true;
    }

    public CaveElement deepClone() {
        return new DiamondElement();
    }

    public void accept(CaveElementVisitor visitor) {
        visitor.visit(this);
    }
}
