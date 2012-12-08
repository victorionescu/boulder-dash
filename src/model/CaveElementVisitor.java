package model;

public interface CaveElementVisitor {
    public void visit(WallElement e);
    public void visit(HeavyElement e);
    public void visit(DirtElement e);
    public void visit(PlayerElement e);
}
