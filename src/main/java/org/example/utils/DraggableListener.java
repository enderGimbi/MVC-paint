package org.example.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DraggableListener  extends MouseAdapter {
    private final JComponent component;
    private Point mouseAnchor;

    public DraggableListener(JComponent component) {
        this.component = component;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseAnchor = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currentLoc = component.getLocation();
        int x = currentLoc.x + e.getX() - mouseAnchor.x;
        int y = currentLoc.y + e.getY() - mouseAnchor.y;
        component.setLocation(x,y);

    }
}
