package Mechanic;

import Mechanic.AI.AI;
import Mechanic.AI.Condition;
import Mechanic.AI.Motion;
import Objects.GameObject;
import Objects.Player;

import javax.swing.*;
import java.util.ArrayList;

public class MainVariables {
    Runnable runnableGame;
    Thread threadGame;
    Runnable runnableJump;
    Thread threadJump;
    Runnable runnableAuto;
    Thread threadAuto;

    public static JFrame mainFrame = new JFrame("Jump !");

    public static ArrayList<GameObject> listOfGameObjects = new ArrayList<>();
    public static ArrayList<Player> listOfGamePlayers = new ArrayList<>();
    public static ArrayList<AI> listOfGameAIs = new ArrayList<>();
    public static ArrayList<Condition> listOfConditions = new ArrayList<>();
    public static ArrayList<Motion> listOfMotions = new ArrayList<>();

    public static boolean isSpace = false;
    public static boolean isAuto = false;
    public static boolean isAi = false;
    public static boolean isGameActive = false;

    public static int countOfDeaths = 0;
}
