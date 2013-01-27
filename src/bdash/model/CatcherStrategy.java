package bdash.model;
/*
    Strategy interface used by elements to be able to decide what happens when another element lands on them.
 */
public interface CatcherStrategy {
    void catchElement(HeavyElement elementToCatch, CaveElementHolder catcherHolder);
}
