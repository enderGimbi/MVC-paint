package org.example.view;

import org.example.controller.Controller;
import org.example.model.fill.Fill;
import org.example.model.fill.NoFill;
import org.example.model.factory.ShapeType;
import org.example.utils.Observable;
import org.example.utils.Observer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MyPanel extends JPanel implements Observer {
    private final Controller controller;
    private final JMenuBar menuBar;

    public MyPanel(Controller controller) {
        this.controller = controller;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                controller.mousePressed(arg0.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                controller.mouseDragged(arg0.getPoint());
            }

        });
        menuBar = new JMenuBar();
        var shapes = getShapes(controller);
        var colors = getColors(controller);
        var fill = getFill(controller);

        menuBar.add(shapes);
        menuBar.add(colors);
        menuBar.add(fill);
        add(menuBar);
    }

    private JMenu getShapes(Controller controller) {
        JMenu shapes = new JMenu("Фигуры");

        JMenuItem ellipse = new JMenuItem("Эллипс");
        ellipse.addActionListener((ActionEvent e)->{
            controller.getModel().setShape(ShapeType.Ellipse);
        });
        JMenuItem rectangle = new JMenuItem("Прямоугольник");
        rectangle.addActionListener((ActionListener) ->{
            controller.getModel().setShape(ShapeType.Rectangle);
        });

        shapes.add(ellipse);
        shapes.add(rectangle);

        menuBar.add(shapes);
        add(menuBar);

        return shapes;
    }

    private JMenu getColors(Controller controller){
        JMenu colors = new JMenu("Цвета");

        JMenuItem green = new JMenuItem("Зеленый");
        green.addActionListener((ActionListener)->{
            controller.getModel().setColor(Color.GREEN);
        } );
        JMenuItem red = new JMenuItem("Красный");
        red.addActionListener((actionEvent)->{
            controller.getModel().setColor(Color.RED);
        });
        JMenuItem blue = new JMenuItem("Синий");
        blue.addActionListener((actionEvent)->{
            controller.getModel().setColor(Color.BLUE);
        });

        colors.add(green);
        colors.add(red);
        colors.add(blue);

        return colors;
    }

    private JMenu getFill(Controller controller){
        JMenu fill = new JMenu("Заливка");

        JMenuItem withFill = new JMenuItem("С заливкой");
        withFill.addActionListener((actionEvent)->{
            controller.getModel().setFb(new Fill());
        });
        JMenuItem withoutFill = new JMenuItem("Без заливки");
        withoutFill.addActionListener((actionEvent)->{
            controller.getModel().setFb(new NoFill());
        });

        fill.add(withFill);
        fill.add(withoutFill);

        return fill;
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
