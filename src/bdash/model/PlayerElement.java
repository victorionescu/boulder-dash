package bdash.model;

public class PlayerElement extends CaveElement {
    public static enum Direction {NORTH, SOUTH, EAST, WEST }

    /* The direction the player is oriented. */
    private final Direction direction;

    public PlayerElement(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public CaveElement deepClone() {
        return new PlayerElement(direction);
    }

    public void accept(CaveElementVisitor visitor, CaveElementHolder holder) {
        visitor.visit(this, holder);
    }

    public CatcherStrategy getCatcherStrategy() {
        return PlayerCatcherStrategy.INSTANCE;
    }
}
