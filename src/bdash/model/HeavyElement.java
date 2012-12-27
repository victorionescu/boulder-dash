package bdash.model;

public abstract class HeavyElement extends CaveElement {
    protected boolean falling;

    public HeavyElement() {
        falling = false;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public abstract boolean isLethal();
}
