package org.example.model;

import org.example.model.fill.FillBehavior;
import org.example.model.shape.factory.MyShapeFactory;
import org.example.model.shape.factory.ShapeType;
import org.example.view.Observable;

import java.awt.*;
import java.awt.geom.Point2D;

public class Model extends Observable {
    private MyShape currentShape;
    private Color color;
    private ShapeType shape;
    private FillBehavior fb;

    public void setMyShape() {
        this.currentShape = MyShapeFactory.createShape(shape,color,fb);
    }

    public void setMyShape(MyShape shape) {
        this.currentShape = shape;
    }

    public void changeShape(Point2D x, Point2D y) {
        currentShape.setFrame(x, y);
        this.setChanged();
        this.notifyObservers();
    }

    public void draw(Graphics2D g) {
        currentShape.draw(g);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ShapeType getShape() {
        return shape;
    }

    public void setShape(ShapeType shape) {
        this.shape = shape;
    }

    public FillBehavior getFb() {
        return fb;
    }

    public void setFb(FillBehavior fb) {
        this.fb = fb;
    }
}
