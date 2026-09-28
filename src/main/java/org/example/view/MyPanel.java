package org.example.view;

import org.example.controller.Controller;
import org.example.controller.observer.PanelSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.annotation.PostConstruct;
import javax.swing.JPanel;

@Component
public class MyPanel extends JPanel implements PanelSubscriber {
    private Controller controller;

    @Autowired
    public void setController(Controller controller) {
        this.controller = controller;
    }
    @PostConstruct
    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                super.mousePressed(arg0);
                controller.mousePressed(arg0.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent arg0) {
                super.mouseDragged(arg0);
                controller.mouseDragget(arg0.getPoint());
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        controller.repaintAllModels(g2);
    }

    @Override
    public void update() {
        repaint();
    }
}
