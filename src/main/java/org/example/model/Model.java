package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.model.fill.FillBehavior;
import org.example.model.factory.MyShapeFactory;
import org.example.model.factory.ShapeType;
import org.example.utils.Observable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Model extends Observable {
    private MyShape currentShape;
    @Setter
    private Color color;
    @Setter
    private ShapeType shape;
    @Setter
    private FillBehavior fb;

    private List<MyShape> shapesList = new ArrayList<>();

    public void setMyShape() {
        this.currentShape = MyShapeFactory.createShape(shape,color,fb);
    }

    public void createCurrentShape(MyShape shape) {
        this.currentShape = shape;
        if (shape!=null && !shapesList.contains(shape)) {
            shapesList.add(shape);
        }
        notifyObservers();
    }

    public void draw(Graphics2D g) {
        for (MyShape s : shapesList) {
            s.draw(g);
        }
    }

    public void setShapesList(List<MyShape> shapesList) {
        this.shapesList = (shapesList!=null)?shapesList:this.shapesList;
        notifyObservers();
    }

    public void removeShape(MyShape shape) {
        shapesList.remove(shape);
        notifyObservers();
    }
}
