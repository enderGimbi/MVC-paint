package org.example.controller.action;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Action {

    protected Point2D pressedPoint;
    protected Point2D releasedPoint;

    public void mousePressed(Point point) {

    }

    public void mouseDragged(Point point) {

    }

    public abstract void execute();
    public abstract void unexecute();

}
