package bdash.model;

/*
    This is an abstract class, extending CaveElement, that factors out the heavy elements. (i.e. diamonds, boulders).

    This associates a 'falling' flag with the element which will denote whether the object is falling or not.

    It also, provides an interface for deciding whether the element is lethal when lands on a player or not.
 */

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

    /*
        This decides whether the object is lethal or not. Given that complex objects may arise, this has been
        factored out as a method, rather than a flag.
     */
    public abstract boolean isLethal();
}
