package bdash.model;

public class DryCatcherStrategy implements CatcherStrategy {
    public static final DryCatcherStrategy INSTANCE = new DryCatcherStrategy();

    private DryCatcherStrategy() {}

    public void catchElement(HeavyElement elementToCatch, CaveElementHolder catcherHolder) {
        elementToCatch.setFalling(false);
    }
}
