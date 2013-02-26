package gui2.view;

import gui2.controller.PanelDrawingListener;
import gui2.model.ShapeList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static gui2.view.PaintFrameState.*;

/**
 * @author CAPELLE.E
 *         Date: 22/02/13
 *         Time: 21:08
 */
public class PaintJFrame extends JFrame {
    private ShapeList model;
    
    // PANELS
    private PaintJPanel paintPanel;
    private JPanel bottomPanel;
    private JPanel colorPickerPanel;
    private JPanel buttonPanel;
    
    // BUTTONS
    private JButton addRectangleBtn;
    private JButton addCircleBtn;
    private JButton addLineBtn;
    private JButton moveShapeBtn;
    private JButton deleteShapeBtn;
    private JButton setColorBtn;
    
    // LABELS
    private JLabel redLabel, greenLabel, blueLabel;
    
    // TEXTFIELDS
    private JTextField red, green, blue;
    
    // STATES
    public PaintFrameState state;
    
    public PaintJFrame(ShapeList model)
    {
        this.model = model;
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout());
        this.setTitle("PROB Paint");

        state = IDLE;
        
        paintPanel = new PaintJPanel(model);
        this.add(paintPanel, BorderLayout.CENTER);
        
        bottomPanel = new JPanel(new BorderLayout());
        
        buttonPanel = new JPanel(new FlowLayout());
        addRectangleBtn = new JButton("Add Rectangle");
        addCircleBtn = new JButton("Add Circle");
        addLineBtn = new JButton("Add Line");
        moveShapeBtn = new JButton("Move Shapes");
        deleteShapeBtn = new JButton("Delete Shapes");
        
        buttonPanel.add(addRectangleBtn);
        buttonPanel.add(addCircleBtn);
        buttonPanel.add(addLineBtn);
        buttonPanel.add(new JSeparator(JSeparator.VERTICAL));
        buttonPanel.add(moveShapeBtn);
        buttonPanel.add(deleteShapeBtn);

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        
        colorPickerPanel = new JPanel(new FlowLayout());

        redLabel = new JLabel("Color Picker =====>       R: ");
        greenLabel = new JLabel("G: ");
        blueLabel = new JLabel("B: ");
        red = new JTextField("0", 4);
        green = new JTextField("0", 4);
        blue = new JTextField("0", 4);
        setColorBtn = new JButton("Set Color Mode");
        
        colorPickerPanel.add(redLabel);
        colorPickerPanel.add(red);
        colorPickerPanel.add(greenLabel);
        colorPickerPanel.add(green);
        colorPickerPanel.add(blueLabel);
        colorPickerPanel.add(blue);
        colorPickerPanel.add(setColorBtn);
        
        bottomPanel.add(colorPickerPanel, BorderLayout.SOUTH);
        
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        initListeners();
        
        this.pack();
        this.setSize(800,600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initListeners() {
        addRectangleBtn.addMouseListener(new MouseButtonListener());
        addCircleBtn.addMouseListener(new MouseButtonListener());
        addLineBtn.addMouseListener(new MouseButtonListener());
        moveShapeBtn.addMouseListener(new MouseButtonListener());
        deleteShapeBtn.addMouseListener(new MouseButtonListener());
        setColorBtn.addMouseListener(new MouseButtonListener());
        
        paintPanel.addMouseListener(new PanelDrawingListener(this));
    }

    @Override
    public void revalidate() {
        super.revalidate();
        
        addRectangleBtn.setText("Add Rectangle");
        addCircleBtn.setText("Add Circle");
        addLineBtn.setText("Add Line");
        moveShapeBtn.setText("Move Shapes");
        deleteShapeBtn.setText("Delete Shapes");
        setColorBtn.setText("Set Color Mode");
        
        switch(state)
        {
            case DRAWRECT:
                addRectangleBtn.setText("Add Rectangle (ACTIVE)");
                break;
            case DRAWCIRCLE:
                addCircleBtn.setText("Add Circle (ACTIVE)");
                break;
            case DRAWLINE:
                addLineBtn.setText("Add Line (ACTIVE)");
                break;
            case MOVESHAPE:
                moveShapeBtn.setText("Move Shapes (ACTIVE)");
                break;
            case DELSHAPE:
                deleteShapeBtn.setText("Delete Shapes (ACTIVE)");
                break;
            case SETCOLOR:
                setColorBtn.setText("Set Color Mode (ACTIVE)");
                break;
        }
    }
    
    public ShapeList getShapeList()
    {
        return model;
    }
    
    public PaintFrameState getFrameState()
    {
        return state;
    }
    
    public Color getColorPickerColor()
    {
        int r,g,b;
        
        try {
            r = Integer.parseInt(red.getText());
            if(r < 0 | r > 255)
            {
                r = 0;
                red.setText("0");
            }
        }
        catch (NumberFormatException e) {
            r = 0;
            red.setText("0");
        }
        
        try {
            g = Integer.parseInt(green.getText());
            if(g < 0 | g > 255)
            {
                g = 0;
                green.setText("0");
            }
        } catch (NumberFormatException e) {
            g = 0;
            green.setText("0");
        }
        
        try {
            b = Integer.parseInt(blue.getText());
            if(b < 0 | b > 255)
            {
                blue.setText("0");
                b = 0;
            }
        } catch(NumberFormatException e) {
            b = 0;
            blue.setText("0");
        }
        System.out.println("Setting color: " + r + " " + g + " " + b);
        return new Color(r,g,b);
    }
    
    private class MouseButtonListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            
            if(e.getSource() == addRectangleBtn)
            {
                if(state == DRAWRECT)
                    state = IDLE;
                else
                    state = DRAWRECT;
            }
            else if(e.getSource() == addCircleBtn)
            {
                if(state == DRAWCIRCLE)
                    state = IDLE;
                else
                    state = DRAWCIRCLE;
            }
            else if(e.getSource() == addLineBtn)
            {
                if(state == DRAWLINE)
                    state = IDLE;
                else
                    state = DRAWLINE;
            }
            else if(e.getSource() == moveShapeBtn)
            {
                if(state == MOVESHAPE)
                    state = IDLE;
                else
                    state = MOVESHAPE;
            }
            else if(e.getSource() == deleteShapeBtn)
            {
                if(state == DELSHAPE)
                    state = IDLE;
                else
                    state = DELSHAPE;
            }
            else if(e.getSource() == setColorBtn)
            {
                if(state == SETCOLOR)
                    state = IDLE;
                else
                    state = SETCOLOR;
            }
            System.out.println("Frame state set to: " + state.name());
            revalidate();
        }
    }
}
