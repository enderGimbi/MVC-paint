package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.Point;

public class ActionDraw extends Action {
    private final Model model;
    private MyShape shape;

    public ActionDraw(Model model) {
        this.model = model;
    }

    public void createShape(Point point) {
        this.pressedPoint = point;
        MyShape sampleShape = model.getCurrentShape();
        if (sampleShape != null) {
            this.shape = sampleShape.clone();
            execute();
        }
    }

    public void stretchShape(Point point) {
        releasedPoint = point;
        if (shape != null && pressedPoint != null) {
            shape.setFrame(pressedPoint, point);
        }
    }

    @Override
    public void mousePressed(Point point) {
        model.setMyShape();
        createShape(point);
    }

    @Override
    public void mouseDragged(Point point) {
        stretchShape(point);
    }

    @Override
    public void execute() {
        model.createCurrentShape(shape);
    }

    @Override
    public void unexecute() {
        model.removeShape(shape);
    }

}