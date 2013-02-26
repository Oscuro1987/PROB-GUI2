package gui2.model;

import java.awt.*;

/**
 * @author CAPELLE.E
 *         Date: 22/02/13
 *         Time: 21:10
 */
public interface Shape {
    void render(Graphics g);
    boolean inBounds(Point p);
    void move(int dx, int dy);
    void moveTo(int x, int y);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void setColor(Color color);
    Color getColor();
}
