package bdash.model;

public class PlayerCatcherStrategy implements CatcherStrategy {
    public static final PlayerCatcherStrategy INSTANCE = new PlayerCatcherStrategy();

    private PlayerCatcherStrategy() {}

    public void catchElement(HeavyElement elementToCatch, CaveElementHolder catcherHolder) {
        elementToCatch.setFalling(false);

        if (elementToCatch.isLethal()) {
            catcherHolder.cave.fireGameLost();
        }
    }
}
