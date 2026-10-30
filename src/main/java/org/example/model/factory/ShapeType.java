package org.example.model.factory;

import org.example.model.MyShape;
import org.example.model.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public enum ShapeType {
    Rectangle {
        @Override
        public MyShape createShape(Color color, FillBehavior fillBehavior) {
            return new MyShape(new Rectangle2D.Double(), color, fillBehavior);
        }
    },
    Ellipse {
        @Override
        public MyShape createShape(Color color, FillBehavior fillBehavior) {
            return new MyShape(new Ellipse2D.Double(), color, fillBehavior);
        }
    };

    public abstract MyShape createShape(Color color, FillBehavior fillBehavior);
}
