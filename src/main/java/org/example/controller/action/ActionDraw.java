package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.Point;
import java.awt.geom.Point2D;

public class ActionDraw {
    private final Model model;
    private MyShape sampleShape;
    private MyShape shape;
    private Point2D firstPoint;

    public ActionDraw(Model model) {
        this.model = model;
        this.sampleShape = model.getCurrentShape();
    }

    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    public void createShape(Point point) {
        this.firstPoint = point;
        if (sampleShape != null) {
            this.shape = sampleShape.clone();
            model.createCurrentShape(this.shape);
        }
    }

    public void stretchShape(Point point) {
        if (shape != null && firstPoint != null) {
            shape.setFrame(firstPoint, point);
        }
    }
}