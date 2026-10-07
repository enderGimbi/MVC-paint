package org.example.model.shape.factory;

import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyShapeFactory {
    public static MyShape createShape(ShapeType type, Color color, FillBehavior fillBehavior){
        switch (type){
            case Ellipse -> {
                return new MyShape(new Ellipse2D.Double(),color,fillBehavior);
            }
            case Rectangle -> {
                Shape shape = new Rectangle2D.Double();
                return new MyShape(shape);
            }
            default -> {
                return new MyShape();
            }
        }
    }
}
