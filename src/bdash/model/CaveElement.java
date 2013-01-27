package bdash.model;

/*
    Abstract class for the CaveElement.

    CaveElement's are value objects, so both 'equals' and 'hashCode' have been overridden.

    Also, we need to clone the elements, so a version of clone depending on the abstract 'deepClone' has been
    implemented.
 */


public abstract class CaveElement implements Cloneable {
    public CaveElement clone() {
        return deepClone();
    }

    public boolean equals(Object object) {
        return (object.getClass() == this.getClass());
    }

    public int hashCode() {
        return (this.getClass().hashCode());
    }


    /* 'deepClone' should be implemented for every CaveElement and should return a deep clone of the element. */
    public abstract CaveElement deepClone();

    /* This tells the element that it should accept 'visitor' while positioned in 'holder'. */
    public abstract void accept(CaveElementVisitor visitor, CaveElementHolder holder);

    /*
        Returns a strategy that decides what happens with an element that lands on this element, as a
        consequence of falling.
     */
    public abstract CatcherStrategy getCatcherStrategy();
}
