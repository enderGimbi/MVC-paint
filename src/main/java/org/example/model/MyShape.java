package org.example.model;

import lombok.Builder;
import lombok.Setter;
import org.example.model.fill.FillBehavior;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

@Setter
@Builder
public class MyShape {
    private Color color;
    private RectangularShape shape;
    private FillBehavior fb;
    public void setFrame(Point2D x, Point2D y) {
        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);

    }
}
