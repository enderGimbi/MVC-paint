package org.example.controller.action;

import org.example.model.Model;

import java.awt.*;

public class ActionChangeColor extends Action{
    private final Model model;
    private final Color newColor;
    private Color oldColor;

    public ActionChangeColor(Model model, Color newColor) {
        this.model = model;
        this.newColor = newColor;
    }

    @Override
    public void execute() {
        oldColor = model.getColor();
        model.changeColor(newColor);
    }

    @Override
    public void unexecute() {
        model.changeColor(oldColor);
    }
}
