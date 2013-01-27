package bdash.model;
/*
    Interface for objects that listen to changes in the cave's state.
 */
public interface CaveListener {
    /* This is fired before the immediate change of an element. */
    public void caveElementWillChange(Cave cave, CaveElementHolder elementHolder);

    /* This is fired after an element has changed. */
    public void caveElementChanged(Cave cave, CaveElementHolder elementHolder);

    /* This is fired after a diamond has been collected by the player. */
    public void diamondCollected();

    /* This is fired after the game has been lost (i.e. the player has been killed). */
    public void gameLost();
}
