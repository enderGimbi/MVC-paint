package org.example.view;

import org.example.controller.Controller;
import org.example.model.shape.factory.ShapeType;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import javax.swing.*;


public class MyPanel extends JPanel implements Observer {
    private final Controller controller;
    private final JMenuBar menuBar;

    public MyPanel(Controller controller) {
        this.controller = controller;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                controller.getPointOne(arg0.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                controller.getPointTwo(arg0.getPoint());
            }
        });
        menuBar = new JMenuBar();
        JMenu shapes = new JMenu("Фигуры");

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Эллипс");
        ellipse.addActionListener((ActionEvent e)->{
            controller.getModel().setShape(ShapeType.Ellipse);
            controller.getModel().setMyShape();
        });
        JRadioButtonMenuItem rectangle = new JRadioButtonMenuItem("Прямоугольник");
        rectangle.addActionListener((ActionListener) ->{
            controller.getModel().setShape(ShapeType.Rectangle);
            controller.getModel().setMyShape();
        });

        shapes.add(ellipse);
        shapes.add(rectangle);

        menuBar.add(shapes);
        add(menuBar);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        controller.draw(g2);
    }

    @Override
    public void update(Observable observable, Object o) {
        repaint();
    }
}
