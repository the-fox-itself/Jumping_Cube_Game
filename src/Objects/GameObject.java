package Objects;

import java.awt.*;

public class GameObject {
    public String name;
    public int xOnFrame;
    public int yOnFrame;
    public int x;
    public int y;
    public int width;
    public int height;
    public Color color;

    public GameObject(int xOnFrame, int yOnFrame, int width, int height, Color color) {
        this.xOnFrame = xOnFrame;
        this.yOnFrame = yOnFrame;
        this.x = xOnFrame - 300;
        this.y = yOnFrame - 400;
        this.width = width;
        this.height = height;
        this.color = color;
    }
}
