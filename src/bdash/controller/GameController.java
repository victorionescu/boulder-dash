package bdash.controller;

import bdash.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import javax.swing.Timer;

/*
    This class acts as the main game controller. It is responsible for both the player's moves and the gravity
    force that pulls down HeavyElement's.

    A GravityVisitor iterates through the whole cave for this purpose.
 */

public class GameController {
    /* Cave that the controller acts on. */
    private final Cave cave;

    /* Timer responsible with gravity. */
    private final Timer timer;

    /*
        The frequency at which gravity changes the state of the cave.
        Change constant here for different user experience.
     */
    private final int GRAVITY_STEP_IN_MS = 100;

    public GameController(Cave cave) {
        this.cave = cave;

        /* Gravity changes the state of the cave every GRAVITY_STEP_IN_MS milliseconds. */
        timer = new Timer(GRAVITY_STEP_IN_MS, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Cave cave = GameController.this.cave;

                Iterator<CaveElementHolder> elementHolders = cave.getElementHolders();

                while (elementHolders.hasNext()) {
                    CaveElementHolder elementHolder = elementHolders.next();
                    if (elementHolder.getCaveElement() != null) {
                        /* The GravityVisitor will be only effective on HeavyElement's */
                        elementHolder.getCaveElement().accept(GravityVisitor.INSTANCE, elementHolder);
                    }
                }
            }
        });

        timer.start();
    }

    /* Stop the timer and, consequently, the game. */
    public void stopTimer() {
        timer.stop();
    }

    /* Move player in a certain direction (NORTH, SOUTH, EAST, WEST) */
    public void movePlayer(PlayerElement.Direction direction) {
        /* We use a FindPlayerVisitor to locate the player on the map. */
        FindPlayerVisitor findPlayerVisitor = new FindPlayerVisitor();
        Iterator<CaveElementHolder> elementHolders = cave.getElementHolders();

        while (elementHolders.hasNext()) {
            CaveElementHolder elementHolder = elementHolders.next();
            if (elementHolder.getCaveElement() != null) {
                elementHolder.getCaveElement().accept(findPlayerVisitor, elementHolder);
            }
        }

        CaveElementHolder playerHolder = findPlayerVisitor.getPlayerHolder();

        /* If there is no player on the map, then we cannot move. */
        if (playerHolder == null) {
            return;
        }

        PlayerElement playerElement = new PlayerElement(direction);

        playerHolder.setCaveElement(playerElement);

        int row = playerHolder.getRow();
        int column = playerHolder.getColumn();

        int rowOffset = 0;
        int columnOffset = 0;

        switch (direction) {
            case EAST:
                columnOffset = 1;
                break;
            case WEST:
                columnOffset = -1;
                break;
            case NORTH:
                rowOffset = -1;
                break;
            case SOUTH:
                rowOffset = 1;
                break;
            default:
        }

        /* The cell that the player intends to visit by making this move is at (neighborRow, neighborColumn). */
        int neighborRow = row + rowOffset;
        int neighborColumn = column + columnOffset;

        /* The desired location needs, first of all, to be in the cave. */
        if (neighborRow >= 0 && neighborRow < cave.getHeight() &&
                neighborColumn >= 0 && neighborColumn < cave.getWidth()) {
            CaveElementHolder neighborHolder = cave.getElementHolder(neighborRow, neighborColumn);

            if (neighborHolder.getCaveElement() == null) {
                /* If the neighboring cell is free, we can move directly. */
                playerHolder.setCaveElement(null);
                neighborHolder.setCaveElement(playerElement);
            } else {
                /*
                    Otherwise, we need to interact with the element in the neighboring cell, for which we use
                    an instance of PlayerVisitor.
                 */
                PlayerVisitor playerVisitor = new PlayerVisitor(playerHolder);
                neighborHolder.getCaveElement().accept(playerVisitor, neighborHolder);
            }
        }
    }
}
