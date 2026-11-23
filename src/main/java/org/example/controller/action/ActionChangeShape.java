package org.example.controller.action;

import org.example.model.Model;
import org.example.model.factory.ShapeType;

public class ActionChangeShape extends Action{
    private final Model model;
    private final ShapeType newType;
    private ShapeType oldType;

    public ActionChangeShape(Model model, ShapeType newType) {
        this.model = model;
        this.newType = newType;
    }

    @Override
    public void execute() {
        oldType = model.getShapeType();
        model.changeShape(newType);
    }

    @Override
    public void unexecute() {
        model.changeShape(oldType);
    }
}
