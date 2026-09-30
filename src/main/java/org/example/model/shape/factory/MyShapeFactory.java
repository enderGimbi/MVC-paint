package org.example.model.shape.factory;

import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyShapeFactory {
    public MyShape createShape(ShapeType type){
        switch (type){
            case Ellipse -> {
                Shape shape = new Ellipse2D.Double();
                return new MyShape(shape);
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
