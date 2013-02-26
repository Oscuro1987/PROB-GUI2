package gui2.view;

import gui2.model.Shape;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * @author Oscuro
 *         Date: 25/02/13
 *         Time: 02:13
 */
public class Line implements Shape {
    
    private Point start, end;
    private Color color;
    
    public Line(Point start, Point end)
    {
        this.start = start;
        this.end = end;
        color = new Color(0f,0f,0f);
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    @Override
    public boolean inBounds(Point p) {
        if(Line2D.ptLineDist(start.x, start.y, end.x, end.y, p.x, p.y) >= -5.0 && Line2D.ptSegDist(start.x, start.y, end.x, end.y, p.x, p.y) <= 5.0)
            return true;
        return false;
    }

    @Override
    public void move(int dx, int dy) {
        start.x += dx;
        start.y += dy;
        end.x += dx;
        end.y += dy;
    }

    @Override
    public void moveTo(int x, int y) {
        //TODO moveTo ligne
        return; 
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
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
