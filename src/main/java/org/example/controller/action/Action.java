package org.example.controller.action;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Action implements Cloneable {

    protected Point2D pressedPoint;
    protected Point2D releasedPoint;

    public abstract void mousePressed(Point point);

    public abstract void mouseDragged(Point point);

    public abstract void execute();

    public abstract void unexecute();

    @Override
    public Action clone() {
        try {
            return (Action) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
