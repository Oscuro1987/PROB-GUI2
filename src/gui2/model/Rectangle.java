package gui2.model;

import java.awt.*;

/**
 * @author CAPELLE.E
 *         Date: 22/02/13
 *         Time: 21:12
 */
public class Rectangle implements Shape {

    Point topLeft;
    int width, height;
    private Color color;

    public Rectangle(Point topLeft, int width, int height) {
        this.topLeft = topLeft;
        if (width < 0) {
            topLeft.x += width;
            width = -width;
        }
        this.width = width;
        if (height < 0) {
            topLeft.y += height;
            height = -height;
        }
        this.height = height;
        color = new Color(0f, 0f, 0f);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(topLeft.x, topLeft.y, width, height);
    }

    @Override
    public boolean inBounds(Point p) {
        if (p.getX() > topLeft.x && p.getX() < topLeft.x + width
                && p.getY() > topLeft.getY() && p.getY() < topLeft.y + height)
            return true;
        return false;
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
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setColor(Color col) {
        this.color = col;
    }

    public Color getColor() {
        return color;
    }
}
