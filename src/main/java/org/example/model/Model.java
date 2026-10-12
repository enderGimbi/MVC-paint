package org.example.model;

import org.example.model.fill.FillBehavior;
import org.example.model.factory.MyShapeFactory;
import org.example.model.factory.ShapeType;
import org.example.utils.Observable;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Model extends Observable {
    private MyShape currentShape;
    private Color color;
    private ShapeType shape;
    private FillBehavior fb;

    private final List<MyShape> shapesList = new ArrayList<>();

    public void setMyShape() {
        this.currentShape = MyShapeFactory.createShape(shape,color,fb);
    }

    public void setMyShape(MyShape shape) {
        this.currentShape = shape;
    }

    public void createCurrentShape(MyShape shape) {
        this.currentShape = shape;
        if (shape!=null && !shapesList.contains(shape)) {
            shapesList.add(shape);
        }
        this.setChanged();
        this.notifyObservers();
    }

    public void changeShape(Point2D x, Point2D y) {
        currentShape.setFrame(x, y);
        this.setChanged();
        this.notifyObservers();
    }

    public void draw(Graphics2D g) {
        for (MyShape s : shapesList) {
            s.draw(g);
        }
    }

    public Color getColor() { return color; }
    public void setColor(Color color) {
        this.color = color;
    }

    public ShapeType getShape() { return shape; }
    public void setShape(ShapeType shapeType) {
        this.shape = shapeType;
    }

    public FillBehavior getFb() { return fb; }
    public void setFb(FillBehavior fb) {
        this.fb = fb;
    }

    public MyShape getCurrentShape() {
        return currentShape;
    }

    public void clearBoard(){
        shapesList.clear();
        this.setChanged();
        this.notifyObservers();
    }
}
