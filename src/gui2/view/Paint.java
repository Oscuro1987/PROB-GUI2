package gui2.view;

import gui2.model.ShapeList;

import javax.swing.*;

/**
 * @author CAPELLE.E
 *         Date: 22/02/13
 *         Time: 21:07
 */
public class Paint {
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.opengl","True");
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShapeList shapes = new ShapeList();
                new PaintJFrame(shapes);
            }
        });
    }
}
