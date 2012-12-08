package controller;

import model.Cave;
import model.CaveElement;
import model.HeavyElement;

public class DryCatcher implements CaveElementCatcher {
    public static final DryCatcher INSTANCE = new DryCatcher();

    private DryCatcher() {}

    public void landOn(CaveElement e) {
        Cave cave = e.getCave();

        int x = e.getX();
        int y = e.getY();

        HeavyElement fallingElement = (HeavyElement)cave.getElement(x, y - 1);

        fallingElement.setFalling(false);
    }
}
