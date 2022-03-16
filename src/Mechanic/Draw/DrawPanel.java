package Mechanic.Draw;

import Objects.GameObject;
import Objects.Player;

import javax.swing.*;
import java.awt.*;

import static Mechanic.MainVariables.*;

public class DrawPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(0x6FFF05));
        g.fillRect(0, 420, 1000, 180);
        for (GameObject gameObject : listOfGameObjects) {
            g.setColor(gameObject.color);
            g.fillRect(gameObject.xOnFrame, gameObject.yOnFrame, gameObject.width, gameObject.height);
        }
        int max = 0;
        for (Player player : listOfGamePlayers) {
            g.setColor(player.color);
            g.fillRect(player.xOnFrame, player.yOnFrame, player.width, player.height);
            if (player.x > max) {
                max = player.x;
            }
        }
        g.drawString(""+max, 10, 20);
        if (isAuto)
            g.drawString("Режим автопилота", 10, 50);
    }
}
