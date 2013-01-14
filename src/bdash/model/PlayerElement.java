package bdash.model;

public class PlayerElement extends CaveElement {
    public static enum LastDirection {NORTH, SOUTH, EAST, WEST }

    private final LastDirection lastDirection;

    public PlayerElement() {
        this.lastDirection = LastDirection.WEST;
    }

    public LastDirection getLastDirection() {
        return lastDirection;
    }

    public CaveElement deepClone() {
        return new PlayerElement();
    }

    public void accept(CaveElementVisitor visitor, CaveElementHolder holder) {
        visitor.visit(this, holder);
    }

    public CatcherStrategy getCatcherStrategy() {
        return PlayerCatcherStrategy.INSTANCE;
    }
}
