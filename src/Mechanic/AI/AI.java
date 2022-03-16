package Mechanic.AI;

import Mechanic.MainVariables;
import Objects.Player;

import java.util.ArrayList;

public class AI {
    Player playerSelected;
    ArrayList<Condition> listOfConditions;
    ArrayList<Motion> listOfMotions;

    public AI(Player player, ArrayList<Condition> listOfConditions, ArrayList<Motion> listOfMotions) {
        this.playerSelected = player;
        this.listOfConditions = listOfConditions;
        this.listOfMotions = listOfMotions;
    }

    public void startAI() {
        Runnable runnable = () -> {
            while (true) {
                if (MainVariables.isAi) {
                    
                } else {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
