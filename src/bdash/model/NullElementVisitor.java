package bdash.model;
/*
    Visitor implementing the null object pattern, that can easily be extended by visitors with reduced
    functionality.
 */

public class NullElementVisitor implements CaveElementVisitor {
    public void visit(BoulderElement boulderElement, CaveElementHolder holder) {}

    public void visit(DiamondElement diamondElement, CaveElementHolder holder) {}

    public void visit(DirtElement dirtElement, CaveElementHolder holder) {}

    public void visit(PlayerElement playerElement, CaveElementHolder holder) {}

    public void visit(WallElement wallElement, CaveElementHolder holder) {}
}
