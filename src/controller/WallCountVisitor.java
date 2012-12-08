package controller;

import model.*;

public class WallCountVisitor implements CaveElementVisitor {
    private int count;

    public WallCountVisitor() {
        count = 0;
    }

    public void visit(WallElement e) {
        count += 1;
    }

    public void visit(HeavyElement e) {}
    public void visit(DirtElement e) {}
    public void visit(PlayerElement e) {}
}
