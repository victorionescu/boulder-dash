package bdash.model;
/*
    CatcherStrategy used by the player.
 */
public class PlayerCatcherStrategy implements CatcherStrategy {
    public static final PlayerCatcherStrategy INSTANCE = new PlayerCatcherStrategy();

    private PlayerCatcherStrategy() {}

    public void catchElement(HeavyElement elementToCatch, CaveElementHolder catcherHolder) {
        elementToCatch.setFalling(false);

        /* Game is lost if landing object is lethal. */
        if (elementToCatch.isLethal()) {
            catcherHolder.cave.fireGameLost();
        }
    }
}
