package bdash.model;

public interface CaveElementVisitor {
    public void visit(WallElement e);
    public void visit(BoulderElement e);
    public void visit(DiamondElement e);
    public void visit(DirtElement e);
    public void visit(PlayerElement e);
    public void visit(EmptyElement e);
}
