package bdash.model;

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


    public abstract CaveElement deepClone();
    public abstract void accept(CaveElementVisitor visitor, CaveElementHolder holder);
    public abstract CatcherStrategy getCatcherStrategy();
}
