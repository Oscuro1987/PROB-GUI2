package gui2.controller;

import gui2.model.Circle;
import gui2.model.Rectangle;
import gui2.model.Shape;
import gui2.view.Line;
import gui2.view.PaintJFrame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static gui2.view.PaintFrameState.*;

/**
 * @author CAPELLE.E
 *         Date: 23/02/13
 *         Time: 15:21
 */
public class PanelDrawingListener extends MouseAdapter
{
    private Point start, end, current;
    private gui2.model.Shape selectedShape = null;
    private PaintJFrame owner;
    
    public PanelDrawingListener(PaintJFrame owner)
    {
        this.owner = owner;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        
        current = new Point(e.getX(), e.getY());
        
        if(owner.getFrameState() == DELSHAPE)
        {
            for(Shape s : owner.getShapeList())
            {
                if(s.inBounds(current))
                {
                    System.out.println("Deleting a shape");
                    owner.getShapeList().remove(s);
                    owner.repaint();
                    break;
                }
            }
        }
        
        if(owner.getFrameState() == SETCOLOR)
        {
            Color col = owner.getColorPickerColor();
            
            for(Shape s : owner.getShapeList())
            {
                if(s.inBounds(current))
                {
                    System.out.println("Clicked at: " + current.x + ", " + current.y );
                    System.out.println("Shape position: " + s.getX() + ", " + s.getY());
                    System.out.println("Shape Size: " + s.getWidth() + ", " + s.getHeight());
                    System.out.println("Changing color of a: " + s.getClass().getSimpleName());
                    s.setColor(col);
                    owner.repaint();
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        start = new Point(e.getX(), e.getY());

        if(owner.getFrameState() == DRAWRECT)
        {
            System.out.println("Started drawing a rectangle");
        }
        else if(owner.getFrameState() == DRAWCIRCLE)
        {
            System.out.println("Started drawing a circle");
        }
        else if(owner.getFrameState() == DRAWLINE)
        {
            System.out.println("Started drawing a line");
        }
        else if(owner.getFrameState() == MOVESHAPE)
        {
            for(gui2.model.Shape s : owner.getShapeList())
            {
                if(s.inBounds(start))
                {
                    System.out.println("Clicked inside a: " + s.getClass().getSimpleName());
                    selectedShape = s;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        end = new Point(e.getX(), e.getY());

        int dx = end.x - start.x;
        int dy = end.y - start.y;

        if(owner.getFrameState() == DRAWRECT)
        {
            Rectangle r = new Rectangle(start, dx, dy);
            owner.getShapeList().add(r);
            owner.repaint();
            System.out.println("Finished drawing a rectangle");
        }
        else if(owner.getFrameState() == DRAWCIRCLE)
        {
            int radius = (int)Math.sqrt(Math.pow((end.x-start.y), 2) + Math.pow((end.y-start.y), 2) );
            Circle c = new Circle(start, radius/2);
            owner.getShapeList().add(c);
            owner.repaint();
            System.out.println("Finished drawing a circle");
        }
        else if(owner.getFrameState() == DRAWLINE)
        {
            Line l = new Line(start, end);
            owner.getShapeList().add(l);
            owner.repaint();
            System.out.println("Finished drawing a line");
        }
        else if(owner.getFrameState() == MOVESHAPE && selectedShape != null)
        {
            selectedShape.move(dx,dy);
            owner.repaint();
            selectedShape = null;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        current = new Point(e.getX(), e.getY());

        int dx = end.x - start.x;
        int dy = end.y - start.y;

        if(owner.getFrameState() == MOVESHAPE && selectedShape != null)
        {
            selectedShape.move(dx,dy);
            owner.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);

        current = new Point(e.getX(), e.getY());

        int dx = end.x - start.x;
        int dy = end.y - start.y;

        if(owner.getFrameState() == MOVESHAPE && selectedShape != null)
        {
            selectedShape.move(dx,dy);
            owner.repaint();
        }
    }
}
