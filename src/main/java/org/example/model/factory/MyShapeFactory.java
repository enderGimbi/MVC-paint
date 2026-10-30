package org.example.model.factory;

import org.example.model.fill.FillBehavior;
import org.example.model.MyShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class MyShapeFactory {
    public static MyShape createShape(ShapeType type, Color color, FillBehavior fillBehavior) {
        return type.createShape(color, fillBehavior);
    }
}
