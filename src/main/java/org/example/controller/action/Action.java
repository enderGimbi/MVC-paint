package org.example.controller.action;

import java.awt.*;

public interface Action {
    public void mousePressed(Point point);

    public void mouseDragged(Point point);

    public void update();
}
