package bdash.controller;

import bdash.model.*;

public class GravityVisitor implements CaveElementVisitor {
    public void visit(WallElement e) {}

    public void visit(HeavyElement e) {

    }

    public void visit(DirtElement e) {}
    public void visit(PlayerElement e) {}
    public void visit(EmptyElement e) {}
}
