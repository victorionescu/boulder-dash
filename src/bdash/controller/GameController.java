package bdash.controller;

import bdash.model.Cave;
import bdash.model.CaveElement;
import bdash.model.CaveElementHolder;
import bdash.model.GravityVisitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.Timer;

public class GameController {
    private final Cave cave;
    private final Timer timer;

    public GameController(Cave cave) {
        this.cave = cave;

        timer = new Timer(100, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Cave cave = GameController.this.cave;

                Iterator<CaveElementHolder> elementHolders = cave.getElementHolders();

                while (elementHolders.hasNext()) {
                    CaveElementHolder elementHolder = elementHolders.next();
                    if (elementHolder.getCaveElement() != null) {
                        elementHolder.getCaveElement().accept(GravityVisitor.INSTANCE, elementHolder);
                    }
                }
            }
        });

        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
