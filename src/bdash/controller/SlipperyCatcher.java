package bdash.controller;

import bdash.model.Cave;
import bdash.model.CaveElement;
import bdash.model.HeavyElement;

public class SlipperyCatcher implements CaveElementCatcher {
    public static final SlipperyCatcher INSTANCE = new SlipperyCatcher();

    private SlipperyCatcher() {}

    public void landOn(CaveElement e) {
        Cave cave = e.getCave();

        int x = e.getX();
        int y = e.getY();

        HeavyElement fallingElement = (HeavyElement)cave.getElement(x, y - 1);

        if ((x + 1) < cave.getWidth() && cave.getElement(x + 1, y - 1) == null &&
                cave.getElement(x + 1, y) == null) {
            /* NEEDS CODE TO HANDLE THIS */
        } else if ((x - 1) >= 0 && cave.getElement(x - 1, y - 1) == null &&
                cave.getElement(x - 1, y) == null) {
            /* NEEDS CODE TO HANDLE THIS */
        } else {
            fallingElement.setFalling(false);
        }
    }
}
