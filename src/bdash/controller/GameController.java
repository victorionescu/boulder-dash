package bdash.controller;

import bdash.model.Cave;
import bdash.view.CaveView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameController {
    private final CaveView caveView;
    private final Timer timer;

    public GameController(CaveView caveView) {
        this.caveView = caveView;
        timer = new Timer(180, new ActionListener(){
            public void actionPerformed(ActionEvent e) {

            }
        });
        timer.start();
    }

    public void stopTimer() {
        timer.stop();
    }
}
