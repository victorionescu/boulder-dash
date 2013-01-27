package bdash.model;


/*
    Class representing the dirt.
 */

public class DirtElement extends CaveElement {
    public CaveElement deepClone() {
        return new DirtElement();
    }

    public void accept(CaveElementVisitor visitor, CaveElementHolder holder) {
        visitor.visit(this, holder);
    }

    /* Whenever an element lands on dirt, it stops falling. */
    public CatcherStrategy getCatcherStrategy() {
        return DryCatcherStrategy.INSTANCE;
    }
}
