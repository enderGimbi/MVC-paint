package org.example.model;

import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable {
    private final Color color;
    private final RectangularShape shape;
    private final FillBehavior fb;

    public MyShape() {
        color = Color.BLUE;
        shape = new Rectangle2D.Double();
        fb = new Fill();
        fb.setColor(color);
        fb.setShape(shape);
    }

    public MyShape(Shape shape, Color color, FillBehavior fill) {
        this.shape = (RectangularShape) shape;
        this.color = color;
        fb = fill;
        fb.setColor(color);
        fb.setShape((RectangularShape) shape);
    }

    public void setFrame(Point2D x, Point2D y) {
        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);
    }

    @Override
    public MyShape clone() {
        try {
            return (MyShape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
