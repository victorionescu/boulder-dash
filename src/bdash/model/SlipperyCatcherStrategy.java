package bdash.model;

public class SlipperyCatcherStrategy implements CatcherStrategy {

    public static final SlipperyCatcherStrategy INSTANCE = new SlipperyCatcherStrategy();

    private SlipperyCatcherStrategy() {}

    public void catchElement(HeavyElement elementToCatch, CaveElementHolder catcherHolder) {
        Cave cave = catcherHolder.cave;

        int row = catcherHolder.getRow();
        int column = catcherHolder.getColumn();

        if (column < (cave.getWidth() - 1) && cave.getElementHolder(row - 1, column + 1).getCaveElement() == null &&
                cave.getElementHolder(row, column + 1).getCaveElement() == null) {

            cave.getElementHolder(row, column + 1).setCaveElement(elementToCatch);
            cave.getElementHolder(row - 1, column).setCaveElement(null);

        } else if (column > 0 && cave.getElementHolder(row - 1, column - 1).getCaveElement() == null &&
                cave.getElementHolder(row, column - 1).getCaveElement() == null) {

            cave.getElementHolder(row, column - 1).setCaveElement(elementToCatch);
            cave.getElementHolder(row - 1, column).setCaveElement(null);

        } else {

            elementToCatch.setFalling(false);

        }
    }
}
