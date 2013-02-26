package gui2.model;

import java.awt.*;

/**
 * @author CAPELLE.E
 *         Date: 22/02/13
 *         Time: 21:13
 */
public class Circle implements Shape {
    Point topLeft;
    int radius;
    private Color color;
    
    public Circle(Point topLeft, int radius)
    {
        this.topLeft = topLeft;
        this.radius = radius;
        color = new Color(0f,0f,0f);
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(topLeft.x, topLeft.y, radius, radius);
    }

    @Override
    public boolean inBounds(Point p) {
        double dx,dy;
        dx = p.getX()- topLeft.x;
        dy = p.getY()- topLeft.y;
        return dx*dx+dy*dy <= radius*radius;
    }

    @Override
    public void move(int dx, int dy) {
        topLeft.x += dx;
        topLeft.y += dy;
    }

    @Override
    public void moveTo(int x, int y) {
        topLeft.x = x;
        topLeft.y = y;
    }

    @Override
    public int getX() {
        return topLeft.x;
    }

    @Override
    public int getY() {
        return topLeft.y;
    }

    @Override
    public int getWidth() {
        return radius*2;
    }

    @Override
    public int getHeight() {
        return radius*2;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
