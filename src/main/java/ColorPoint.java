import java.awt.*;

public class ColorPoint {
    private Color color;
    //initialization
    private int x, y;
    //declaring variables

    public ColorPoint(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    //setters

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    //getters
}