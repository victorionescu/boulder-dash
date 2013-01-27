package bdash.model;

/*
    CatcherStrategy related to dry objects, such as walls and dirt.
 */


public class DryCatcherStrategy implements CatcherStrategy {
    public static final DryCatcherStrategy INSTANCE = new DryCatcherStrategy();

    private DryCatcherStrategy() {}

    public void catchElement(HeavyElement elementToCatch, CaveElementHolder catcherHolder) {
        /* Object stops falling. */
        elementToCatch.setFalling(false);
    }
}
