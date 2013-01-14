package bdash.model;

public interface CaveElementVisitor {
    void visit(WallElement e, CaveElementHolder holder);
    void visit(BoulderElement e, CaveElementHolder holder);
    void visit(DiamondElement e, CaveElementHolder holder);
    void visit(DirtElement e, CaveElementHolder holder);
    void visit(PlayerElement e, CaveElementHolder holder);
}
