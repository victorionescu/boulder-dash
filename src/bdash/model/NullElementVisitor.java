package bdash.model;

import bdash.model.*;

public class NullElementVisitor implements CaveElementVisitor {
    public void visit(BoulderElement boulderElement, CaveElementHolder holder) {}

    public void visit(DiamondElement diamondElement, CaveElementHolder holder) {}

    public void visit(DirtElement dirtElement, CaveElementHolder holder) {}

    public void visit(PlayerElement playerElement, CaveElementHolder holder) {}

    public void visit(WallElement wallElement, CaveElementHolder holder) {}
}
