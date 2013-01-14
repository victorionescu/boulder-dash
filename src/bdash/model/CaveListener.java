package bdash.model;

public interface CaveListener {
    public void caveElementWillChange(Cave cave, CaveElementHolder elementHolder);

    public void caveElementChanged(Cave cave, CaveElementHolder elementHolder);

    public void gameLost();
}
