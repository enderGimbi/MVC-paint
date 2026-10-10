package org.example.model.factory;

import org.example.model.fill.FillBehavior;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyShapeFactory {
    public static MyShape createShape(ShapeType type, Color color, FillBehavior fillBehavior){
        Shape shape;
        switch (type){
            case Ellipse -> {
                shape=new Ellipse2D.Double();
            }
            case Rectangle -> {
                shape=new Rectangle2D.Double();
            }
            default -> {
                return new MyShape();
            }
        }
        return new MyShape(shape,color,fillBehavior);
    }
}
