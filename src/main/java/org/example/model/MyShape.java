package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

public class MyShape implements Cloneable, Serializable {
    private final Color color;
    private final RectangularShape shape;
    private final FillBehavior fb;
    @Setter
    @Getter
    private Point2D firstPoint;
    @Setter
    @Getter
    private Point2D secondPoint;

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
        this.firstPoint = x;
        this.secondPoint = y;
        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);
    }

    @Override
    public MyShape clone() {
        FillBehavior newFb;
        if (this.fb instanceof NoFill) {
            newFb = new NoFill();
        } else {
            newFb = new Fill();
        }
        newFb.setColor(this.color);

        Shape newGeom = (this.shape instanceof Ellipse2D) ? new Ellipse2D.Double() : new Rectangle2D.Double();
        MyShape cloned = new MyShape(newGeom, this.color, newFb);
        if (this.shape != null) {
            cloned.shape.setFrame(this.shape.getBounds2D());
            newFb.setShape(cloned.shape);
        }
        cloned.setFirstPoint(getFirstPoint());
        cloned.setSecondPoint(getSecondPoint());
        return cloned;
    }

}
