package Objects;

import java.awt.*;

public class Player extends GameObject {
    public Player(int xOnFrame, int yOnFrame, int width, int height, Color color) {
        super(xOnFrame, yOnFrame, width, height, color);
        name = "Player";
    }
}
