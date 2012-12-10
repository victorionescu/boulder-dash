package bdash.model;

public interface CaveListener {
    public void caveElementWillChange(Cave cave, CaveElement caveElement);

    public void caveElementChanged(Cave cave, CaveElement caveElement);
}
