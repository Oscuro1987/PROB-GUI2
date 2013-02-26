package gui2.view;

import gui2.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * @author CAPELLE.E
 *         Date: 22/02/13
 *         Time: 21:08
 */
public class PaintJPanel extends JPanel {

    ShapeList model;

    public PaintJPanel(ShapeList model)
    {
        this.model = model;
        init();
    }

    private void init() {
        setBackground(new Color(1f,1f,1f));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(gui2.model.Shape s : model)
            s.render(g);
        g.dispose();
    }
}
