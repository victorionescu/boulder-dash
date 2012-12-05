package model;

import java.awt.*;

public abstract class HeavyElement extends CaveElement {
    private boolean falling;

    public HeavyElement(Cave cave, Point coordinates) {
        super(cave, coordinates);
        falling = false;
    }

    public boolean isHeavy() {
        return true;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }
}
