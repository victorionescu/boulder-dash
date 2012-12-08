package controller;

import model.Cave;
import model.CaveElement;
import model.HeavyElement;

public class PlayerCatcher implements CaveElementCatcher {
    public static final PlayerCatcher INSTANCE = new PlayerCatcher();

    private PlayerCatcher() {}

    public void landOn(CaveElement e) {
        Cave cave = e.getCave();

        int x = e.getX();
        int y = e.getY();

        HeavyElement fallingElement = (HeavyElement)cave.getElement(x, y - 1);

        if (fallingElement.isLethal()) {
            System.out.println("GAME OVER!");
        } else {
            fallingElement.setFalling(false);
        }
    }
}
