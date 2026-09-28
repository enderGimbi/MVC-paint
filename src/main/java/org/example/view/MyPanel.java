package org.example.view;

import org.example.controller.Controller;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;


public class MyPanel extends JPanel implements Observer {
    private final Controller controller;

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
        add(new JComboBox<>(new String[]{"123"}));
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
