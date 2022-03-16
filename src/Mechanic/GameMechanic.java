package Mechanic;

import Mechanic.AI.AI;
import Mechanic.AI.Condition;
import Mechanic.AI.Motion;
import Mechanic.Draw.DrawPanel;
import Objects.GameObject;
import Objects.Player;
import Objects.Spike;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class GameMechanic extends MainVariables {
    void preparation() {
        mainFrame.setSize(1000, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        DrawPanel drawPanel = new DrawPanel();
        mainFrame.add(drawPanel);
        drawPanel.setBounds(0, 0, 1000, 600);
        mainFrame.addKeyListener(new FrameKeyListener());

        Condition condition = new Condition("distance to the nearest obstacle");
        listOfConditions.add(condition);

        Motion motion = new Motion("jump");
        listOfMotions.add(motion);

        Player player = new Player(300, 400, 20, 20, new Color(0xE43826));
        listOfGamePlayers.add(player);

        AI ai = new AI(player, listOfConditions, listOfMotions);
        listOfGameAIs.add(ai);
        ai.startAI();

        Spike spike = new Spike(1000, 400, 20, 20, new Color(0));
        listOfGameObjects.add(spike);

        mainFrame.setVisible(true);
        isGameActive = true;
        startGame();
    }

    public static void playSound(File soundFile) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(ais);

            FloatControl vc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            vc.setValue(1);

            clip.setFramePosition(0);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }
    private void startGame() {
        runnableGame = () -> {
            while (isGameActive) {
                mainFrame.repaint();
                int maxX = 0;
                GameObject gameObjectRemove = null;
                for (GameObject gameObject : listOfGameObjects) {
                    gameObject.xOnFrame-=3;
                    if (gameObject.xOnFrame == 0-gameObject.width) {
                        gameObjectRemove = gameObject;
                    }
                    if (gameObject.xOnFrame > maxX) {
                        maxX = gameObject.xOnFrame;
                    }
                }
                if (gameObjectRemove != null) {
                    listOfGameObjects.remove(gameObjectRemove);
                }
                for (Player player : listOfGamePlayers) {
                    player.x++;
                }
                if (maxX != 0 && maxX < 800) {
                    int randCount = (int) (Math.random()*5);
                    int randX = (int) (Math.random()*200)+1000;
                    switch (randCount) {
                        case 0:
                            Spike spike1 = new Spike(randX, 400, 20, 20, new Color(0));
                            listOfGameObjects.add(spike1);
                            break;
                        case 1:
                            Spike spike2 = new Spike(randX, 400, 20, 20, new Color(0));
                            Spike spike3 = new Spike(randX+20, 400, 20, 20, new Color(0));
                            listOfGameObjects.add(spike2);
                            listOfGameObjects.add(spike3);
                            break;
                        case 2:
                            Spike spike4 = new Spike(randX, 400, 20, 20, new Color(0));
                            Spike spike5 = new Spike(randX+20, 400, 20, 20, new Color(0));
                            Spike spike6 = new Spike(randX+40, 400, 20, 20, new Color(0));
                            listOfGameObjects.add(spike4);
                            listOfGameObjects.add(spike5);
                            listOfGameObjects.add(spike6);
                            break;
                        case 3:
                            Spike spike7 = new Spike(randX, 400, 20, 20, new Color(0));
                            Spike spike8 = new Spike(randX+20, 400, 20, 20, new Color(0));
                            Spike spike9 = new Spike(randX+40, 400, 20, 20, new Color(0));
                            Spike spike10 = new Spike(randX+60, 400, 20, 20, new Color(0));
                            listOfGameObjects.add(spike7);
                            listOfGameObjects.add(spike8);
                            listOfGameObjects.add(spike9);
                            listOfGameObjects.add(spike10);
                            break;
                        case 4:
                            Spike spike11 = new Spike(randX, 390, 30, 30, new Color(0));
                            listOfGameObjects.add(spike11);
                    }
                }
                boolean isDeath = false;
                for (Player player : listOfGamePlayers) {
                    for (GameObject gameObject : listOfGameObjects) {
                        for (int x = player.xOnFrame; x < player.xOnFrame+player.width; x++) {
                            for (int x1 = gameObject.xOnFrame; x1 < gameObject.xOnFrame+gameObject.width; x1++) {
                                if (x == x1) {
                                    for (int y = player.yOnFrame; y < player.yOnFrame+player.height; y++) {
                                        for (int y1 = gameObject.yOnFrame; y1 < gameObject.yOnFrame+gameObject.height; y1++) {
                                            if (y == y1) {
                                                isDeath = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (isDeath)
                    death();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        threadGame = new Thread(runnableGame);
        threadGame.start();

        runnableJump = () -> {
            while (isGameActive) {
                if (isSpace) {
                    playSound(new File("sfx-13.wav"));
                    for (Player player : listOfGamePlayers) {
                        if (player.yOnFrame == 400) {
                            for (int x = 0; x < 30; x++) {
                                player.yOnFrame -= 2;
                                try {
                                    Thread.sleep(4);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            for (int x = 0; x < 20; x++) {
                                player.yOnFrame--;
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            for (int x = 0; x < 20; x++) {
                                player.yOnFrame++;
                                try {
                                    Thread.sleep(5);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            for (int x = 0; x < 30; x++) {
                                player.yOnFrame += 2;
                                try {
                                    Thread.sleep(4);
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }
                        player.yOnFrame = 400;
                        player.y = 0;
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        threadJump = new Thread(runnableJump);
        threadJump.start();

        runnableAuto = () -> {
            while (isGameActive) {
                if (isAuto) {
                    for (GameObject gameObject : listOfGameObjects) {
                        if (gameObject.xOnFrame - listOfGamePlayers.get(0).xOnFrame < 45 && gameObject.xOnFrame - listOfGamePlayers.get(0).xOnFrame > -1) {
                            isSpace = true;
                        }
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isSpace = false;
                } else {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        threadAuto = new Thread(runnableAuto);
        threadAuto.start();

        isAi = true;
    }
    private void death() {
        playSound(new File("mario-smert.wav"));
        Runnable runnableDeath = () -> {
            isGameActive = false;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("death");
            countOfDeaths++;
            for (Player player : listOfGamePlayers) {
                player.x = 0;
                player.yOnFrame = 400;
                player.y = 0;
            }
            listOfGameObjects = new ArrayList<>();
            Spike spike = new Spike(1000, 400, 20, 20, new Color(0));
            listOfGameObjects.add(spike);

            isGameActive = true;
            startGame();
        };
        Thread threadDeath = new Thread(runnableDeath);
        threadDeath.start();
    }

    private class FrameKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyChar()) {
                case ' ':
                    if (!isAuto) {
                        isSpace = true;
                    }
                    break;
                case 'i':
                case 'I':
                case 'ш':
                case 'Ш':
                    if (isAuto) {
                        isAuto = false;
                    } else {
                        isSpace = false;
                        isAuto = true;
                    }
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyChar()) {
                case ' ':
                    if (!isAuto) {
                        isSpace = false;
                    }
                    break;
            }
        }
    }
}
